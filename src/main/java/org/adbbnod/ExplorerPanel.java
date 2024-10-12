package org.adbbnod;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
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
    JButton eatButton = new JButton("Comer");
    JButton exploreButton = new JButton("Explorar");
    JButton accidentButton = new JButton("Accidente");
    JButton illnessButton = new JButton("Enfermedad");



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

        JPanel actions = new JPanel();
        actions.add(exploreButton);
        actions.add(eatButton);
        actions.add(accidentButton);
        actions.add(illnessButton);



        this.add(statsPanel);
        this.add(movePanel);
        this.add(actions);



        this.setPreferredSize(new Dimension(200, 130));
    }
}
