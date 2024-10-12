package entity;

public class Resource extends Entity{
    private String type;
    private int amount;

    public void useResource(int quantity){
        if (quantity<=amount){
            amount-=quantity;
        }
    }

    public void addResource(int quantity){
        amount+=quantity;
    }
}
