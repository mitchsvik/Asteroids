package com.coursework.gameobjects;

import com.coursework.frames.Canvas;
import com.coursework.main.GameEngine;
import com.coursework.util.Vector2d;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Random;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Asteroid extends GameObject{
    private int size;
    private double rotation;

    public Asteroid(Random random) {
        super(calculatePosition(random), calculateVelocity(random), 40, 25);
        rotation = -0.01 + random.nextDouble()*0.02;
        size = 3;
    }

    public Asteroid(Asteroid parent, int size, Random random) {
        super(parent.getPosition(), calculateVelocity(random), 40/(2*(2-size)+5), parent.getScore()*2);
        rotation = -0.01 + random.nextDouble()*0.02;
        this.size = size;
    }

    @Override
    public void update(GameEngine gameEngine) {
        super.update(gameEngine);
        rotate(rotation);
    }

    @Override
    public void handleCollision(GameObject gameObject, GameEngine gameEngine) {
        if (gameObject.getClass() != Asteroid.class) {
            if (size != 1) {
                int spawnSize = size - 1;
                for(int i = 0; i<2; i++) {
                    gameEngine.addGameObject(new Asteroid(this, spawnSize, gameEngine.getRandom()));
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g, GameEngine gameEngine) {
        Shape shape = getShape();

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2f));
        g.draw(shape);
    }

    private static Vector2d calculatePosition(Random random) {
        Vector2d vector = new Vector2d(Canvas.FIELD_SIZE, Canvas.FIELD_SIZE);
        return vector.add(new Vector2d(random.nextDouble() * Math.PI*2).scale(150 + random.nextDouble() * (Canvas.FIELD_SIZE / 2 - 150)));
    }

    private static Vector2d calculateVelocity(Random random) {
        return new Vector2d(random.nextDouble() * Math.PI * 2).scale(0.75 + random.nextDouble());
    }

    private Shape getShape() {
        double x, y;
        double angleDelta = Math.PI*2 / (size*4);
        GeneralPath p = new GeneralPath();
        p.moveTo(radius, 0);
        for(int i = 1; i < size*4; i++) {
            x = Math.cos(angleDelta*i)*radius;
            y = Math.sin(angleDelta*i)*radius;
            p.lineTo(x,y);
        }
        p.closePath();
        return p;
    }
}
