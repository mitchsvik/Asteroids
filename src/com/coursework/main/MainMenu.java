package com.coursework.main;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame {
    MainMenu() {
        setDefaultLookAndFeelDecorated(true);
        JFrame f = new JFrame("Asteroids");
        f.setSize(360, 480);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        Font font = new Font("Gost Type B", 0, 48);

        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(300, 105));
        start.setFont(font);
        start.addActionListener(Action -> {
            Canvas c = new Canvas("Asteroids", 1280, 720, f);
            f.setVisible(false);
        });
        p.add(start);

        JButton stats = new JButton("Stats");
        stats.setPreferredSize(new Dimension(300, 105));
        stats.setFont(font);
        stats.addActionListener(Action -> {
            //Stats frame;
        });
        p.add(stats);

        JButton settings = new JButton("Settings");
        settings.setPreferredSize(new Dimension(300, 105));
        settings.setFont(font);
        settings.addActionListener(Action -> {
            //Config frame;
        });
        p.add(settings);

        JButton exit = new JButton("Exit");
        exit.setPreferredSize(new Dimension(300, 105));
        exit.setFont(font);
        exit.addActionListener(Action -> {
            System.exit(0);
        });
        p.add(exit);

        f.add(p);

        f.setVisible(true);
    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainMenu menu = new MainMenu();
            }
        });
    }
}
