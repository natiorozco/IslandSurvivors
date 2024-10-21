package org.adbbnod;

import entity.Character;
import utils.*;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class main implements Runnable {
    public static void main(String[] args) throws Exception {

        // Cargar el mapa
        MapLoader mapLoader = new MapLoader("C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\maps\\mapaFinal.json");
        MapPanel mapPanel = new MapPanel(mapLoader);
        // C:\Users\bryan\OneDrive\Documentos\Allan\IslandSurvivors\sprites\tile0.png
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

        InventoryPanel inventoryPanel = new InventoryPanel(generalInventory);
        // Inicialización de personajes
        Explorer explorer = new Explorer(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniNobleMan.png", generalInventory, inventoryPanel); // Nobleman
        Hunter hunter = new Hunter(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniPeasant.png", generalInventory, inventoryPanel); // Peasant
        Healer healer = new Healer(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniOldWoman.png", generalInventory, inventoryPanel); // Oldwoman
        Builder builder = new Builder(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniWorker.png", generalInventory, inventoryPanel); // Worker
        Gatherer gatherer = new Gatherer(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniVillagerMan.png", generalInventory, inventoryPanel); // Villager
        Scientist scientist = new Scientist(gamePanel, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\characters\\MiniQueen.png", generalInventory, inventoryPanel); // Queen

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

        GodPanel godPanel = new GodPanel(characters,mapPanel, gamePanel, (ExplorerPanel) explorerPanel, (BuilderPanel) builderPanel, (GathererPanel) gathererPanel, (HealerPanel) healerPanel, (HunterPanel) hunterPanel, (ScientistPanel) scientistPanel, window);


        //Panel del inventario
        inventoryPanel.setPreferredSize(new Dimension(100, window.getHeight()));
        inventoryPanel.setMinimumSize(new Dimension(100, window.getHeight()));
        inventoryPanel.setMaximumSize(new Dimension(100, window.getHeight()));
        inventoryPanel.setVisible(false);  // Iniciar oculto

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

        // Botón para mostrar/ocultar el menú God
        JButton toggleButton = new JButton("God");
        toggleButton.addActionListener(new ActionListener() {
            private boolean isMenuVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                isMenuVisible = !isMenuVisible;

                if (isMenuVisible) {
                    // Mostrar el menú de God, ocultar el inventario si está visible
                    menuPanel.setVisible(true);
                    inventoryPanel.setVisible(false);
                    splitPane.setRightComponent(menuPanel);
                    splitPane.setDividerLocation(50);  // Ajusta el tamaño del menú
                } else {
                    // Ocultar el menú de God
                    menuPanel.setVisible(false);
                    splitPane.setDividerLocation(0);  // Cerrar el panel lateral
                }
            }
        });

        // Botón para mostrar/ocultar el inventario
        JButton toggleInventoryButton = new JButton("Inventory");
        toggleInventoryButton.addActionListener(new ActionListener() {
            private boolean isInventoryVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                isInventoryVisible = !isInventoryVisible;

                if (isInventoryVisible) {
                    // Mostrar el inventario, ocultar el menú de God si está visible
                    inventoryPanel.setVisible(true);
                    menuPanel.setVisible(false);
                    splitPane.setRightComponent(inventoryPanel);
                    splitPane.setDividerLocation(200);  // Ajustar el tamaño del inventario
                } else {

                    // Ocultar el inventario
                    inventoryPanel.setVisible(false);
                    splitPane.setDividerLocation(0);  // Cerrar el panel lateral
                }
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
        buttonPanel.add(toggleInventoryButton);

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

        // Iniciar hilo para eventos aleatorios en el mapa
        Thread eventThread = new Thread(new GameEventThread(mapPanel));
        eventThread.start();
    }

    @Override
    public void run() {
        // Puedes manejar la lógica principal del juego aquí si lo necesitas.
    }




}
