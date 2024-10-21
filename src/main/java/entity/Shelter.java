package entity;

import java.util.ArrayList;

public class Shelter extends Entity{

    private int stability;
    private int capacity;
    private ArrayList<Character> characters;

    public Shelter(String path){
        stability = 100;
        capacity = 2;
        characters = new ArrayList<>();
        loadSprite(path, 32, 32);
    }

    public void addCharacter(Character character){
        if (characters.size()<capacity){
            characters.add(character);
            character.setShelter(this);
        }
    }

    public void deleteCharacter(Character character){
        if (characters.contains(character)){
            characters.remove(character);
            character.setShelter(null);
        }
    }

    public int evaluate(){
        return stability;
    }

    public void getRepared(){
        System.out.println("Fui reparado");
        this.stability=100;
    }

    public int getCapacity(){
        return this.capacity;
    }
    public void decreaseCapacity(){
        this.capacity--;
    }

    public boolean decreaseStability(int amount){
        if (!(stability-amount<0)){
            this.stability -= amount;
            return true;
        }
        return false;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
}
