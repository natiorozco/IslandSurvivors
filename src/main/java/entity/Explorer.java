package entity;

import org.adbbnod.GamePanel;
import org.adbbnod.InventoryPanel;
import utils.MapPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Explorer extends Character{
    private int explorationLevel;

    Random random = new Random();
    int min = 15;
    int max = 30;
    int randomInRange = random.nextInt(max - min + 1) + min;


    public Explorer(GamePanel gp, String path, ArrayList<Resource> mainInventory, InventoryPanel inventoryPanel) {
        super(gp, path, 32, 32, mainInventory, inventoryPanel);
        this.x=10;
        this.y=10;
        explorationLevel=0;
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

    public void explore(MapPanel map){
        if (this.getEnergy()>=15){
        map.revealTile(this.getX()+1,this.getY());
        map.revealTile(this.getX()+1,this.getY()+1);
        map.revealTile(this.getX(),this.getY()+1);
        map.revealTile(this.getX()-1,this.getY()+1);
        map.revealTile(this.getX()-1,this.getY());
        map.revealTile(this.getX()-1,this.getY()-1);
        map.revealTile(this.getX(),this.getY()-1);
        map.revealTile(this.getX()+1,this.getY()-1);
        this.reduceEnergy(randomInRange);
    }}

    public void gather(MapPanel map){
        if (this.getEnergy()>=5){
        Resource r = map.resourceHere(this.x,this.y);
        if (r!=null){
        this.reduceEnergy(5);
        this.getInventory().add(r);
        r.setX(900);
        r.setY(900);
        inventoryPanel.updateInventory();}}
    }

    public void rest(){
        if (this.getShelter()!=null){
            if (this.getShelter().evaluate()>70){
                this.increaseEnergy(20);
                System.out.println("Dormi!");
            }
        }else{
            this.reduceEnergy(10);
        }
    }

}
