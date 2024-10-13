package org.adbbnod;
import utils.*;
import entity.*;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) throws Exception {

        MapLoader mapLoader = new MapLoader("C://Users//natal//Desktop//sage//IslandSurvivors//maps//mapaFinal.json");
        MapPanel mapPanel = new MapPanel(mapLoader);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Island Survivors");

        window.setUndecorated(true);

        // Start game panel
        GamePanel gamePanel = new GamePanel();

        gamePanel.add(mapPanel);
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

        MenuPanel menuPanel = new MenuPanel(characterPanels, godPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, menuPanel);
        splitPane.setDividerLocation(600);

        window.getContentPane().add(splitPane);
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
