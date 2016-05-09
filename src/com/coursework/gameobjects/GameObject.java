package com.coursework.gameobjects;

import com.coursework.frames.Canvas;
import com.coursework.main.GameEngine;
import com.coursework.util.Vector2d;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public abstract class GameObject {
    protected Vector2d position;
    protected Vector2d velocity;
    protected double radius;
    protected double rotation;
    private int score;
    private boolean alive;

    public GameObject(Vector2d position, Vector2d velocity, double radius, int score) {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.score = score;
        rotation = 0.0;
        alive = true;
    }

    public Vector2d getPosition() {
        return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public double getRadius() {
        return radius;
    }

    public double getRotation() {
        return rotation;
    }

    public int getScore() {
        return score;
    }

    public boolean isAlive() {
        return alive;
    }

    public void rotate(double angle) {
        rotation += angle;
        rotation %= Math.PI * 2;
    }

    public void notAlive() {
        alive = false;
    }

    public void update(Canvas canvas) {
        position.add(velocity);
        if (position.getX() < 0.0) {
            position.setX(position.getX() + canvas.getWidth());
        }
        if (position.getY() < 0.0) {
            position.setY(position.getY() + canvas.getHeight());
        }
        if (position.getX() > canvas.getWidth()) {
            position.setX(position.getX() - canvas.getWidth());
        }
        if (position.getY() > canvas.getHeight()) {
            position.setY(position.getY() - canvas.getHeight());
        }
    }

    public boolean checkCollision(GameObject gameObject) {
        double radius = gameObject.getRadius() + getRadius();
        return (position.getDistanceTo(gameObject.position) < radius);
    }

    public abstract void handleCollision(GameObject gameObject, GameEngine engine);
    public abstract void draw(Graphics2D g, GameEngine gameEngine);
}
