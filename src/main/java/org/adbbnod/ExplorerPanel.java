package org.adbbnod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.Explorer;

public class ExplorerPanel extends JPanel {
    Explorer explorer;
    JLabel title = new JLabel("EXPLORADOR");
    JLabel energy = new JLabel("Energía");
    JLabel health = new JLabel("Salud");
    BatteryPanel energyBar = new BatteryPanel();
    BatteryPanel healthBar = new BatteryPanel();
    JTextField x = new JTextField(3);
    JTextField y = new JTextField(3);
    JButton moveButton = new JButton("Mover");
    Timer moveTimer;
    JButton eatButton = new JButton("Comer");
    JButton exploreButton = new JButton("Explorar");
    JButton accidentButton = new JButton("Accidente");
    JButton illnessButton = new JButton("Enfermedad");
    JButton gatherButton = new JButton("Recolectar");

    int targetX;
    int targetY;
    int stepX;
    int stepY;

    public ExplorerPanel(Explorer explorer) {
        this.explorer = explorer;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        statsPanel.setBackground(Color.WHITE);

        energy.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(energy);
        energyBar.updateBatteryLevel(explorer.getEnergy());
        statsPanel.add(energyBar);

        health.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(health);
        healthBar.updateBatteryLevel(explorer.getHealth());
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
        actionsPanel.add(exploreButton);
        actionsPanel.add(eatButton);
        actionsPanel.add(accidentButton);
        actionsPanel.add(illnessButton);
        actionsPanel.add(gatherButton);

        this.add(statsPanel);
        this.add(movePanel);
        this.add(actionsPanel);

        this.setPreferredSize(new Dimension(200, 130));
    }

    private void startMove() {
        int deltaX = targetX - explorer.getX();
        int deltaY = targetY - explorer.getY();

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
        int currentX = explorer.getX();
        int currentY = explorer.getY();

        if (Math.abs(currentX - targetX) > Math.abs(stepX) || Math.abs(currentY - targetY) > Math.abs(stepY)) {
            explorer.setX(currentX + stepX);
            explorer.setY(currentY + stepY);
            repaint();
        } else {
            explorer.setX(targetX);
            explorer.setY(targetY);
            moveTimer.stop();
        }
    }
}
