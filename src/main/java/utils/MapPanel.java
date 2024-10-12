package utils;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.awt.image.BufferedImage;

public class MapPanel extends JPanel {
    private MapLoader mapLoader;

    public MapPanel(MapLoader mapLoader) {
        this.mapLoader = mapLoader;
        setPreferredSize(new Dimension(mapLoader.getMap()[0].length * mapLoader.getTileWidth(),
                mapLoader.getMap().length * mapLoader.getTileHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Obtener el mapa y los tiles
        int[][] map = mapLoader.getMap();
        Map<Integer, BufferedImage> tileMap = mapLoader.getTileMap();
        int tileWidth = mapLoader.getTileWidth();
        int tileHeight = mapLoader.getTileHeight();

        // Dibujar cada tile en su posición correspondiente
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int tileId = map[y][x];
                BufferedImage tileImage = tileMap.get(tileId);
                if (tileImage != null) {
                    g.drawImage(tileImage, x * tileWidth, y * tileHeight, null);
                }
            }
        }
    }
}
