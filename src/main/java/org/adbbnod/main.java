package org.adbbnod;

import entity.Character;
import utils.*;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws Exception {
        // Cargar el mapa
        MapLoader mapLoader = new MapLoader("C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\maps\\mapaFinal.json");
        MapPanel mapPanel = new MapPanel(mapLoader);

        // Definir dimensiones del mapa
        int tileSize = 32;
        int mapWidth = 30 * tileSize;
        int mapHeight = 30 * tileSize;

        mapPanel.setPreferredSize(new Dimension(mapWidth, mapHeight));
        mapPanel.setMinimumSize(new Dimension(mapWidth, mapHeight));
        mapPanel.setMaximumSize(new Dimension(mapWidth, mapHeight));

        // Configuración de la ventana
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Island Survivors");
        window.setUndecorated(true);

        // Contenedor del juego
        JPanel gameContainer = new JPanel();
        gameContainer.setLayout(new OverlayLayout(gameContainer));
        gameContainer.add(mapPanel);

        GamePanel gamePanel = new GamePanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameContainer, BorderLayout.CENTER);

        ArrayList<Resource> generalInventory = new ArrayList<Resource>();

        // Inicialización de personajes
        Explorer explorer = new Explorer(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniNobleMan.png", generalInventory); // Nobleman
        Hunter hunter = new Hunter(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniPeasant.png", generalInventory); // Peasant
        Healer healer = new Healer(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniOldWoman.png", generalInventory); // Oldwoman
        Builder builder = new Builder(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniWorker.png", generalInventory); // Worker
        Gatherer gatherer = new Gatherer(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniVillagerMan.png", generalInventory); // Villager
        Scientist scientist = new Scientist(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniQueen.png", generalInventory); // Queen



        // Establecer coordenadas iniciales
        explorer.setX(7);
        explorer.setY(7);
        hunter.setX(8);
        hunter.setY(9);
        healer.setX(10);
        healer.setY(9);
        builder.setX(7);
        builder.setY(10);
        gatherer.setX(9);
        gatherer.setY(10);
        scientist.setX(8);
        scientist.setY(8);

        // Agregar personajes al panel del mapa
        mapPanel.addCharacter(explorer);
        mapPanel.addCharacter(hunter);
        mapPanel.addCharacter(healer);
        mapPanel.addCharacter(builder);
        mapPanel.addCharacter(gatherer);
        mapPanel.addCharacter(scientist);

        // Paneles de personajes
        JPanel explorerPanel = new ExplorerPanel(explorer, mapPanel);
        JPanel hunterPanel = new HunterPanel(hunter, mapPanel);
        JPanel healerPanel = new HealerPanel(healer, mapPanel);
        JPanel builderPanel = new BuilderPanel(builder, mapPanel);
        JPanel gathererPanel = new GathererPanel(gatherer, mapPanel);
        JPanel scientistPanel = new ScientistPanel(scientist, mapPanel);

        JPanel[] characterPanels = {
                explorerPanel, healerPanel,
                builderPanel, gathererPanel, scientistPanel, hunterPanel
        };

        Character[] characters = {explorer,healer,builder,gatherer,scientist,hunter};

        GodPanel godPanel = new GodPanel(characters, mapPanel, gamePanel);

        // Panel del menú
        MenuPanel menuPanel = new MenuPanel(characterPanels, godPanel);
        menuPanel.setPreferredSize(new Dimension(50, window.getHeight()));
        menuPanel.setMinimumSize(new Dimension(50, window.getHeight()));
        menuPanel.setMaximumSize(new Dimension(50, window.getHeight()));
        menuPanel.setVisible(false);

        // Panel dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, menuPanel);
        splitPane.setDividerLocation(0);
        splitPane.setEnabled(false);
        splitPane.setDividerSize(0);

        // Botón para mostrar/ocultar el menú
        JButton toggleButton = new JButton("X");
        toggleButton.addActionListener(new ActionListener() {
            private boolean isVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                menuPanel.setVisible(isVisible);
                splitPane.setDividerLocation(isVisible ? 50 : 0);
            }
        });

        mapPanel.revealTile(7, 7);
        mapPanel.revealTile(7, 8);
        mapPanel.revealTile(7, 9);
        mapPanel.revealTile(7, 10);

        mapPanel.revealTile(8, 7);
        mapPanel.revealTile(8, 8);
        mapPanel.revealTile(8, 9);
        mapPanel.revealTile(8, 10);

        mapPanel.revealTile(9, 7);
        mapPanel.revealTile(9, 8);
        mapPanel.revealTile(9, 9);
        mapPanel.revealTile(9, 10);

        mapPanel.revealTile(10, 7);
        mapPanel.revealTile(10, 8);
        mapPanel.revealTile(10, 9);
        mapPanel.revealTile(10, 10);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(toggleButton);

        buttonPanel.setOpaque(false);
        gamePanel.add(buttonPanel, BorderLayout.NORTH);

        // Añadir el panel dividido a la ventana
        window.getContentPane().add(splitPane);
        window.pack();
        window.setVisible(true);

        // Configuración de pantalla completa
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(window);
        } else {
            window.setSize(800, 600);
            window.setVisible(true);
        }
    }
}