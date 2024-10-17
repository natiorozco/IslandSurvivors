package org.adbbnod;

import entity.Animal;
import entity.Character;
import entity.Resource;
import utils.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.lang.String.valueOf;

public class GodPanel extends JPanel{
    GamePanel gp;
    JLabel title = new JLabel("ACCIONES DE DIOS");
    JTextField x = new JTextField(3);
    JTextField y = new JTextField(3);
    JButton animalButton = new JButton("Enviar animal");
    JButton resourceButton = new JButton("Aparecer recurso");

    JButton disasterButton = new JButton("Tormenta");
    JButton nextDayButton = new JButton("Pasar de día");
    int day = 0;
    boolean storm = false; // 0 regular, 1 tormenta
    JLabel currentDay = new JLabel("Day: "+valueOf(day)+")");
    JLabel weather = new JLabel("("+(storm ? "Tormenta" : "Despejado"));

    Random rand = new Random();
    int rand_int1 = rand.nextInt(4);

    Random rand2 = new Random();
    int rand_int2 = rand2.nextInt(6);

    public GodPanel(Character[] characters, MapPanel map, GamePanel gp) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);


        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        headerPanel.setBackground(Color.WHITE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);


        headerPanel.add(weather);
        headerPanel.add(currentDay);


        JPanel appearPanel = new JPanel();
        appearPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 1));
        appearPanel.setBackground(Color.WHITE);
        appearPanel.add(new JLabel("X:"));
        appearPanel.add(x);
        appearPanel.add(new JLabel("Y:"));
        appearPanel.add(y);
        appearPanel.add(animalButton);
        appearPanel.add(resourceButton);



        JPanel actions = new JPanel();
        actions.add(disasterButton);
        actions.add(nextDayButton);


        this.add(headerPanel);

        this.add(appearPanel);

        this.add(actions);

        this.gp = gp;

        this.setPreferredSize(new Dimension(200, 150));

        nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                day++;
                currentDay.setText("Day: " + valueOf(day)+")");
                repaint();

                for (Character character : characters){
                    if (character.getSickness()==1){
                        character.reduceHealth(10);
                        repaint();
                    } else if(character.getSickness()==2){
                        character.reduceHealth(20);
                        repaint();
                    }
                }

                for (Character character:characters){
                    character.rest();
                    repaint();
                }

            }
        });



        animalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Animal animal= randomAnimal();
                animal.setX(Integer.parseInt(x.getText()));
                animal.setY(Integer.parseInt(y.getText()));

                map.addAnimals(animal);
            }
        });

        resourceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Resource resource= randomResource();
                resource.setX(Integer.parseInt(x.getText()));
                resource.setY(Integer.parseInt(y.getText()));

                map.addResources(resource);
            }
        });
    }

    public Animal randomAnimal(){
        switch (rand.nextInt(4)){
            case 0:
                return (new Animal("Oso", 20, 30, 1, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\animals\\MiniBear_RoseGold.png", this.gp));

            case 1:
                return new Animal("Caballo", 15, 20, 1,"C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\animals\\FarmAnimals.png" ,this.gp);

            case 2:
                return new Animal("Oveja", 10, 10, 1, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\animals\\FarmAnimals.png", this.gp);

            case 3:
                return new Animal("Pollo", 5, 5, 1, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\animals\\FarmAnimals.png",this.gp);

        }

        return null;
    }

    public Resource randomResource(){
        switch (rand2.nextInt(5)){
            case 0:
                return new Resource("vegetal", 1, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\resources\\Food.png", this.gp);
            case 1:
                return new Resource("planta medicinal", 1, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\resources\\plantasMedicinales.png", this.gp);
            case 2:
                return new Resource("madera", 1, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\resources\\liana.png", this.gp);
            case 3:
                return new Resource("piedra",1,"C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\resources\\liana.png",this.gp);
            case 4:
                return new Resource("liana", 1, "C:\\Users\\natal\\Desktop\\sage\\IslandSurvivors\\sprites\\resources\\liana.png",this.gp);
            default:
                return null;
        }
    }
}
