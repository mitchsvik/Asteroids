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

        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(300, 105));
        start.setLocation(30,30);
        p.add(start);

        JButton stats = new JButton("Stats");
        stats.setPreferredSize(new Dimension(300, 105));
        p.add(stats);

        JButton settings = new JButton("Settings");
        settings.setPreferredSize(new Dimension(300, 105));
        p.add(settings);

        JButton exit = new JButton("Start");
        exit.setPreferredSize(new Dimension(300, 105));
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
