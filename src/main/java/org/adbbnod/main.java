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
        MapLoader mapLoader = new MapLoader("C:\\Users\\bryan\\OneDrive\\Documentos\\Allan\\IslandSurvivors\\maps\\mapaFinal.json");
        MapPanel mapPanel = new MapPanel(mapLoader);

        // Tamaño del mapa: 30x30 tiles, cada uno de 32x32 píxeles
        int tileSize = 32;
        int mapWidth = 30 * tileSize;
        int mapHeight = 30 * tileSize;

        // Establecer el tamaño del mapPanel
        mapPanel.setPreferredSize(new Dimension(mapWidth, mapHeight));
        mapPanel.setMinimumSize(new Dimension(mapWidth, mapHeight));
        mapPanel.setMaximumSize(new Dimension(mapWidth, mapHeight));

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Island Survivors");

        window.setUndecorated(true);

        // Crear un JPanel con GridBagLayout para centrar el mapa
        JPanel mapContainer = new JPanel(new GridBagLayout());
        mapContainer.setOpaque(false); // Hacer que el contenedor sea transparente
        mapContainer.add(mapPanel, new GridBagConstraints()); // Añadir el mapPanel centrado

        // Start game panel
        GamePanel gamePanel = new GamePanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(mapContainer, BorderLayout.CENTER); // Centra el contenedor del mapa

        Explorer explorer = new Explorer(gamePanel);
        Hunter hunter = new Hunter(gamePanel);
        Healer healer = new Healer(gamePanel);
        Builder builder = new Builder(gamePanel);
        Gatherer gatherer = new Gatherer(gamePanel);
        Scientist scientist = new Scientist(gamePanel);

        ExplorerPanel explorerPanel = new ExplorerPanel(explorer);
        HunterPanel hunterPanel = new HunterPanel(hunter);
        HealerPanel healerPanel = new HealerPanel(healer);
        BuilderPanel builderPanel = new BuilderPanel(builder);
        GathererPanel gathererPanel = new GathererPanel(gatherer);
        ScientistPanel scientistPanel = new ScientistPanel(scientist);

        JPanel[] characterPanels = {
                explorerPanel, healerPanel,
                builderPanel, gathererPanel, scientistPanel, hunterPanel
        };

        GodPanel godPanel = new GodPanel();

        // Ajuste del tamaño del character panel
        MenuPanel menuPanel = new MenuPanel(characterPanels, godPanel);
        menuPanel.setPreferredSize(new Dimension(150, window.getHeight())); // Panel más pequeño
        menuPanel.setMinimumSize(new Dimension(150, window.getHeight())); // Tamaño mínimo para evitar redimensionamiento
        menuPanel.setMaximumSize(new Dimension(150, window.getHeight())); // Fijar tamaño máximo también

        // Inicialmente ocultar el menuPanel
        menuPanel.setVisible(false);

        // Crear el splitPane ANTES de crear el botón para que esté accesible
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, menuPanel);
        splitPane.setDividerLocation(0); // Colapsado al inicio
        splitPane.setEnabled(false); // Deshabilitar el redimensionamiento
        splitPane.setDividerSize(0); // Eliminar la barra de redimensionamiento

        // Botón para mostrar/ocultar el menuPanel
        JButton toggleButton = new JButton("X"); // Solo una "X"
        toggleButton.addActionListener(new ActionListener() {
            private boolean isVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                menuPanel.setVisible(isVisible); // Mostrar u ocultar el panel de personajes
                splitPane.setDividerLocation(isVisible ? 150 : 0); // Ajustar la división
            }
        });

        // Panel para colocar el botón en la esquina superior derecha
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alinear a la derecha
        buttonPanel.add(toggleButton);
        buttonPanel.setOpaque(false); // Transparente para que no cubra el mapa
        gamePanel.add(buttonPanel, BorderLayout.NORTH); // Añadir el botón en la parte superior

        window.getContentPane().add(splitPane);
        window.pack();
        window.setVisible(true);

        gamePanel.startGameThread();

        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(window);
        } else {
            window.setSize(800, 600);
            window.setVisible(true);
        }
    }
}
