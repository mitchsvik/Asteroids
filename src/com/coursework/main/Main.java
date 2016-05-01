package com.coursework.main;

/**
 * Created by Veniamin Zinevych on 01.05.2016.
 */
public class Main {
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Engine engine = new Engine();
            }
        });
    }
}
