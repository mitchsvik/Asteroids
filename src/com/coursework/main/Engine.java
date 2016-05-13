package com.coursework.main;

import com.coursework.frames.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Veniamin Zinevych on 01.05.2016.
 */
public class Engine {
    private MainMenu menu;
    private GameEngine gameEngine;
    private SettingsMenu settingsMenu;
    private StatsMenu statsMenu;
    private List<Record> highScores;

    public Engine() {
        menu = new MainMenu(this);
        highScores = loadHighScoresFromFile();
    }

    public void startGame() {
        menu.setVisible(false);
        if (settingsMenu != null) {
            settingsMenu.setVisible(false);
        }
        if (statsMenu != null) {
            statsMenu.setVisible(false);
        }
        if (gameEngine == null) {
            gameEngine = new GameEngine(this);
        }
        gameEngine.startGame();
    }

    public JFrame getMainMenu() {
        return menu;
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
            statsMenu = new StatsMenu(this);
        statsMenu.setVisible(true);
    }

    public void stop() {
        saveHighScore();
        System.exit(0);
    }

    private List<Record> loadHighScoresFromFile() {
        List<Record> output = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources\\HighScores.dat"))){
            while (reader.ready()) {
                output.add(new Record(reader.readLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            output = null;
        }

        if (output == null || output.size() < 10) {
            output = generateDefault();
        }

        return output;
    }

    private List<Record> generateDefault() {
        List<Record> defaultList = new ArrayList<>();

        defaultList.add(new Record("General", 100000));
        defaultList.add(new Record("Coloner", 75000));
        defaultList.add(new Record("Major", 50000));
        defaultList.add(new Record("Captain", 30000));
        defaultList.add(new Record("Lieutenant", 20000));
        defaultList.add(new Record("Chief Master" , 15000));
        defaultList.add(new Record("Master Sergant" , 10000));
        defaultList.add(new Record("Tecnical Sergant" , 5000));
        defaultList.add(new Record("Staff Sergant" , 2500));
        defaultList.add(new Record("Airman" , 1000));

        return defaultList;
    }

    public boolean isHighScore(int score) {
        for (Record record: highScores) {
            if (score > record.score) {
                return true;
            }
        }
        return false;
    }

    public void writeHighScore(String string) {
        Iterator<Record> iterator = highScores.iterator();
        Record newRecord = new Record(string);
        int i = 0;
        while (iterator.hasNext()) {
            Record record = iterator.next();
            if (newRecord.score > record.score) {
                highScores.add(i, newRecord);
                highScores.remove(10);
                break;
            }
            i++;
        }
    }

    public List<String> getHighScores() {
        List<String> output = new LinkedList<>();
        for (Record record: highScores) {
            output.add(record.toString());
        }
        return output;
    }

    private void saveHighScore() {
        try (FileWriter writer = new FileWriter("resources\\HighScores.dat", false)){
            for (Record record: highScores) {
                writer.write(record.toFileString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Record {
        public String name;
        public int score;

        public Record(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public Record(String input) {
            String []st = input.split("-");
            name = st[0];
            score = Integer.parseInt(st[1]);
        }

        @Override
        public String toString() {
            return "" + name + ' ' + score;
        }

        public String toFileString() {
            return "" + name + '-' + score;
        }
    }
}
