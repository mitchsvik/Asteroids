package com.coursework.main;

import com.coursework.frames.Canvas;
import com.coursework.gameobjects.Shuttle;

/**
 * Created by Veniamin Zinevych on 03.05.2016.
 */
public class GameEngine {
    private Engine engine;
    private Canvas canvas;
    private Shuttle shuttle;

    public GameEngine(Engine e) {
        engine = e;
        canvas = new Canvas(this);

        shuttle = new Shuttle();
        shuttle.setPos(canvas.getWidth()/2, canvas.getHeight()/2);
        canvas.add(shuttle);
    }

    public void frameClosing() {
        engine.showMainMenu();
    }
}
