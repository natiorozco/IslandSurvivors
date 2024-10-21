package entity;

import org.adbbnod.GamePanel;
import org.adbbnod.InventoryPanel;
import utils.MapPanel;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Hunter extends Character{
    private int huntingLevel;


    Random random = new Random();
    int min = 10;
    int max = 20;
    int randomInRange = random.nextInt(max - min + 1) + min;


    public Hunter(GamePanel gp, String path, ArrayList<Resource> mainInventory, InventoryPanel inventoryPanel) {
        super(gp, path, 32, 32, mainInventory, inventoryPanel);
    }

    @Override
    public void act() {

    }

    @Override
    public void eat() {
        for (Resource resource: getInventory()){
            if (Objects.equals(resource.getType(), "carne")){
                resource.useResource(resource.getAmount());
                this.increaseEnergy(20);
                this.getInventory().remove(resource);
                inventoryPanel.updateInventory();
                break;
            }

        }

    }

    public void defend(MapPanel map){
        Animal a = map.animalHere(this.x,this.y);
        a.setX(900);
        a.setY(900);

        if (Objects.equals(a.getType(), "Oso")){
            this.reduceEnergy(25);
            this.reduceHealth(30);

        }
        else if (Objects.equals(a.getType(), "Caballo")){
            this.reduceEnergy(25);
            this.reduceHealth(20);

        }
        else if (Objects.equals(a.getType(), "Oveja")){
            this.reduceEnergy(25);
            this.reduceHealth(15);

        }else if (Objects.equals(a.getType(), "Pollo")){
            this.reduceEnergy(25);
            this.reduceHealth(15);

        }


    }

    public void hunt(MapPanel map){

        Animal a = map.animalHere(this.x,this.y);
        if (a != null) {
            Resource r = new Resource("carne", a.getFoodGained(),"C:/Users/natal/Desktop/sage/IslandSurvivors/sprites/resources/Food.png", gp);
            this.getInventory().add(r);
            inventoryPanel.updateInventory();
            a.setX(900);
            a.setY(900);

            if (Objects.equals(a.getType(), "Oso")){
                this.reduceEnergy(30);
                this.reduceHealth(randomInRange);
                a.beHunted(this);
            }
            else if (Objects.equals(a.getType(), "Caballo")){
                this.reduceEnergy(30);
                this.reduceHealth(randomInRange);
                a.beHunted(this);
            }
            else if (Objects.equals(a.getType(), "Oveja")){
                this.reduceEnergy(20);
                a.beHunted(this);
            }else if (Objects.equals(a.getType(), "Pollo")){
                this.reduceEnergy(20);
                a.beHunted(this);
            }
        }

    }

    //no dice que refugio estable ni seguro, lo validamos o no?
    public void rest(){
        if (this.getShelter()!=null){
            this.increaseEnergy(15);
            System.out.println("Dormi!");
            if (this.getHealth()>90 && this.getHealth()<100){
                this.setHealth(100);
            } if (this.getHealth()<90){
                this.increaseHealth(10);
            }
        }else{
            this.reduceEnergy(10);
        }
    }
}
