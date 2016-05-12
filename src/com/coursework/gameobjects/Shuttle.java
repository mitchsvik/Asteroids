package com.coursework.gameobjects;

import com.coursework.frames.Canvas;
import com.coursework.main.GameEngine;
import com.coursework.util.TexturePool;
import com.coursework.util.Vector2d;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Shuttle extends GameObject {
    private boolean pushPressed;
    private boolean rotateLeftPressed;
    private boolean rotateRightPressed;
    private boolean firePressed;
    private boolean firingEnabled;
    private int consecutiveShots;
    private int fireCooldown;
    private int overheatCooldown;
    private List<Bullet> bullets;

    public Shuttle() {
        super(new Vector2d(Canvas.FIELD_SIZE/2.0, Canvas.FIELD_SIZE/2.0), new Vector2d(0.0, 0.0), 10.0, 0);

        bullets = new LinkedList<Bullet>();
        rotation = -Math.PI / 2;
        pushPressed = false;
        rotateLeftPressed = false;
        rotateRightPressed = false;
        firePressed = false;
        firingEnabled = true;
        fireCooldown = 0;
        overheatCooldown = 0;
    }

    public void setPush(boolean bool) {
        pushPressed = bool;
    }

    public void setRotateLeft(boolean bool) {
        rotateLeftPressed = bool;
    }

    public void setRotateRight(boolean bool) {
        rotateRightPressed = bool;
    }

    public void setFiring(boolean bool) {
        firePressed = bool;
    }

    public void setFiringEnabled(boolean bool) {
        firingEnabled = bool;
    }

    public void reset() {
        rotation = -Math.PI/2;
        position.set(Canvas.FIELD_SIZE/2.0, Canvas.FIELD_SIZE/2.0);
        velocity.set(0.0, 0.0);
        bullets.clear();
        consecutiveShots = 0;
    }

    @Override
    public void update(GameEngine gameEngine) {
        super.update(gameEngine);

        if (rotateLeftPressed != rotateRightPressed) {
            rotate(rotateLeftPressed ? -0.0523 : 0.0523);
        }

        if (pushPressed) {
            velocity.add(new Vector2d(rotation).scale(0.036));
            if (velocity.getLength() > 6.5) {
                velocity.normalize().scale(6.5);
            }
        }

        if (velocity.getLength() > 0.0) {
            velocity.scale(0.995);
        }

        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (!bullet.isAlive()) {
                iterator.remove();
            }
        }

        fireCooldown--;
        overheatCooldown--;
        if (firingEnabled && firePressed && fireCooldown <= 0 && overheatCooldown <= 0) {
            if (bullets.size() < 8) {
                fireCooldown = 6;
                Bullet bullet = new Bullet(this, rotation);
                bullets.add(bullet);
                gameEngine.addGameObject(bullet);
            }

            consecutiveShots++;
            if(consecutiveShots == 8) {
                consecutiveShots = 0;
                overheatCooldown = 30;
            }
        } else if (consecutiveShots > 0) {
            consecutiveShots--;
        }
    }

    @Override
    public void handleCollision(GameObject gameObject, GameEngine gameEngine) {
        if (gameObject.getClass() != Bullet.class) {
            gameEngine.killShuttle();
        }
    }

    @Override
    public void draw(Graphics2D g, GameEngine gameEngine) {
        Shape shape = getShape();

        g.setClip(shape);
        g.drawImage(TexturePool.getShuttleTexture(), -(int)radius, -(int)radius, null);
        g.setClip(null);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(1f));
        g.draw(shape);

        if (pushPressed) {
            shape = getFlameShape();

            g.setClip(shape);
            g.drawImage(TexturePool.getFlameTexture(), -11, -5, null);
        }
    }

    private Shape getShape() {
        GeneralPath p = new GeneralPath();
        p.moveTo(10, 0);
        p.lineTo(-5,-9);
        p.lineTo(-8,-6);
        p.lineTo(-6,-5);
        p.lineTo(-6, 5);
        p.lineTo(-8, 6);
        p.lineTo(-5, 9);
        p.closePath();
        return p;
    }

    private Shape getFlameShape() {
        GeneralPath p = new GeneralPath();
        p.moveTo(-6,-5);
        p.lineTo(-11, 0);
        p.lineTo(-6, 5);
        p.closePath();
        return p;
    }
}
