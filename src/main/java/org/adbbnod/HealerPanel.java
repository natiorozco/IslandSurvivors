package org.adbbnod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.Healer;

public class HealerPanel extends JPanel {
    Healer healer;
    JLabel title = new JLabel("CURANDERO");
    JLabel energy = new JLabel("Energía");
    JLabel health = new JLabel("Salud");
    BatteryPanel energyBar = new BatteryPanel();
    BatteryPanel healthBar = new BatteryPanel();
    JTextField x = new JTextField(3);
    JTextField y = new JTextField(3);
    JButton moveButton = new JButton("Mover");
    JButton healButton = new JButton("Curar"); //TODO
    JButton prepareButton = new JButton("Preparar remedio"); //TODO
    JButton eatButton = new JButton("Comer");
    JButton accidentButton = new JButton("Accidente");
    JButton illnessButton = new JButton("Enfermedad");

    Timer moveTimer;
    int targetX;
    int targetY;
    int stepX;
    int stepY;

    public HealerPanel(Healer healer) {
        this.healer = healer;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        statsPanel.setBackground(Color.WHITE);

        energy.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(energy);
        energyBar.updateBatteryLevel(healer.getEnergy());
        statsPanel.add(energyBar);

        health.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(health);
        healthBar.updateBatteryLevel(healer.getHealth());
        statsPanel.add(healthBar);

        statsPanel.setPreferredSize(new Dimension(90, 10));

        JPanel movePanel = new JPanel();
        movePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        movePanel.setBackground(Color.WHITE);
        movePanel.add(new JLabel("X:"));
        movePanel.add(x);
        movePanel.add(new JLabel("Y:"));
        movePanel.add(y);
        movePanel.add(moveButton);
        movePanel.setPreferredSize(new Dimension(90, 10));

        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                healer.eat();
                energyBar.updateBatteryLevel(healer.getEnergy());
                repaint();
            }
        });

        accidentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                healer.accident();
                energyBar.updateBatteryLevel(healer.getEnergy());
                healthBar.updateBatteryLevel(healer.getHealth());
                repaint();
            }
        });

        illnessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                healer.sickness();
                energyBar.updateBatteryLevel(healer.getEnergy());
                healthBar.updateBatteryLevel(healer.getHealth());
                repaint();
            }
        });

        moveButton.addActionListener(e -> {
            try {
                targetX = Integer.parseInt(x.getText());
                targetY = Integer.parseInt(y.getText());
                startMove();
            } catch (NumberFormatException ex) {
            }
        });

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.add(healButton);
        actionsPanel.add(prepareButton);
        actionsPanel.add(eatButton);
        actionsPanel.add(accidentButton);
        actionsPanel.add(illnessButton);
        actionsPanel.setPreferredSize(new Dimension(90, 10));

        this.add(statsPanel);
        this.add(movePanel);
        this.add(actionsPanel);

        this.setPreferredSize(new Dimension(200, 80));
    }

    private void startMove() {
        int deltaX = targetX - healer.getX();
        int deltaY = targetY - healer.getY();

        stepX = deltaX / 50;
        stepY = deltaY / 50;

        moveTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveStep();
            }
        });

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
