package entity;

import org.adbbnod.GamePanel;

import java.util.Objects;
import java.util.Random;

public class Hunter extends Character{
    private int huntingLevel;

    Random random = new Random();
    int min = 10;
    int max = 20;
    int randomInRange = random.nextInt(max - min + 1) + min;


    public Hunter(GamePanel gp, String path) {
        super(gp, path, 32, 32);
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
                break;
            }

        }
    }

    public void defend(Character character, Animal animal){
        if(Objects.equals(animal.getType(), "grande")){
            this.reduceEnergy(25);
            this.reduceHealth(30);
            animal.beHunted(this);
        } else if (Objects.equals(animal.getType(),"pequeno")) {

            this.reduceEnergy(25);
            this.reduceHealth(15);
            animal.beHunted(this);
        }
    }

    public void hunt(Animal animal){
        if (Objects.equals(animal.getType(), "grande")){
            this.reduceEnergy(30);
            this.reduceHealth(randomInRange);
            animal.beHunted(this);
        }
        else if (Objects.equals(animal.getType(), "pequeno")){
            this.reduceEnergy(20);
            animal.beHunted(this);
        }

    }

    //no dice que refugio estable ni seguro, lo validamos o no?
    public void rest(){
        if (this.getShelter()!=null){
            this.increaseEnergy(15);
            if (this.getHealth()>90 && this.getHealth()<100){
                this.setHealth(100);
            } if (this.getHealth()<90){
                this.increaseHealth(10);
            }
        }
    }
}
