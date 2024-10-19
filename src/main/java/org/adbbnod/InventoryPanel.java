package org.adbbnod;

import entity.Resource;

import javax.swing.*;
import java.util.ArrayList;

public class InventoryPanel extends JPanel {
    private ArrayList<Resource> inventory;

    public InventoryPanel(ArrayList<Resource> inventory) {
        this.inventory = inventory;
        updateInventory();
    }

    public void updateInventory() {
        this.removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Resource resource : inventory) {
            JLabel resourceLabel = new JLabel(resource.getType());
            this.add(resourceLabel);
        }

        this.revalidate();  // Recalcular el diseño
        this.repaint();     // Redibujar el panel
    }
}
