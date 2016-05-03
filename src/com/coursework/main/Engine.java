package com.coursework.main;

import com.coursework.frames.*;

/**
 * Created by Veniamin Zinevych on 01.05.2016.
 */
public class Engine {
    private MainMenu menu;
    private GameEngine gameEngine;
    private SettingsMenu settingsMenu;
    private StatsMenu statsMenu;

    public Engine() {
        menu = new MainMenu(this);
    }

    public void startGame() {
        menu.setVisible(false);
        gameEngine = new GameEngine(this);
    }

    public void showMainMenu() {
        menu.setVisible(true);
    }

    public void showSettings() {
        if (settingsMenu == null)
            settingsMenu = new SettingsMenu();
        settingsMenu.setVisible(true);
    }

    public void showStats() {
        if (statsMenu == null)
            statsMenu = new StatsMenu();
        statsMenu.setVisible(true);
    }

    public void stop() {
        System.exit(0);
    }
}
