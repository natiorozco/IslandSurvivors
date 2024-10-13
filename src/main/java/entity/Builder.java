package entity;

import org.adbbnod.GamePanel;

import java.util.Objects;

public class Builder extends Character{
    int constructionLevel;

    public Builder(GamePanel gp, String path) {
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

    public Shelter buildShelter(){
        boolean wood = false;
        boolean rope = false;
        boolean stone = false;

        //ver si estan todos los materiales necesarios
        for (Resource resource: this.getInventory()){
            if(Objects.equals(resource.getType(), "madera")){
                wood = true;
            } else if (Objects.equals(resource.getType(), "liana")){
                rope = true;
            } else if (Objects.equals(resource.getType(), "piedra")){
                stone = true;
            }
        }

        //si si pues construye
        if (wood && rope && stone){
            //quito los objetos del inventario porque ya los usó
            for (Resource resource: this.getInventory()){
                if(Objects.equals(resource.getType(), "madera")) {
                    this.getInventory().remove(resource);
                    break;
                }
            }
            for (Resource resource: this.getInventory()){
                if(Objects.equals(resource.getType(), "liana")) {
                    this.getInventory().remove(resource);
                    break;
                }
            }
            for (Resource resource: this.getInventory()){
                if(Objects.equals(resource.getType(), "piedra")) {
                    this.getInventory().remove(resource);
                    break;
                }
            }


            return new Shelter();
        } else{
            return null;
        }

    }

    public void repair(Shelter shelter){
        for (Resource resource: this.getInventory()){
            if(Objects.equals(resource.getType(), "madera") || Objects.equals(resource.getType(), "liana") || Objects.equals(resource.getType(), "piedra")){
                shelter.getRepared();

        }
    }
}
}
