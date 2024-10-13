package org.adbbnod;

import utils.*;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
        public static void main(String[] args) throws Exception {

                // Cargar el mapa
                MapLoader mapLoader = new MapLoader("C://Users//natal//Desktop//sage//IslandSurvivors//maps//mapaFinal.json");
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

                // Inicialización de personajes
                Explorer explorer = new Explorer(gamePanel);
                Hunter hunter = new Hunter(gamePanel);
                Healer healer = new Healer(gamePanel);
                Builder builder = new Builder(gamePanel);
                Gatherer gatherer = new Gatherer(gamePanel);
                Scientist scientist = new Scientist(gamePanel);

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

                GodPanel godPanel = new GodPanel();

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
