package entity;

//import jdk.internal.loader.Resource;
import org.adbbnod.GamePanel;
import java.util.ArrayList;

public abstract class Character extends Entity{

    GamePanel gp;

    private String name;
    private int health;
    private int energy;
    private ArrayList<Resource> inventory;
    private Shelter shelter;
    private int sickness; //0 para no enfermeadad, 1 enfermedad light y 2 enfermedad grave


    public Character(GamePanel gp){
        this.gp=gp;
        this.health=100;
        this.energy=100;
        this.inventory=new ArrayList<Resource>();
    }

    public int getSickness() {
        return sickness;
    }

    public void setSickness(int sickness) {
        this.sickness = sickness;
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

    public Shelter getShelter() {
        return shelter;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public abstract void act();
    public abstract void eat();
    public void rest(){

    };
    public void reduceEnergy(int quantity){
        if ((this.getEnergy()-quantity) <= 0){
            this.energy=0;
        } else{
        this.energy-=quantity;}
    }

    public void reduceHealth(int quantity){
        if ((this.getHealth()-quantity) <= 0){
            this.health=0;
        } else{
            this.health-=quantity;}
    }

    public void increaseEnergy(int quantity){
        if ((this.getEnergy()+quantity)>=90){
            this.energy=100;
        } else{
        this.energy+=quantity;}
    }

    public void increaseHealth(int quantity){
        if ((this.getHealth()+quantity)>=90){
            this.health=100;
        } else{
            this.health+=quantity;}
    }

    public void shareResource(Character Character, Resource resource){
        if (inventory.contains(resource)){
            Character.inventory.add(resource);
            this.inventory.remove(resource);
        }
    }

    public ArrayList<Resource> getInventory() {
        return inventory;
    }

    //accidnete y enfermeadd
}

