package com.coursework.main;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame {
    private Engine engine;

    MainMenu(Engine e) {
        engine = e;

        setSize(360, 480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        Font font = new Font("Gost Type B", 0, 48);

        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(300, 105));
        start.setFont(font);
        start.addActionListener(Action -> {
            e.createCanvas();
            setVisible(false);
        });
        p.add(start);

        JButton stats = new JButton("Stats");
        stats.setPreferredSize(new Dimension(300, 105));
        stats.setFont(font);
        stats.addActionListener(Action -> {
            e.createStats();
        });
        p.add(stats);

        JButton settings = new JButton("Settings");
        settings.setPreferredSize(new Dimension(300, 105));
        settings.setFont(font);
        settings.addActionListener(Action -> {
            e.createSettings();
        });
        p.add(settings);

        JButton exit = new JButton("Exit");
        exit.setPreferredSize(new Dimension(300, 105));
        exit.setFont(font);
        exit.addActionListener(Action -> {
            e.stop();
        });
        p.add(exit);

        add(p);

        setVisible(true);
    }
}
