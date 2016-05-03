package com.coursework.main;

import com.coursework.frames.Canvas;

/**
 * Created by Veniamin Zinevych on 03.05.2016.
 */
public class GameEngine {
    private Engine engine;
    private Canvas canvas;

    public GameEngine(Engine e) {
        engine = e;
        canvas = new Canvas("Asteroids", this);

    }
}
