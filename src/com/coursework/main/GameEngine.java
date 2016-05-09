package com.coursework.main;

import com.coursework.frames.Canvas;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Veniamin Zinevych on 03.05.2016.
 */
public class GameEngine extends JFrame {
    private Engine engine;
    private Canvas canvas;

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
    }
}
