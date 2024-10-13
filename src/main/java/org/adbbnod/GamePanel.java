package org.adbbnod;

import javax.swing.*;
import java.awt.*;
import entity.Character;
import entity.Explorer;

public class GamePanel extends JPanel implements Runnable{
    public Explorer explorer;

    // Global variables:

    int FPS = 60;

    public GamePanel() {
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // Obtener tamaño de pantalla
        //this.setSize(600,600);  // Ajustar el panel al tamaño de la pantalla
        this.setPreferredSize(new Dimension(600,600));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    Thread gameThread;
    //Player player = new Player(this);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

            try {
                double remainingDrawTime = nextDrawTime - System.nanoTime();
                remainingDrawTime = remainingDrawTime/1000000;

                if (remainingDrawTime < 0){
                    remainingDrawTime = 0;
                }

                Thread.sleep((long) remainingDrawTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } {

            }
            // All pending actions.



            // Update information:
            update();
            // Draw screen with updated information:

        }
    }

    public void update(){

    }






}