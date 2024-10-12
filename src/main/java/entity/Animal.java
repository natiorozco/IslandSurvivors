package entity;

public class Animal extends Entity{
    private String type;
    private int strength;
    private int huntDifficulty;
    private int foodGained;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHuntDifficulty() {
        return huntDifficulty;
    }

    public void setHuntDifficulty(int huntDifficulty) {
        this.huntDifficulty = huntDifficulty;
    }

    public int getFoodGained() {
        return foodGained;
    }

    public void setFoodGained(int foodGained) {
        this.foodGained = foodGained;
    }

    public void attack(Character character){

    }

    public void beHunted(Hunter hunter){

    }
}
