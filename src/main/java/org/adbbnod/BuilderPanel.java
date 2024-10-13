package org.adbbnod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.Builder;

public class BuilderPanel extends JPanel {
    Builder builder;
    JLabel title = new JLabel("CONSTRUCTOR");
    JLabel energy = new JLabel("Energía");
    JLabel health = new JLabel("Salud");
    BatteryPanel energyBar = new BatteryPanel();
    BatteryPanel healthBar = new BatteryPanel();
    JTextField x = new JTextField(3);
    JTextField y = new JTextField(3);
    JButton moveButton = new JButton("Mover");
    JButton buildButton = new JButton("Construir");
    JButton repairButton = new JButton("Reparar");
    JButton eatButton = new JButton("Comer");
    JButton accidentButton = new JButton("Accidente");
    JButton illnessButton = new JButton("Enfermedad");

    Timer moveTimer;


    int targetX;
    int targetY;
    int stepX;
    int stepY;

    public BuilderPanel(Builder builder) {
        this.builder = builder;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);


        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        statsPanel.setBackground(Color.WHITE);

        energy.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(energy);
        energyBar.updateBatteryLevel(builder.getEnergy());
        statsPanel.add(energyBar);

        health.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(health);
        healthBar.updateBatteryLevel(builder.getHealth());
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
                System.out.println("Por favor ingrese valores numéricos válidos para X e Y.");
            }
        });


        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.add(buildButton);
        actionsPanel.add(repairButton);
        actionsPanel.add(eatButton);
        actionsPanel.add(accidentButton);
        actionsPanel.add(illnessButton);


        this.add(statsPanel);
        this.add(movePanel);
        this.add(actionsPanel);

        this.setPreferredSize(new Dimension(200, 130));
    }


    private void startMove() {

        int deltaX = targetX - builder.getX();
        int deltaY = targetY - builder.getY();


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
        int currentX = builder.getX();
        int currentY = builder.getY();


        if (Math.abs(currentX - targetX) > Math.abs(stepX) || Math.abs(currentY - targetY) > Math.abs(stepY)) {
            builder.setX(currentX + stepX);
            builder.setY(currentY + stepY);
            System.out.println("Moviendo a X: " + builder.getX() + ", Y: " + builder.getY());
            repaint();
        } else {

            builder.setX(targetX);
            builder.setY(targetY);
            moveTimer.stop();
            System.out.println("Builder ha llegado a la posición X: " + builder.getX() + ", Y: " + builder.getY());
        }
    }
}
