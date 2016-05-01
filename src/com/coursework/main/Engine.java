package com.coursework.main;

/**
 * Created by Veniamin Zinevych on 01.05.2016.
 */
public class Engine {
    private MainMenu menu;
    private Canvas canvas;

    public Engine() {
        createMainMenu();
    }

    public void createMainMenu() {
        menu = new MainMenu(this);
    }

    public void createCanvas() {
        canvas = new Canvas("Asteroids", menu);
    }

    public void createSettings() {
        //TODO
    }

    public void createStats() {
        //TODO
    }

    public void stop() {
        System.exit(0);
    }
}
