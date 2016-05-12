package com.coursework.gameobjects;

import com.coursework.frames.Canvas;
import com.coursework.main.GameEngine;
import com.coursework.util.TexturePool;
import com.coursework.util.Vector2d;

import java.awt.*;
import java.util.Random;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Asteroid extends GameObject{
    private AsteroidType type;
    private double rotation;

    public Asteroid(Random random) {
        super(calculatePosition(random), calculateVelocity(random), AsteroidType.Large.getRadius(), AsteroidType.Large.getScore());
        rotation = -0.01 + random.nextDouble()*0.02;
        type = AsteroidType.Large;
    }

    public Asteroid(Asteroid parent, AsteroidType type, Random random) {
        super(new Vector2d(parent.getPosition()), calculateVelocity(random), type.getRadius(), type.getScore());
        rotation = -0.01 + random.nextDouble()*0.02;
        this.type = type;

        for (int i = 0; i < 15; i++) {
            update(null);
        }
    }

    @Override
    public void update(GameEngine gameEngine) {
        super.update(gameEngine);
        rotate(rotation);
    }

    @Override
    public void handleCollision(GameObject gameObject, GameEngine gameEngine) {
        if (gameObject.getClass() == Bullet.class) {
            if (type != AsteroidType.Small) {
                AsteroidType spawnType = AsteroidType.values()[type.ordinal() - 1];
                for(int i = 0; i<2; i++) {
                    gameEngine.addGameObject(new Asteroid(this, spawnType, gameEngine.getRandom()));
                }
            }
            notAlive();
            gameEngine.addScore(type.getScore());
        }
    }

    @Override
    public void draw(Graphics2D g, GameEngine gameEngine) {
        Shape shape = type.getShape(radius);

        g.setColor(Color.WHITE);
        g.setClip(shape);
        g.drawImage(TexturePool.getAsteroidRockTexture(), -(int)radius, -(int)radius, null);
        g.setClip(null);
    }

    private static Vector2d calculatePosition(Random random) {
        Vector2d vector = new Vector2d(Canvas.FIELD_SIZE, Canvas.FIELD_SIZE);
        return vector.add(new Vector2d(random.nextDouble() * Math.PI*2).scale(150 + random.nextDouble() * (Canvas.FIELD_SIZE / 2 - 150)));
    }

    private static Vector2d calculateVelocity(Random random) {
        return new Vector2d(random.nextDouble() * Math.PI * 2).scale(0.75 + random.nextDouble());
    }
}
