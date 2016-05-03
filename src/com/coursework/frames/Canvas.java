package com.coursework.frames;

import com.coursework.gameobjects.GameObject;
import com.coursework.main.GameEngine;

import javax.imageio.ImageIO;
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
    private JFrame canvasFrame;
    private GameEngine parent;
    private BufferedImage baskground;

    private LinkedList<GameObject> container = new LinkedList<GameObject>();

    public Canvas(String name, GameEngine engine) {
        parent = engine;

        canvasFrame = new JFrame(name);
        canvasFrame.setUndecorated(true);

        canvasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        canvasFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        canvasFrame.setResizable(false);

        canvasFrame.add(this);

        canvasFrame.setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        try {
            baskground = ImageIO.read(new File("resources\\images\\backgrounds\\canvasMain.jpg"));
        } catch (IOException e) {
            baskground = new BufferedImage(1, 1, 1);
        }
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
