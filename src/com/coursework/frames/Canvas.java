package com.coursework.frames;

import com.coursework.main.GameEngine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Canvas extends JPanel{
    private final static int FIELD_SIZE = 640;

    private GameEngine gameEngine;
    private BufferedImage background;

    public Canvas(GameEngine engine) {
        gameEngine = engine;

        try {
            background = ImageIO.read(new File("resources\\images\\backgrounds\\canvas.jpg"));
        } catch (IOException e) {
            background = new BufferedImage(FIELD_SIZE, FIELD_SIZE, 1);
        }

        setPreferredSize(new Dimension(FIELD_SIZE, FIELD_SIZE));
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(background, 0, 0, null);
    }
}
