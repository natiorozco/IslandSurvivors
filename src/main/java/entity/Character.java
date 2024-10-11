package entity;

import org.adbbnod.GamePanel;
import java.util.ArrayList;

public abstract class Character extends Entity{

    GamePanel gp;

    private String name;
    private int health;
    private int energy;
    private ArrayList<Resource> inventory;
    private Shelter shelter;


    public Player(GamePanel gp){
        this.gp=gp;
        this.health=100;
        this.energy=100;
    }

    public abstract void act();
    public abstract void eat();
    public void rest(){

    };
    public void reduceEnergy(int quantity){
        this.energy-=quantity;
    }

    public void reduceHealth(int quantity){
        this.health-=quantity;
    }

    public void increaseEnergy(int quantity){
        this.energy+=quantity;
    }

    public void increaseHealth(int quantity){
        this.health+=quantity;
    }

    public void shareResource(Player player, Resource resource){
        if (inventory.contains(resource)){
            player.inventory.add(resource);
            this.inventory.remove(resource);
        }
    }

}

