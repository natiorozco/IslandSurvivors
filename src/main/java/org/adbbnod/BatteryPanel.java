package org.adbbnod;

import javax.swing.*;
import java.awt.*;

public class BatteryPanel extends JPanel {
    private JProgressBar batteryBar;
    private JLabel batteryIcon;

    public BatteryPanel() {
        this.setLayout(new FlowLayout());


        batteryBar = new JProgressBar(0, 100);
        batteryBar.setValue(75); // Valor inicial del porcentaje de energía
        batteryBar.setStringPainted(true); // Mostrar el porcentaje en la barra
        batteryBar.setPreferredSize(new Dimension(100, 25));


        this.add(batteryBar);
    }


    public void updateBatteryLevel(int percentage) {
        batteryBar.setValue(percentage);
    }


}
