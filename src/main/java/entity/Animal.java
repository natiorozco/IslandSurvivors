package entity;

import org.adbbnod.GamePanel;

public class Animal extends Entity{
    private String type;
    private int strength;
    private int huntDifficulty;
    private int foodGained;
    private String path;
    private GamePanel gp;

    public Animal(String type, int strength, int huntDifficulty, int foodGained, String path, GamePanel gp) {
        this.type = type;
        this.strength = strength;
        this.huntDifficulty = huntDifficulty;
        this.foodGained = foodGained;
        this.path=path;
        loadSprite(path, 32, 32);
        this.gp = gp;
    }

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
        character.reduceHealth(this.strength);
    }

    public void beHunted(Hunter hunter){
            this.type="cazado";
    }
}
