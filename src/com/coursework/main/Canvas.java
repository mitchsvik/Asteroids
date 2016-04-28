package com.coursework.main;

import com.coursework.gameobjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Canvas extends JPanel{
    private LinkedList<GameObject> container = new LinkedList<GameObject>();
    private JFrame parrent;
    WindowListener w = new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            parrent.setVisible(true);
        }
    };

    public Canvas(String name, int width, int height, JFrame m) {
        parrent = m;
        JFrame canvasFrame = new JFrame(name);
        canvasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        canvasFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        canvasFrame.setUndecorated(true);
        canvasFrame.setSize(width, height);
        canvasFrame.add(this);
        canvasFrame.setVisible(true);
        canvasFrame.addWindowListener(w);

    }



    @Override
    public void paint(Graphics g){

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
