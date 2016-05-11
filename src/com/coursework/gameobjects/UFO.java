package com.coursework.gameobjects;

import com.coursework.main.GameEngine;
import com.coursework.util.Vector2d;

import java.awt.*;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class UFO extends GameObject {
    public UFO(Vector2d position, Vector2d velocity, double radius, int score) {
        super(position, velocity, radius, score);
    }

    @Override
    public void handleCollision(GameObject gameObject, GameEngine gameEngine) {

    }

    @Override
    public void draw(Graphics2D g, GameEngine gameEngine) {

    }
}
