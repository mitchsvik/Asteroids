package com.coursework.frames;

import com.coursework.gameobjects.GameObject;
import com.coursework.main.GameEngine;
import com.coursework.util.Vector2d;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Canvas extends JPanel{
    public final static int FIELD_SIZE = 640;

    private GameEngine gameEngine;
    private BufferedImage background;

    public Canvas(GameEngine engine) {
        gameEngine = engine;

        try {
            background = ImageIO.read(new File("resources\\images\\backgrounds\\canvas.jpg"));
        } catch (IOException e) {
            background = new BufferedImage(FIELD_SIZE, FIELD_SIZE, 1);
        }

        setPreferredSize(new Dimension(FIELD_SIZE, FIELD_SIZE));
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(background, 0, 0, null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AffineTransform transform = g2d.getTransform();

        Iterator<GameObject> iterator = gameEngine.getGameObjectList().iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();

            Vector2d position = gameObject.getPosition();

            drawGameObject(g2d, gameObject, position.getX(), position.getY());
        }
    }

    private void drawGameObject(Graphics2D g2d, GameObject gameObject, double x, double y) {
        g2d.translate(x, y);
        double rotation = gameObject.getRotation();
        if (rotation != 0.0) {
            g2d.rotate(gameObject.getRotation());
        }
        gameObject.draw(g2d, gameEngine);
    }
}
