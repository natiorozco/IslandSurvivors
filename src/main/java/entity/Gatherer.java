package entity;

import org.adbbnod.GamePanel;
import org.adbbnod.InventoryPanel;
import utils.MapPanel;

import java.util.ArrayList;
import java.util.Objects;

public class Gatherer extends Character{
    private int gatheringLevel;

    public Gatherer(GamePanel gp, String path, ArrayList<Resource> mainInventory, InventoryPanel inventoryPanel) {
        super(gp, path, 32, 32, mainInventory, inventoryPanel);
    }

    @Override
    public void act() {

    }

    @Override
    public void eat() {
        for (Resource resource: getInventory()){
            if (Objects.equals(resource.getType(), "vegetal")){
                resource.useResource(resource.getAmount());
                this.increaseEnergy(15);
                this.getInventory().remove(resource);
                inventoryPanel.updateInventory();
                break;
            }

        }

    }

    public void gather(MapPanel map){
        if (this.getEnergy()>=10){
        Resource r = map.resourceHere(this.x,this.y);

        if (r!=null){
        this.getInventory().add(r);
        r.setX(900);
        r.setY(900);

        if (Objects.equals(r.getType(), "vegetal")){
            this.reduceEnergy(10);
        } else if (Objects.equals(r.getType(), "planta medicinal")){
            this.reduceEnergy(15);
        } else if (Objects.equals(r.getType(), "madera")){
            this.reduceEnergy(17);
        } else if (Objects.equals(r.getType(), "piedra")){
            this.reduceEnergy(19);
        } else if(Objects.equals(r.getType(), "liana")){
            this.reduceEnergy(20);
        }}
        inventoryPanel.updateInventory();
    }}

    public void rest(){
        if (this.getShelter()!=null){
            if (this.getShelter().evaluate()>70){
                this.increaseEnergy(15);
                System.out.println("Dormi!");
            }
        }else{
            this.reduceEnergy(10);
        }
    }
}
