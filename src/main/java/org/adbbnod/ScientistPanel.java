package org.adbbnod;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.Scientist;
import utils.MapPanel;

public class ScientistPanel extends JPanel {
    Scientist scientist;
    JLabel title = new JLabel("CIENTÍFICO");
    JLabel energy = new JLabel("Energía:");
    JLabel health = new JLabel("Salud:");
    BatteryPanel energyBar = new BatteryPanel();
    BatteryPanel healthBar = new BatteryPanel();
    JTextField x = new JTextField(2);
    JTextField y = new JTextField(2);
    JButton moveButton = new JButton("Mover");
    JButton eatButton = new JButton("Comer");
    JButton experimentButton = new JButton("Experimentar");
    JButton accidentButton = new JButton("Accidente");
    JButton illnessButton = new JButton("Enfermedad");

    Timer moveTimer;
    int targetX;
    int targetY;
    int stepX;
    int stepY;

    public ScientistPanel(Scientist scientist, MapPanel map) {
        this.scientist = scientist;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(245, 245, 245));
        this.setBorder(new LineBorder(new Color(100, 100, 100), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Título
        title.setText("CIENTÍFICO | (" + scientist.getX() + "," + scientist.getY() + ")");
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

        energyBar.updateBatteryLevel(scientist.getEnergy());
        healthBar.updateBatteryLevel(scientist.getHealth());

        // Panel de movimiento
        JPanel movePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        movePanel.setBackground(new Color(230, 245, 230));
        movePanel.setBorder(new LineBorder(new Color(120, 180, 120), 1, true));
        gbc.gridy = 2;
        this.add(movePanel, gbc);

        movePanel.add(new JLabel("X:"));
        movePanel.add(x);
        movePanel.add(new JLabel("Y:"));
        movePanel.add(y);
        movePanel.add(moveButton);

        // Panel de acciones
        JPanel actionsPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        actionsPanel.setBackground(new Color(250, 240, 230));
        actionsPanel.setBorder(new LineBorder(new Color(200, 150, 100), 1, true));
        gbc.gridy = 3;
        this.add(actionsPanel, gbc);

        stylizeButton(experimentButton, new Color(100, 180, 255));
        stylizeButton(eatButton, new Color(150, 250, 150));
        stylizeButton(accidentButton, new Color(255, 100, 100));
        stylizeButton(illnessButton, new Color(255, 180, 100));

        actionsPanel.add(experimentButton);
        actionsPanel.add(eatButton);
        actionsPanel.add(accidentButton);
        actionsPanel.add(illnessButton);

        eatButton.addActionListener(e -> {
            scientist.eat();
            energyBar.updateBatteryLevel(scientist.getEnergy());
            repaint();
        });

        accidentButton.addActionListener(e -> {
            scientist.accident();
            energyBar.updateBatteryLevel(scientist.getEnergy());
            healthBar.updateBatteryLevel(scientist.getHealth());
            repaint();
        });

        illnessButton.addActionListener(e -> {
            scientist.sickness();
            energyBar.updateBatteryLevel(scientist.getEnergy());
            healthBar.updateBatteryLevel(scientist.getHealth());
            repaint();
        });

        moveButton.addActionListener(e -> {
            try {
                targetX = Integer.parseInt(x.getText());
                targetY = Integer.parseInt(y.getText());
                if(map.isRevealed(targetX,targetY))
                    startMove();
                title.setText("CIENTÍFICO | (" + scientist.getX() + "," + scientist.getY() + ")");
            } catch (NumberFormatException ex) {
                System.out.println("Por favor ingrese valores numéricos válidos para X e Y.");
            }
        });

        this.setPreferredSize(new Dimension(240, 200));
    }

    private void stylizeButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(color.darker(), 1, true));
    }

    private void startMove() {
        moveTimer = new Timer(50, e -> moveStep());
        moveTimer.start();
    }

    private void moveStep() {
        // Llamar al método move en el explorador
        scientist.move(targetX, targetY);
        updateBars();

        repaint();

        // Detener el timer si el explorador ha llegado a la posición objetivo
        if (scientist.getX() == targetX && scientist.getY() == targetY) {
            System.out.println("Posición final: (" + scientist.getX() + ", " + scientist.getY() + ")");
            moveTimer.stop();
        }
    }
    private void updateBars() {
        energyBar.updateBatteryLevel(scientist.getEnergy());
        healthBar.updateBatteryLevel(scientist.getHealth());
    }
}
