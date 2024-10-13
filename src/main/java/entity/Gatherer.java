package entity;

import org.adbbnod.GamePanel;

import java.util.Objects;

public class Gatherer extends Character{
    private int gatheringLevel;

    public Gatherer(GamePanel gp, String path) {
        super(gp, path, 32, 32);
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
                break;
            }

        }

    }

    public void gather(Resource resource){
        if (Objects.equals(resource.getType(), "vegetal")){
            this.reduceEnergy(10);
        } else if (Objects.equals(resource.getType(), "planta medicinal")){
            this.reduceEnergy(15);
        } else if (Objects.equals(resource.getType(), "madera")){
            this.reduceEnergy(17);
        } else if (Objects.equals(resource.getType(), "piedra")){
            this.reduceEnergy(19);
        } else if(Objects.equals(resource.getType(), "liana")){
            this.reduceEnergy(20);
        }
    }

    public void rest(){
        if (this.getShelter()!=null){
            if (this.getShelter().evaluate()>70){
                this.increaseEnergy(15);
            }
        }
    }
}
