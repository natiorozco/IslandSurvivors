package org.adbbnod;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(JPanel[] characterPanels, JPanel godPanel) {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(200, 500));

        // Container para los paneles de personajes
        JPanel characterPanelContainer = new JPanel();
        characterPanelContainer.setLayout(new GridLayout(3, 2, 5, 5)); // Espacio entre componentes ajustado
        characterPanelContainer.setBackground(Color.LIGHT_GRAY);
        characterPanelContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Margen adicional

        // Añadir los paneles de personajes
        for (JPanel panel : characterPanels) {
            characterPanelContainer.add(panel);
        }

        this.add(characterPanelContainer, BorderLayout.CENTER);

        // Ajustar el tamaño del godPanel
        godPanel.setPreferredSize(new Dimension(200, 85));  // Ajusta la altura según sea necesario
        this.add(godPanel, BorderLayout.SOUTH);
    }
}
