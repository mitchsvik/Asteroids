package com.coursework.main;

import com.coursework.frames.Canvas;
import com.coursework.gameobjects.Asteroid;
import com.coursework.gameobjects.GameObject;
import com.coursework.gameobjects.Shuttle;
import com.coursework.util.TexturePool;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by Veniamin Zinevych on 03.05.2016.
 */
public class GameEngine extends JFrame {
    private List<GameObject> gameObjectList;
    private List<GameObject> waitingList;
    private Engine engine;
    private Canvas canvas;
    private Shuttle shuttle;
    private Random random;
    private Timer timer;
    private int lives;

    public GameEngine(Engine e) {
        super("Asteroids");
        engine = e;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        add(canvas = new Canvas(this), BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                TexturePool.reset();
                engine.showMainMenu();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    // Push ship;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        shuttle.setPush(true);
                        break;
                    // Rotate left;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        shuttle.setRotateLeft(true);
                        break;
                    // Rotate right;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        shuttle.setRotateRight(true);
                        break;
                    // Open fire from main gun;
                    case KeyEvent.VK_SPACE:
                        shuttle.setFiring(true);
                        break;
                    // Pause the game;
                    case KeyEvent.VK_ESCAPE:
                        break;
                    // Other keys;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    // Stop pushing ship;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        shuttle.setPush(false);
                        break;
                    // Stop rotate left;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        shuttle.setRotateLeft(false);
                        break;
                    // Stop rotate right;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        shuttle.setRotateRight(false);
                        break;
                    // Stop fire from main gun;
                    case KeyEvent.VK_SPACE:
                        shuttle.setFiring(false);
                        break;
                }
            }
        });

        setMinimumSize(new Dimension(Canvas.FIELD_SIZE+6, Canvas.FIELD_SIZE+29));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public int getLives() {
        return lives;
    }

    public Random getRandom() {
        return random;
    }

    public void startGame() {
        gameObjectList = new LinkedList<GameObject>();
        waitingList = new ArrayList<GameObject>();
        shuttle = new Shuttle();
        random = new Random();

        resetGame();

        gameObjectList.add(new Asteroid(random));
        gameObjectList.add(new Asteroid(random));
        gameObjectList.add(new Asteroid(random));

        timer = new Timer(17, ActionEvent -> {
            updateGame();
            canvas.repaint();
            }
        );
        timer.start();
    }

    public void updateGame() {
        gameObjectList.addAll(waitingList);
        waitingList.clear();

        for (GameObject gameObject: gameObjectList) {
            gameObject.update(this);
        }

        for (int i = 0; i < gameObjectList.size() - 1; i++) {
            GameObject a = gameObjectList.get(i);
            for (int j = i + 1; j < gameObjectList.size(); j++) {
                GameObject b = gameObjectList.get(j);
                if (a.checkCollision(b)) {
                    a.handleCollision(b, this);
                    b.handleCollision(a, this);
                }
            }
        }

        Iterator<GameObject> iterator = gameObjectList.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().isAlive()) {
                iterator.remove();
            }
        }
    }

    public void resetGame() {
        lives = 3;
        waitingList.clear();
        gameObjectList.clear();
        gameObjectList.add(shuttle);
    }

    public void killShuttle() {
        lives--;
    }

    public void addGameObject(GameObject gameObject) {
        waitingList.add(gameObject);
    }
}
