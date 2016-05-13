package com.coursework.frames;

import com.coursework.main.Engine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Veniamin Zinevych on 02.05.2016.
 */
public class StatsMenu extends JFrame {
    public StatsMenu(Engine e) {
        setSize(360, 480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(e.getMainMenu().getX() - 354, e.getMainMenu().getY());
        setTitle("High scores");

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        Font font = new Font("Open Sans", 0, 24);

        for(String record: e.getHighScores()) {
            JLabel label = new JLabel(record);
            label.setPreferredSize(new Dimension(300, 39));
            label.setFont(font);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            p.add(label);
        }

        add(p);

        setVisible(true);
    }


}
