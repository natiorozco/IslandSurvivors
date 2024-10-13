package entity;

import org.adbbnod.GamePanel;

import java.util.Objects;

public class Scientist extends Character{
    int scienceLevel;
    
    public Scientist(GamePanel gp, String path) {
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
    
    public void createMedicine(){
        for (Resource resource: this.getInventory()){
            if (Objects.equals(resource.getType(), "planta medicinal")){
                Resource medicine = new Resource();
                medicine.setType("medicina");
                this.getInventory().add(medicine);
                this.getInventory().remove(resource);
                this.reduceEnergy(15);
                break;
            }
        }
    }

    public void rest(){
        if (this.getShelter()!=null){
            this.increaseEnergy(15);
        }
    }

}
