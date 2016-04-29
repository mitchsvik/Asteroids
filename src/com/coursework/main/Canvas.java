package com.coursework.main;

import com.coursework.gameobjects.GameObject;

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
    private JFrame parent;

    private LinkedList<GameObject> container = new LinkedList<GameObject>();

    public Canvas(String name, JFrame m) {
        parent = m;

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
        BufferedImage image;
        try {
            image = ImageIO.read(new File("resources\\images\\backgrounds\\canvasMain.jpg"));
            g.drawImage(image, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void repaint() {

    }

    public void addElement(GameObject go) {

    }

    public void removeElement(GameObject go) {
            container.remove(go);
    }
}
