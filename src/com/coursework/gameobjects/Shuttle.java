package com.coursework.gameobjects;

import com.coursework.frames.Canvas;
import com.coursework.util.Vector2d;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Shuttle extends GameObject {
    public Shuttle(Vector2d position, Vector2d velocity, double radius, int score) {
        super(position, velocity, radius, score);
    }

    @Override
    public void handleCollision(GameObject gameObject, Canvas canvas) {

    }

    @Override
    public void draw(Graphics2D g, Canvas canvas) {

    }
}
