package entity;

import org.adbbnod.GamePanel;

import java.util.ArrayList;
import java.util.Objects;
import entity.Resource;
import org.adbbnod.InventoryPanel;
import utils.MapPanel;

public class Healer extends Character{
    int healLevel;

    public Healer(GamePanel gp, String path, ArrayList<Resource> mainInventory, InventoryPanel inventoryPanel) {
        super(gp, path, 32, 32, mainInventory, inventoryPanel);
    }

    @Override
    public void act() {

    }

    @Override
    public void eat() {
        for (Resource resource: getInventory()){
            if (Objects.equals(resource.getType(), "carne") || Objects.equals(resource.getType(), "vegetal")){
                resource.useResource(resource.getAmount());
                if (Objects.equals(resource.getType(), "carne")){
                    this.increaseEnergy(20);
                } else{
                    this.increaseEnergy(10);
                }
                this.getInventory().remove(resource);
                inventoryPanel.updateInventory();
                break;
            }

        }
    }

    public void gather(MapPanel map){
        if (this.getEnergy()>=10){
            Resource r = map.resourceHere(this.x,this.y);

            if (r!=null && Objects.equals(r.getType(),"planta medicinal")){
                this.getInventory().add(r);
                r.setX(900);
                r.setY(900);

               this.reduceEnergy(10);
            inventoryPanel.updateInventory();
        }

    }}


    public void heal(MapPanel map){
        Character c = map.characterToHealHere(this.x ,this.y);

        if (c==null) {
            c = this;
        }

            for (Resource resource : getInventory()) {
                if (Objects.equals(resource.getType(), "medicina")) {
                    c.increaseHealth(20);
                    c.setSickness(0);
                    this.getInventory().remove(resource);
                    inventoryPanel.updateInventory();
                    break;
                }
            }

            if (this.getEnergy()>=15) {
                for (Resource resource : getInventory()) {
                    if (Objects.equals(resource.getType(), "planta medicinal")) {

                        this.getInventory().remove(resource);
                        inventoryPanel.updateInventory();
                        if (c.getHealth() <= 80) {
                            c.increaseHealth(20);
                            c.setSickness(0);
                        } else if (c.getHealth() > 80) {
                            c.setHealth(100);
                            c.setSickness(0);
                        }

                        this.reduceEnergy(15);

                        break;
                    }
                }
            }

    }

    public void rest (){
        if (this.getShelter()!=null){
            this.increaseEnergy(15);
            System.out.println("Dormi!");
        }else{
            this.reduceEnergy(10);
        }
    }
}
