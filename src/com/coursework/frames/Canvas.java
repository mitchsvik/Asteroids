package com.coursework.frames;

import com.coursework.gameobjects.GameObject;
import com.coursework.main.GameEngine;

import javax.imageio.ImageIO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Canvas extends JPanel{
    private final static int FIELD_SIZE = 640;

    private JFrame canvasFrame;
    private GameEngine gameEngine;
    private BufferedImage baskground;

    private LinkedList<GameObject> container = new LinkedList<GameObject>();

    public Canvas(GameEngine engine) {
        gameEngine = engine;

        try {
            baskground = ImageIO.read(new File("resources\\images\\backgrounds\\canvasMain.jpg"));
        } catch (IOException e) {
            baskground = new BufferedImage(FIELD_SIZE, FIELD_SIZE, 1);
        }

        setPreferredSize(new Dimension(FIELD_SIZE, FIELD_SIZE));

        canvasFrame = new JFrame("Asteroids");

        canvasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        canvasFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                gameEngine.frameClosing();
            }
        });

        canvasFrame.setResizable(false);

        canvasFrame.add(this);

        canvasFrame.pack();
        canvasFrame.setLocationRelativeTo(null);
        canvasFrame.setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(baskground, 0, 0, null);
    }

    @Override
    public void repaint() {

    }

    public void add(GameObject go) {
        container.add(go);
    }

    public void remove(GameObject go) {
        container.remove(go);
    }
}
