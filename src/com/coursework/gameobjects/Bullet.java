package com.coursework.gameobjects;

import com.coursework.main.GameEngine;
import com.coursework.util.Vector2d;

import java.awt.*;

/**
 * Created by Veniamin Zinevych on 10.05.2016.
 */
public class Bullet extends GameObject {
    private int lifeCycles;
    public Bullet(GameObject owner, double angle){
        super(new Vector2d(owner.getPosition()), new Vector2d(angle).scale(7.0), 2.0, 0);
        lifeCycles = 60;
    }

    @Override
    public void update(GameEngine gameEngine) {
        super.update(gameEngine);
        lifeCycles--;
        if(lifeCycles < 0) {
            notAlive();
        }
    }

    @Override
    public void handleCollision(GameObject gameObject, GameEngine engine) {
        if(gameObject.getClass() != Shuttle.class) {
            notAlive();
        }
    }

    @Override
    public void draw(Graphics2D g, GameEngine gameEngine) {
        g.setColor(Color.GREEN);
        g.drawOval(-1, -1, 2, 2);
    }
}
