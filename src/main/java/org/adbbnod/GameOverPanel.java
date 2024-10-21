package org.adbbnod;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    public GameOverPanel() {
        setLayout(new BorderLayout());

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
        gameOverLabel.setForeground(Color.RED);

        add(gameOverLabel, BorderLayout.CENTER);

        setEnabled(false);
    }

    public void showGameOver(JFrame parentFrame) {

        JDialog dialog = new JDialog(parentFrame, "Game Over", true);
        dialog.getContentPane().add(this);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(parentFrame);


        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
    }
}
