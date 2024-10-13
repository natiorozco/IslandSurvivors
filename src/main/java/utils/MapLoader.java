package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class MapLoader {
    private int[][] map; // Matriz que representa el mapa combinado
    private Map<Integer, BufferedImage> tileMap;
    private int tileWidth;
    private int tileHeight;

    public MapLoader(String jsonFilePath) throws Exception {
        // Leer el archivo JSON con Jackson
        System.out.println(jsonFilePath);
        ObjectMapper mapper = new ObjectMapper();
        String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        JsonNode json = mapper.readTree(content);

        // Obtener dimensiones del mapa
        int height = json.get("height").asInt();
        int width = json.get("width").asInt(); // Cambié esto a json.get("width").asInt()

        // Crear la matriz del mapa, inicialmente llena de ceros
        map = new int[height][width];

        // Cargar el tileset
        tileMap = new HashMap<>();
        JsonNode tilesets = json.get("tilesets");
        for (JsonNode tileset : tilesets) {
            int firstgid = tileset.get("firstgid").asInt();
            String imagePath = tileset.get("image").asText();

            tileWidth = tileset.get("tilewidth").asInt();
            tileHeight = tileset.get("tileheight").asInt();
            int tileCount = tileset.get("tilecount").asInt();
            int columns = tileset.get("columns").asInt();

            // Cargar la imagen del tileset
            BufferedImage tilesetImage = ImageIO.read(new File(imagePath));

            // Extraer y mapear los tiles
            for (int id = 0; id < tileCount; id++) {
                int tileX = (id % columns) * tileWidth;
                int tileY = (id / columns) * tileHeight;

                BufferedImage tileImage = tilesetImage.getSubimage(tileX, tileY, tileWidth, tileHeight);
                tileMap.put(firstgid + id, tileImage);
            }
        }

        // Obtener los layers
        JsonNode layers = json.get("layers");

        // Acceder directamente a las capas con datos
        if (layers.size() > 1) { // Verificar si hay suficientes capas
            processLayer(layers.get(1), height, width); // Tile Layer 1
        }
        if (layers.size() > 2) { // Verificar si hay suficientes capas
            processLayer(layers.get(2), height, width); // Tile Layer 2
        }
    }

    private void processLayer(JsonNode layer, int height, int width) {
        JsonNode data = layer.get("data");

        // Verificar si data no es nulo y es un arreglo
        if (data == null || !data.isArray()) {
            System.out.println("Layer '" + layer.get("name").asText() + "' no tiene datos o no es un arreglo.");
            return; // Terminar el procesamiento
        }

        // Combinar el layer en el mapa
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Calcular el índice correctamente
                int index = y * width + x;

                // Verificar que no se salga del rango de data
                if (index < data.size()) {
                    int tileId = data.get(index).asInt(); // Leer el tileId
                    if (tileId != 0) { // Si el tile no es cero, se mantiene en el mapa
                        map[y][x] = tileId;
                    }
                } else {
                    System.out.println("Índice fuera de rango para layer '" + layer.get("name").asText() + "'.");
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public Map<Integer, BufferedImage> getTileMap() {
        return tileMap;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
