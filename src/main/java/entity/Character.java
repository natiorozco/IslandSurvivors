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


    public Character(GamePanel gp){
        this.gp=gp;
        this.health=100;
        this.energy=100;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
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

    public void shareResource(Character character, Resource resource){
        if (inventory.contains(resource)){
            character.inventory.add(resource);
            this.inventory.remove(resource);
        }
    }

}

