package org.adbbnod;

import utils.*;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
    public static void main(String[] args) throws Exception {

        MapLoader mapLoader = new MapLoader("C:\\Users\\adbyb\\OneDrive\\Documentos\\GitHub\\IslandSurvivors\\maps\\mapaFinal.json");
        MapPanel mapPanel = new MapPanel(mapLoader);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Island Survivors");

        window.setUndecorated(true);

        // Start game panel
        GamePanel gamePanel = new GamePanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(mapPanel, BorderLayout.CENTER); // Centra el mapa

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

        // Crear el splitPane ANTES de crear el botón para que esté accesible
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, menuPanel);
        splitPane.setDividerLocation(150); // Ajusta la ubicación inicial a un tamaño pequeño
        splitPane.setEnabled(false); // Deshabilitar el redimensionamiento
        splitPane.setDividerSize(0); // Eliminar la barra de redimensionamiento

        // Botón para mostrar/ocultar el menuPanel
        JButton toggleButton = new JButton("Mostrar/Ocultar Panel de Personajes");
        toggleButton.addActionListener(new ActionListener() {
            private boolean isVisible = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                menuPanel.setVisible(isVisible); // Mostrar u ocultar el panel de personajes
                splitPane.setDividerLocation(isVisible ? 150 : window.getWidth()); // Ajustar la división
            }
        });

        // Agregar el botón en la parte inferior del mapa
        gamePanel.add(toggleButton, BorderLayout.SOUTH);

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
