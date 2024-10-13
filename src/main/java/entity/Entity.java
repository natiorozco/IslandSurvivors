package entity;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Entity {
    public int x, y;
    public int speed;
    public BufferedImage sprite;
    public BufferedImage[] sprites;


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

    public void loadSprite(String spritePath, int spriteWidth, int spriteHeight) {
        try {
            // Cargar el tileset
            System.out.println(spritePath);
            BufferedImage tileset = ImageIO.read(new File(spritePath));


            // Calcular el número de filas y columnas en el tileset
            int cols = tileset.getWidth() / spriteWidth;
            int rows = tileset.getHeight() / spriteHeight;

            sprites = new BufferedImage[cols * rows]; // Crear el array para los sprites

            // Extraer cada sprite del tileset
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    sprites[row * cols + col] = tileset.getSubimage(
                            col * spriteWidth,
                            row * spriteHeight,
                            spriteWidth,
                            spriteHeight
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int index) {
        if (index >= 0 && index < sprites.length) {
            return sprites[index]; // Retornar el sprite en el índice especificado
        }
        return null; // O manejar el caso de índice inválido
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
