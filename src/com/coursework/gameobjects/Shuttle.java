package com.coursework.gameobjects;

import java.awt.image.BufferedImage;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Shuttle implements GameObject {
    private int radius;
    private int shieldRadius;
    private int life;
    private int energy;
    private BufferedImage texture;

    public Shuttle() {
        radius = 9;
        shieldRadius = radius + 5;
        life = 3;
        energy = 100;
    }

    public int getRadius() {
        return radius;
    }

    public int getShieldRadius() {
        return shieldRadius;
    }

    public int getLife() {
        return life;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isAlive(){
        if (life > 0) {
            return true;
        }
        return false;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage t) {
        texture = t;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Shuttle){
            return true;
        }
        return false;
    }
}
