package entity;

import org.adbbnod.GamePanel;

import java.util.ArrayList;
import java.util.Objects;

public class Scientist extends Character{
    int scienceLevel;
    
    public Scientist(GamePanel gp, String path, ArrayList<Resource> mainInventory) {
        super(gp, path, 32, 32, mainInventory);
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
                Resource medicine = new Resource("planta medicinal", 1, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\resources\\plantasMedicinales.png", this.gp);
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
