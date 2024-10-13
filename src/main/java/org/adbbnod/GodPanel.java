package org.adbbnod;


import javax.swing.*;
import java.awt.*;

public class GodPanel extends JPanel{
    JLabel title = new JLabel("ACCIONES DE DIOS");
    JTextField x = new JTextField(3);
    JTextField y = new JTextField(3);
    JButton animalButton = new JButton("Enviar animal");
    JButton resourceButton = new JButton("Aparecer recurso");

    JButton disasterButton = new JButton("Tormenta");
    JButton nextDayButton = new JButton("Pasar de día");
    JLabel currentDay = new JLabel("0");

    public GodPanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);


        JPanel appearPanel = new JPanel();
        appearPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        appearPanel.setBackground(Color.WHITE);
        appearPanel.add(new JLabel("X:"));
        appearPanel.add(x);
        appearPanel.add(new JLabel("Y:"));
        appearPanel.add(y);
        appearPanel.add(animalButton);
        appearPanel.add(resourceButton);

        JPanel actions = new JPanel();
        actions.add(disasterButton);
        actions.add(nextDayButton);




        this.add(currentDay);
        this.add(appearPanel);
        this.add(actions);



        this.setPreferredSize(new Dimension(200, 150));
    }




}
