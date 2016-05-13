package com.coursework.frames;

import com.coursework.gameobjects.GameObject;
import com.coursework.main.GameEngine;
import com.coursework.util.TexturePool;
import com.coursework.util.Vector2d;

import java.awt.geom.AffineTransform;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Canvas extends JPanel{
    public final static int FIELD_SIZE = 640;

    private GameEngine gameEngine;

    public Canvas(GameEngine engine) {
        gameEngine = engine;

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(FIELD_SIZE, FIELD_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(TexturePool.getCanvasBackgroundTexture(), 0, 0, null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        g2d.setColor(Color.WHITE);
        AffineTransform transform = g2d.getTransform();

        for (GameObject gameObject: gameEngine.getGameObjectList()) {
            Vector2d position = gameObject.getPosition();

            drawGameObject(g2d, gameObject, position.getX(), position.getY());
            g2d.setTransform(transform);
            drawOtherSide(g2d, gameObject, position.getX(), position.getY(), transform);
        }

        g2d.setColor(Color.WHITE);
        if (!gameEngine.isGameOver()) {
            g.drawString("Score: " + gameEngine.getScore(), 10, 15);
            g2d.translate(15, 30);
            g2d.scale(0.8, 0.8);
            for(int i = 0; i < gameEngine.getLives(); i++) {
                /*g2d.rotate(-Math.PI/2);
                g2d.setStroke(new BasicStroke(1f));
                g2d.draw(gameEngine.getShuttle().getShape());
                g2d.rotate(Math.PI/2);*/
                g2d.drawLine(9, 5, 0, -10);
                g2d.drawLine(9, 5, 6, 8);
                g2d.drawLine(-9, 5, 0, -10);
                g2d.drawLine(-9, 5, -6, 8);
                g2d.drawLine(-6, 8, 6, 8);
                g2d.translate(30, 0);
            }
        }
        g2d.setTransform(transform);

        Font font = new Font("Dialog", Font.PLAIN, 25);
        g2d.setFont(font);
        if (gameEngine.isGameOver()) {
            drawTextCentered("GameOver", g2d, -25);
            drawTextCentered("Final Score: " + gameEngine.getScore(), g2d, 15);
        } else if (gameEngine.isPaused()) {
            drawTextCentered("Pause", g2d, 0);
        } else if (gameEngine.isLevelStarted()) {
            drawTextCentered("Level " + gameEngine.getLevel(), g2d, -20);
        }
    }

    private void drawTextCentered(String text, Graphics g2d, int y) {
        g2d.drawString(text, FIELD_SIZE / 2 - g2d.getFontMetrics().stringWidth(text) / 2,
                FIELD_SIZE / 2 + y);
    }

    private void drawGameObject(Graphics2D g2d, GameObject gameObject, double x, double y) {
        g2d.translate(x, y);
        double rotation = gameObject.getRotation();
        if (rotation != 0.0) {
            g2d.rotate(gameObject.getRotation());
        }
        gameObject.draw(g2d, gameEngine);
    }

    private void drawOtherSide(Graphics2D g2d, GameObject gameObject, double x, double y, AffineTransform transform) {
        double radius = gameObject.getRadius();
        double x_pos = x;
        double y_pos = y;

        if (x < radius) {
            x_pos += FIELD_SIZE;
        } else if (x > (FIELD_SIZE - radius)) {
            x_pos -= FIELD_SIZE;
        }

        if (y < radius) {
            y_pos += FIELD_SIZE;
        } else if (y > (FIELD_SIZE - radius)) {
            y_pos -= FIELD_SIZE;
        }

        if (x_pos != x) {
            drawGameObject(g2d, gameObject, x_pos, y);
            g2d.setTransform(transform);
        }
        if (y_pos != y) {
            drawGameObject(g2d, gameObject, x, y_pos);
            g2d.setTransform(transform);
        }
        if (y_pos != y && x_pos != x) {
            drawGameObject(g2d, gameObject, x_pos, y_pos);
            g2d.setTransform(transform);
        }
    }
}
