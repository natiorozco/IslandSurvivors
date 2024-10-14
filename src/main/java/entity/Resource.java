package entity;

import org.adbbnod.GamePanel;

public class Resource extends Entity{
    private String type;
    private int amount;
    private String path;
    private GamePanel gp;


    public Resource(String type, int amount, String path, GamePanel gp){
        this.type=type;
        this.amount=amount;
        this.path = path;
        this.gp=gp;
        loadSprite(path, 16, 16);
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void useResource(int quantity){
        if (quantity<=amount){
            amount-=quantity;
        }
    }

    public void addResource(int quantity){
        amount+=quantity;
    }
}
