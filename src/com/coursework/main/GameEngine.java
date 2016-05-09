package com.coursework.main;

import com.coursework.frames.Canvas;
import com.coursework.gameobjects.GameObject;
import com.coursework.gameobjects.Shuttle;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veniamin Zinevych on 03.05.2016.
 */
public class GameEngine extends JFrame {
    private List<GameObject> gameObjectList;
    private Engine engine;
    private Canvas canvas;
    private Shuttle shuttle;

    public GameEngine(Engine e) {
        super("Asteroids");
        engine = e;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                engine.showMainMenu();
            }
        });

        add(canvas = new Canvas(this));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        start();
    }

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }


    public void start() {
        gameObjectList = new ArrayList<GameObject>();
        shuttle = new Shuttle();
        gameObjectList.add(shuttle);
    }
}
