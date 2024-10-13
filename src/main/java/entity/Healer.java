package entity;

import org.adbbnod.GamePanel;

import java.util.Objects;
import entity.Resource;

public class Healer extends Character{
    int healLevel;

    public Healer(GamePanel gp, String path) {
        super(gp, path, 32, 32);
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
                break;
            }

        }
    }


    public void heal(Character character){

        for (Resource resource: getInventory()){
            if (Objects.equals(resource.getType(),"medicina")){
                character.increaseHealth(20);
                character.setSickness(0);
                this.getInventory().remove(resource);
                return;
            }
        }

        for (Resource resource: getInventory()){
            if (Objects.equals(resource.getType(), "planta medicinal")){

                this.getInventory().remove(resource);
                if (character.getHealth()<=80){
                character.increaseHealth(20);
                character.setSickness(0);}
                else if (character.getHealth()>80){
                    character.setHealth(100);
                    character.setSickness(0);
                }

                this.reduceEnergy(15);

                break;
            }
        }


    }

    public void rest (){
        if (this.getShelter()!=null){
            this.increaseEnergy(15);
        }
    }
}
