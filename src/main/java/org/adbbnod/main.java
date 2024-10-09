package org.adbbnod;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Island Survivors");


        window.setUndecorated(true);

        // Start game panel
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.startGameThread();

        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(window);
        } else {
            window.setSize(800, 600);
            window.setVisible(true);
        }
    }
}
