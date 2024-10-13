package org.adbbnod;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import entity.Hunter;

public class HunterPanel extends JPanel {
    Hunter hunter;
    JLabel title = new JLabel("CAZADOR");
    JLabel energy = new JLabel("Energía:");
    JLabel health = new JLabel("Salud:");
    BatteryPanel energyBar = new BatteryPanel();
    BatteryPanel healthBar = new BatteryPanel();
    JTextField x = new JTextField(2); // Igual que en Explorer
    JTextField y = new JTextField(2);
    JButton moveButton = new JButton("Mover");
    JButton huntButton = new JButton("Cazar");
    JButton eatButton = new JButton("Comer");
    JButton accidentButton = new JButton("Accidente");
    JButton illnessButton = new JButton("Enfermedad");

    Timer moveTimer;
    int targetX;
    int targetY;
    int stepX;
    int stepY;

    public HunterPanel(Hunter hunter) {
        this.hunter = hunter;
        this.setLayout(new GridBagLayout()); // Usamos GridBagLayout
        this.setBackground(new Color(245, 245, 245)); // Fondo suave
        this.setBorder(new LineBorder(new Color(100, 100, 100), 1, true)); // Borde redondeado

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Margen entre componentes

        // Título estilizado
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(new Color(50, 50, 150));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(title, gbc);

        // Panel de estadísticas
        JPanel statsPanel = new JPanel(new GridBagLayout());
        statsPanel.setBackground(new Color(230, 230, 250));
        statsPanel.setBorder(new LineBorder(new Color(150, 150, 200), 1, true));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(statsPanel, gbc);

        GridBagConstraints statsGbc = new GridBagConstraints();
        statsGbc.insets = new Insets(3, 3, 3, 3);
        statsGbc.fill = GridBagConstraints.HORIZONTAL;

        statsGbc.gridx = 0;
        statsGbc.gridy = 0;
        statsPanel.add(energy, statsGbc);
        statsGbc.gridx = 1;
        statsPanel.add(energyBar, statsGbc);
        statsGbc.gridx = 0;
        statsGbc.gridy = 1;
        statsPanel.add(health, statsGbc);
        statsGbc.gridx = 1;
        statsPanel.add(healthBar, statsGbc);

        energyBar.updateBatteryLevel(hunter.getEnergy());
        healthBar.updateBatteryLevel(hunter.getHealth());

        // Panel de movimiento
        JPanel movePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        movePanel.setBackground(new Color(230, 245, 230)); // Color verde suave
        movePanel.setBorder(new LineBorder(new Color(120, 180, 120), 1, true));
        gbc.gridy = 2;
        this.add(movePanel, gbc);

        movePanel.add(new JLabel("X:"));
        movePanel.add(x);
        movePanel.add(new JLabel("Y:"));
        movePanel.add(y);
        movePanel.add(moveButton);

        // Panel de acciones con GridLayout
        JPanel actionsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        actionsPanel.setBackground(new Color(250, 240, 230)); // Fondo suave
        actionsPanel.setBorder(new LineBorder(new Color(200, 150, 100), 1, true));
        gbc.gridy = 3;
        this.add(actionsPanel, gbc);

        // Estilizamos los botones
        stylizeButton(huntButton, new Color(100, 180, 255));
        stylizeButton(eatButton, new Color(150, 250, 150));
        stylizeButton(accidentButton, new Color(255, 100, 100));
        stylizeButton(illnessButton, new Color(255, 180, 100));

        actionsPanel.add(huntButton);
        actionsPanel.add(eatButton);
        actionsPanel.add(accidentButton);
        actionsPanel.add(illnessButton);

        // Listeners para los botones
        eatButton.addActionListener(e -> {
            hunter.eat();
            energyBar.updateBatteryLevel(hunter.getEnergy());
            repaint();
        });

        accidentButton.addActionListener(e -> {
            hunter.accident();
            energyBar.updateBatteryLevel(hunter.getEnergy());
            healthBar.updateBatteryLevel(hunter.getHealth());
            repaint();
        });

        illnessButton.addActionListener(e -> {
            hunter.sickness();
            energyBar.updateBatteryLevel(hunter.getEnergy());
            healthBar.updateBatteryLevel(hunter.getHealth());
            repaint();
        });

        moveButton.addActionListener(e -> {
            try {
                targetX = Integer.parseInt(x.getText());
                targetY = Integer.parseInt(y.getText());
                startMove();
            } catch (NumberFormatException ex) {
                // Manejar error de formato
            }
        });

        // Ajusta el tamaño total del panel
        this.setPreferredSize(new Dimension(300, 250)); // Aumenta el tamaño
    }

    private void stylizeButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(color.darker(), 1, true)); // Borde redondeado
    }

    // Movimiento del cazador
    private void startMove() {
        int deltaX = targetX - hunter.getX();
        int deltaY = targetY - hunter.getY();

        stepX = deltaX / 50;
        stepY = deltaY / 50;

        moveTimer = new Timer(50, e -> moveStep());
        moveTimer.start();
    }

    private void moveStep() {
        int currentX = hunter.getX();
        int currentY = hunter.getY();

        if (Math.abs(currentX - targetX) > Math.abs(stepX) || Math.abs(currentY - targetY) > Math.abs(stepY)) {
            hunter.setX(currentX + stepX);
            hunter.setY(currentY + stepY);
            repaint();
        } else {
            hunter.setX(targetX);
            hunter.setY(targetY);
            moveTimer.stop();
        }
    }
}
