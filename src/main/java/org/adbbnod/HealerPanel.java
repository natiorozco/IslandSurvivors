package org.adbbnod;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import entity.Healer;

public class HealerPanel extends JPanel {
    // Atributos principales
    Healer healer; // Objeto curandero
    JLabel title = new JLabel("CURANDERO"); // Título del panel
    JLabel energy = new JLabel("Energía:");
    JLabel health = new JLabel("Salud:");
    BatteryPanel energyBar = new BatteryPanel(); // Barra de energía
    BatteryPanel healthBar = new BatteryPanel(); // Barra de salud
    JTextField x = new JTextField(2); // Campo para coordenada X
    JTextField y = new JTextField(2); // Campo para coordenada Y
    JButton moveButton = new JButton("Mover"); // Botón de mover
    JButton healButton = new JButton("Curar"); // Botón para curar (por implementar)
    JButton prepareButton = new JButton("Preparar remedio"); // Botón para preparar remedio (por implementar)
    JButton eatButton = new JButton("Comer"); // Botón para comer
    JButton accidentButton = new JButton("Accidente"); // Botón para simular un accidente
    JButton illnessButton = new JButton("Enfermedad"); // Botón para simular una enfermedad

    Timer moveTimer; // Temporizador para controlar el movimiento
    int targetX; // Coordenada X de destino
    int targetY; // Coordenada Y de destino
    int stepX; // Incremento en X para cada paso del movimiento
    int stepY; // Incremento en Y para cada paso del movimiento

    // Constructor del panel del curandero
    public HealerPanel(Healer healer) {
        this.healer = healer;
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

        energyBar.updateBatteryLevel(healer.getEnergy());
        healthBar.updateBatteryLevel(healer.getHealth());

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
        stylizeButton(healButton, new Color(100, 255, 100));
        stylizeButton(prepareButton, new Color(150, 250, 150));
        stylizeButton(eatButton, new Color(150, 250, 150));
        stylizeButton(accidentButton, new Color(255, 100, 100));
        stylizeButton(illnessButton, new Color(255, 180, 100));

        actionsPanel.add(healButton);
        actionsPanel.add(prepareButton);
        actionsPanel.add(eatButton);
        actionsPanel.add(accidentButton);
        actionsPanel.add(illnessButton);

        // Listeners para los botones
        eatButton.addActionListener(e -> {
            healer.eat();
            energyBar.updateBatteryLevel(healer.getEnergy());
            repaint();
        });

        accidentButton.addActionListener(e -> {
            healer.accident();
            energyBar.updateBatteryLevel(healer.getEnergy());
            healthBar.updateBatteryLevel(healer.getHealth());
            repaint();
        });

        illnessButton.addActionListener(e -> {
            healer.sickness();
            energyBar.updateBatteryLevel(healer.getEnergy());
            healthBar.updateBatteryLevel(healer.getHealth());
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

        this.setPreferredSize(new Dimension(240, 200)); // Ajustamos el tamaño total
    }

    private void stylizeButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(color.darker(), 1, true)); // Borde redondeado
    }

    // Movimiento del curandero
    private void startMove() {
        int deltaX = targetX - healer.getX();
        int deltaY = targetY - healer.getY();

        stepX = deltaX / 50;
        stepY = deltaY / 50;

        moveTimer = new Timer(50, e -> moveStep());
        moveTimer.start();
    }

    private void moveStep() {
        int currentX = healer.getX();
        int currentY = healer.getY();

        if (Math.abs(currentX - targetX) > Math.abs(stepX) || Math.abs(currentY - targetY) > Math.abs(stepY)) {
            healer.setX(currentX + stepX);
            healer.setY(currentY + stepY);
            repaint();
        } else {
            healer.setX(targetX);
            healer.setY(targetY);
            moveTimer.stop();
        }
    }
}
