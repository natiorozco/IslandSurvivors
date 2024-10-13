package entity;

public class Resource extends Entity{
    private String type;
    private int amount;

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
