package com.coursework.gameobjects;

import com.coursework.main.GameEngine;
import com.coursework.util.Vector2d;

import java.awt.*;

/**
 * Created by Veniamin Zinevych on 11.05.2016.
 */
public class LargeUFO extends GameObject {

    public LargeUFO(Vector2d position, Vector2d velocity, double radius, int score) {
        super(position, velocity, radius, score);
    }

    @Override
    public void handleCollision(GameObject gameObject, GameEngine engine) {

    }

    @Override
    public void draw(Graphics2D g, GameEngine gameEngine) {

    }
}
