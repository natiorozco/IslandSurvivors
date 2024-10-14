package entity;

import org.adbbnod.GamePanel;
import utils.MapPanel;

import java.util.ArrayList;
import java.util.Objects;

public class Gatherer extends Character{
    private int gatheringLevel;

    public Gatherer(GamePanel gp, String path, ArrayList<Resource> mainInventory) {
        super(gp, path, 32, 32, mainInventory);
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

    public void gather(MapPanel map){
        Resource r = map.resourceHere(this.x,this.y);
        this.reduceEnergy(5);
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
