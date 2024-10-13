package org.adbbnod;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(JPanel[] characterPanels, JPanel godPanel) {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(200, 500));

        JPanel characterPanelContainer = new JPanel();
        characterPanelContainer.setLayout(new GridLayout(3, 2, 5, 5));
        characterPanelContainer.setBackground(Color.LIGHT_GRAY);
        characterPanelContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        for (JPanel panel : characterPanels) {
            characterPanelContainer.add(panel);
        }

        this.add(characterPanelContainer, BorderLayout.CENTER);

        godPanel.setPreferredSize(new Dimension(200, 85));
        this.add(godPanel, BorderLayout.SOUTH);
    }
}
