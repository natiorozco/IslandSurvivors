package entity;

import java.util.ArrayList;

public class Shelter extends Entity{

    private int stability;
    private int capacity;
    private ArrayList<Resource> materials;
    private ArrayList<Character> characters;

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

    private int evaluate(){
        return stability;
    }

    //repare
}
