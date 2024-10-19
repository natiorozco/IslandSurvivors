package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import entity.Animal;
import entity.Character;
import entity.Entity;
import entity.Resource;

public class MapPanel extends JPanel {
    private MapLoader mapLoader;
    private boolean[][] fog;
    private List<Character> characters;
    private List<Animal> animals;
    private List<Resource> resources;

    public MapPanel(MapLoader mapLoader) {
        this.mapLoader = mapLoader;
        this.characters = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.resources = new ArrayList<>();

        setPreferredSize(new Dimension(mapLoader.getMap(0)[0].length * mapLoader.getTileWidth(),
                mapLoader.getMap(0).length * mapLoader.getTileHeight()));

        // Inicializar la niebla
        int width = mapLoader.getMap(0)[0].length;
        int height = mapLoader.getMap(0).length;
        fog = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                fog[y][x] = true;
            }
        }
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void addAnimals(Animal animal) {
        animals.add(animal);
    }

    public void addResources(Resource resource) {
        resources.add(resource);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int mapCount = mapLoader.getMapCount();
        Map<Integer, BufferedImage> tileMap = mapLoader.getTileMap();
        int tileWidth = mapLoader.getTileWidth();
        int tileHeight = mapLoader.getTileHeight();

        for (int mapIndex = 0; mapIndex < mapCount; mapIndex++) {
            int[][] map = mapLoader.getMap(mapIndex);

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

        drawFog(g, tileWidth, tileHeight);

        // Dibuja los personajes en sus posiciones
        for (Character character : characters) {
            drawCharacter(g, character, 2); // Aquí puedes pasar el índice del sprite si tienes diferentes estados
        }

        for (Animal animal: animals){
            if (Objects.equals(animal.getType(), "Oso")){
                spawnAnimal(g,animal,1);
            } else if (Objects.equals(animal.getType(), "Caballo")){
                spawnAnimal(g,animal,7);
            }else if (Objects.equals(animal.getType(), "Oveja")){
                spawnAnimal(g,animal,22);
            } else if(Objects.equals(animal.getType(), "Pollo")){
                spawnAnimal(g,animal, 37);
            }
        }

        for (Resource resource : resources){
            if (Objects.equals(resource.getType(), "vegetal")){
                spawnResource(g,resource,48);
            } else if(Objects.equals(resource.getType(), "planta medicinal")){
                spawnResource(g, resource, 1);
            } else if (Objects.equals(resource.getType(), "madera")){
                spawnResource(g,resource, 1);
            } else if (Objects.equals(resource.getType(), "piedra")) {
                spawnResource(g, resource, 4);
            }   else if(Objects.equals(resource.getType(), "liana")){
                spawnResource(g, resource, 0);
            }
            }

    }

    private void drawCharacter(Graphics g, Character character, int indx) {
        BufferedImage spriteToDraw = character.getSprite(indx);
        // Multiplica las coordenadas por el tamaño del tile para posicionar correctamente
        int x = character.getX() * mapLoader.getTileWidth();
        int y = character.getY() * mapLoader.getTileHeight();
        g.drawImage(spriteToDraw, x, y, null);
    }


    private void drawFog(Graphics g, int tileWidth, int tileHeight) {
        g.setColor(new Color(0, 0, 0, 200));
        for (int y = 0; y < fog.length; y++) {
            for (int x = 0; x < fog[y].length; x++) {
                if (fog[y][x]) {
                    g.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                }
            }
        }
    }

    public void revealTile(int x, int y) {
        if (x >= 0 && x < fog[0].length && y >= 0 && y < fog.length) {
            fog[y][x] = false;
            repaint(); // Redibuja el mapa después de revelar un tile
        }
    }

    public void spawnAnimal(Graphics g, Animal animal, int indx){
        BufferedImage spriteToDraw = animal.getSprite(indx);
        int x = animal.getX() * mapLoader.getTileWidth();
        int y = animal.getY() * mapLoader.getTileHeight();
        g.drawImage(spriteToDraw, x, y, null);
    }

    public void spawnResource(Graphics g, Resource resource,int indx){
        BufferedImage spriteToDraw = resource.getSprite(indx);
        int x = resource.getX() * mapLoader.getTileWidth();
        int y = resource.getY() * mapLoader.getTileHeight();
        g.drawImage(spriteToDraw, x, y, null);
    }

    public boolean isRevealed(int x, int y){
        if (x >= 0 && x < fog[0].length && y >= 0 && y < fog.length) {
            if (!fog[y][x]){
                return true;
            }
        }
        return false;
    }

    public Animal animalHere(int x, int y){
        for (Animal animal: animals){
            if (animal.getX()==x && animal.getY()==y){
                return animal;
            }
        }
        return null;
    }

    public Resource resourceHere(int x, int y){
        for (Resource resource: resources){
            if (resource.getX()==x && resource.getY()==y){
                return resource;
            }
        }
        return null;
    }

    public Character characterHere(int x, int y){
        for (Character character: characters){
            if (character.getX()==x && character.getY()==y){
                return character;
            }
        }
        return null;
    }

    public Character characterToHealHere(int x, int y){
        for (Character character: characters){
            if (character.getX()==(x+1) && character.getY()==y){
                return character;
            }
        }
        return null;
    }
}
