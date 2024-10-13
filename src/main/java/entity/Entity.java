package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public BufferedImage sprite;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void move(int targetX, int targetY) {
        int deltaX = targetX - this.x;
        int deltaY = targetY - this.y;

        // Calcular el paso, puede ajustarse según la velocidad deseada
        int stepSize = 1; // Tamaño del paso por cada tick del temporizador
        if (Math.abs(deltaX) > stepSize) {
            this.x += (deltaX > 0) ? stepSize : -stepSize; // Mover en la dirección de X
        } else {
            this.x = targetX; // Ajustar para llegar a la posición final
        }

        if (Math.abs(deltaY) > stepSize) {
            this.y += (deltaY > 0) ? stepSize : -stepSize; // Mover en la dirección de Y
        } else {
            this.y = targetY; // Ajustar para llegar a la posición final
        }
    }


}
