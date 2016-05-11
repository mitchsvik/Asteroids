package com.coursework.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Veniamin Zinevych on 11.05.2016.
 */
public class TexturePool {
    private static BufferedImage canvasBackground;
    private static BufferedImage shuttle;
    private static BufferedImage flame;
    private static BufferedImage asteroidRock;
    private static BufferedImage asteroidIce;
    private static BufferedImage asteroidMagma;

    public TexturePool() {}

    public static BufferedImage getCanvasBackgroundTexture() {
        if (canvasBackground == null) {
            try {
                canvasBackground = ImageIO.read(new File("resources\\textures\\canvasBackground.jpg"));
            } catch (IOException e) {
                canvasBackground = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB);
                canvasBackground = drawSimpleMaterialTexture(canvasBackground, Color.BLACK);
            }
        }
        return canvasBackground;
    }

    public static BufferedImage getShuttleTexture() {
        if (shuttle == null) {
            shuttle = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
            shuttle = drawSimpleMaterialTexture(shuttle, Color.WHITE);
        }
        return shuttle;
    }

    public static BufferedImage getFlameTexture() {
        if (flame == null) {
            flame = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
            flame = drawSimpleMaterialTexture(flame, Color.ORANGE);
        }
        return flame;
    }

    public static BufferedImage getAsteroidRockTexture() {
        if (asteroidRock == null) {
            try {
                asteroidRock = ImageIO.read(new File("resources\\textures\\asteroidRock.jpg"));
            } catch (IOException e) {
                asteroidRock = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
                asteroidRock = drawSimpleMaterialTexture(asteroidRock, Color.GRAY);
            }
        }
        return asteroidRock;
    }

    public static BufferedImage getAsteroidIceTexture() {
        if (asteroidIce == null) {
            try {
                asteroidIce = ImageIO.read(new File("resources\\textures\\asteroidIce.jpg"));
            } catch (IOException e) {
                asteroidIce = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
                asteroidIce = drawSimpleMaterialTexture(asteroidIce, Color.blue);
            }
        }
        return asteroidIce;
    }

    public static BufferedImage getAsteroidMagmaTexture() {
        if (asteroidMagma == null) {
            try {
                asteroidMagma = ImageIO.read(new File("resources\\textures\\asteroidMagma.jpg"));
            } catch (IOException e) {
                asteroidMagma = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
                asteroidMagma = drawSimpleMaterialTexture(asteroidMagma, Color.RED);
            }
        }
        return asteroidMagma;
    }

    public static void reset() {
        canvasBackground = null;
        shuttle = null;
        asteroidRock = null;
        asteroidMagma = null;
        asteroidIce = null;
    }

    private static BufferedImage drawSimpleMaterialTexture(BufferedImage image, Color color) {
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        return image;
    }
}
