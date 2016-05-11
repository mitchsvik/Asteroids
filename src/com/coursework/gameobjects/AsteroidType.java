package com.coursework.gameobjects;

import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * Created by Veniamin Zinevych on 11.05.2016.
 */
public enum  AsteroidType {
    Small(15.0, 100),
    Medium(25.0, 100),
    Large(40.0, 100);

    private final Shape shape;
    private final double radius;
    private final int score;

    private AsteroidType(double radius, int score) {
        shape = getShape(radius);
        this.radius = radius;
        this.score = score;
    }

    public Shape getShape(double radius) {
        double x, y;
        int size = (int)(radius / 10);
        double angleDelta = Math.PI*2 / (size*5);

        GeneralPath p = new GeneralPath();
        p.moveTo(radius, 0);
        for(int i = 1; i < size*5; i++) {
            x = Math.cos(angleDelta*i)*radius;
            y = Math.sin(angleDelta*i)*radius;
            p.lineTo(x,y);
        }
        p.closePath();
        return p;
    }

    public Shape getShape() {
        return shape;
    }

    public double getRadius() {
        return radius;
    }

    public int getScore() {
        return score;
    }
}
