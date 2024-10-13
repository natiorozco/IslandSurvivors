package org.adbbnod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.Scientist;

public class ScientistPanel extends JPanel {
    Scientist scientist;
    JLabel title = new JLabel("CIENTÍFICO");
    JLabel energy = new JLabel("Energía");
    JLabel health = new JLabel("Salud");
    BatteryPanel energyBar = new BatteryPanel();
    BatteryPanel healthBar = new BatteryPanel();
    JTextField x = new JTextField(3);
    JTextField y = new JTextField(3);
    JButton moveButton = new JButton("Mover");
    JButton medicineButton = new JButton("Crear medicamento");
    JButton eatButton = new JButton("Comer");
    JButton accidentButton = new JButton("Accidente");
    JButton illnessButton = new JButton("Enfermedad");

    Timer moveTimer;
    int targetX;
    int targetY;
    int stepX;
    int stepY;

    public ScientistPanel(Scientist scientist) {
        this.scientist = scientist;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        statsPanel.setBackground(Color.WHITE);

        energy.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(energy);
        energyBar.updateBatteryLevel(scientist.getEnergy());
        statsPanel.add(energyBar);

        health.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(health);
        healthBar.updateBatteryLevel(scientist.getHealth());
        statsPanel.add(healthBar);

        JPanel movePanel = new JPanel();
        movePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        movePanel.setBackground(Color.WHITE);
        movePanel.add(new JLabel("X:"));
        movePanel.add(x);
        movePanel.add(new JLabel("Y:"));
        movePanel.add(y);
        movePanel.add(moveButton);

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
        actionsPanel.add(medicineButton);
        actionsPanel.add(eatButton);
        actionsPanel.add(accidentButton);
        actionsPanel.add(illnessButton);

        this.add(statsPanel);
        this.add(movePanel);
        this.add(actionsPanel);

        this.setPreferredSize(new Dimension(200, 130));
    }

    private void startMove() {
        int deltaX = targetX - scientist.getX();
        int deltaY = targetY - scientist.getY();

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
        int currentX = scientist.getX();
        int currentY = scientist.getY();

        if (Math.abs(currentX - targetX) > Math.abs(stepX) || Math.abs(currentY - targetY) > Math.abs(stepY)) {
            scientist.setX(currentX + stepX);
            scientist.setY(currentY + stepY);
            repaint();
        } else {
            scientist.setX(targetX);
            scientist.setY(targetY);
            moveTimer.stop();
        }
    }
}
