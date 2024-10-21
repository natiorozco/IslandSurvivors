package utils;

import javax.swing.*;
import java.util.Random;

public class GameEventThread implements Runnable {

    private Random random;
    private boolean running;

    public GameEventThread(MapPanel mapPanel) {
        this.random = new Random();
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(5000 + random.nextInt(5000));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.running = false;
    }
}

