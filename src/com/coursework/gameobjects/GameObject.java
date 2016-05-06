package com.coursework.gameobjects;

import java.awt.image.BufferedImage;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public abstract class GameObject {
    protected int radius;
    private int x_pos, y_pos;
    private BufferedImage texture;

    public void setXPos(int x) {
        x_pos = x;
    }

    public int getXPos() {
        return x_pos;
    }

    public void setYPos(int y) {
        y_pos = y;
    }

    public int getYPos() {
        return y_pos;
    }

    public void setPos(int x, int y) {
        x_pos = x;
        y_pos = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setTexture(BufferedImage t) {
        texture = t;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public abstract boolean equals(Object obj);
}
