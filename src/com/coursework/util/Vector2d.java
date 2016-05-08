package com.coursework.util;

/**
 * 2-dimensional vector.
 * Created by Veniamin Zinevych on 07.05.2016.
 * @author Veniamin Zinevych
 */

public class Vector2d {
    private double x;
    private double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(double angle) {
        this.x = Math.cos(angle);
        this.y = Math.sin(angle);
    }

    public Vector2d(Vector2d vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void scale(double scalar) {
        x *= scalar;
        y *= scalar;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double length = getLength();
        if(length != 0.0 && length != 1.0) {
            x /= length;
            y /= length;
        }
    }

    public double getDistanceTo(Vector2d vector) {
        double dx = x - vector.getX();
        double dy = y - vector.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
