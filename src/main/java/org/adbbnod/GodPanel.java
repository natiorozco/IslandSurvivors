package org.adbbnod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

public class GodPanel extends JPanel{
    JLabel title = new JLabel("ACCIONES DE DIOS");
    JTextField x = new JTextField(3);
    JTextField y = new JTextField(3);
    JButton animalButton = new JButton("Enviar animal");
    JButton resourceButton = new JButton("Aparecer recurso");

    JButton disasterButton = new JButton("Tormenta");
    JButton nextDayButton = new JButton("Pasar de día");
    int day = 0;
    boolean storm = false; // 0 regular, 1 tormenta
    JLabel currentDay = new JLabel("Day: "+valueOf(day)+")");
    JLabel weather = new JLabel("("+(storm ? "Tormenta" : "Despejado"));

    public GodPanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);


        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        headerPanel.setBackground(Color.WHITE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);


        headerPanel.add(weather);
        headerPanel.add(currentDay);


        JPanel appearPanel = new JPanel();
        appearPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 1));
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


        this.add(headerPanel);

        this.add(appearPanel);

        this.add(actions);

        this.setPreferredSize(new Dimension(200, 150));

        nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                day++;
                currentDay.setText("Day: " + valueOf(day)+")");
                repaint();


            }
        });
    }
}
