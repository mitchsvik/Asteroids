package com.coursework.main;

import com.coursework.frames.Canvas;
import com.coursework.gameobjects.*;
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
    private int level;
    private int deathCooldown;
    private int levelStartCooldown;
    private int restartCooldown;
    private int score;
    private boolean gameOver;
    private boolean restartGame;
    private boolean paused;

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
                        if (!checkForRestart()) {
                            shuttle.setPush(true);
                        }
                        break;
                    // Rotate left;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        if (!checkForRestart()) {
                            shuttle.setRotateLeft(true);
                        }
                        break;
                    // Rotate right;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        if (!checkForRestart()) {
                            shuttle.setRotateRight(true);
                        }
                        break;
                    // Open fire from main gun;
                    case KeyEvent.VK_SPACE:
                        if (!checkForRestart()) {
                            shuttle.setFiring(true);
                        }
                        break;
                    // Pause the game;
                    case KeyEvent.VK_ESCAPE:
                        if (!checkForRestart()) {
                            setPaused();
                        }
                        break;
                    // Other keys;
                    default:
                        checkForRestart();
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

    private boolean checkForRestart() {
        if (gameOver && restartCooldown <= 0) {
            restartGame = true;
        }
        return restartGame;
    }

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public Random getRandom() {
        return random;
    }

    public Shuttle getShuttle() {
        return shuttle;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public boolean isLevelStarted() {
        return (levelStartCooldown > 0);
    }

    public boolean isShuttleInvulnerable() {
        return (deathCooldown > 0);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setPaused() {
        paused = !paused;
    }

    public boolean isPaused() {
        return paused;
    }

    public void startGame() {
        gameObjectList = new LinkedList<GameObject>();
        waitingList = new ArrayList<GameObject>();
        shuttle = new Shuttle();
        random = new Random();

        resetGame();

        timer = new Timer(17, ActionEvent -> {
            updateGame();
            canvas.repaint();
            }
        );
        timer.start();
    }

    private void updateGame() {
        if (isPaused()) {
            return;
        }

        gameObjectList.addAll(waitingList);
        waitingList.clear();

        if (restartCooldown > 0) {
            restartCooldown--;
        }
        if (levelStartCooldown > 0) {
            levelStartCooldown--;
        }
        if (gameOver && restartGame) {
            resetGame();
        }

        if (!gameOver && areEnemyDead()) {
            level++;
            levelStartCooldown = 60;

            resetGameObjectsList();

            shuttle.reset();
            shuttle.setFiringEnabled(true);

            for(int i = 0; i< 2 + level; i++) {
                addGameObject(new Asteroid(random));
            }
        }

        if (deathCooldown > 0) {
            if (deathCooldown == 180) {
                shuttle.reset();
            }
            if (deathCooldown == 1) {
                shuttle.setFiringEnabled(true);
            }
            deathCooldown--;
        }

        if (levelStartCooldown == 0) {
            for (GameObject gameObject : gameObjectList) {
                gameObject.update(this);
            }

            for (int i = 0; i < gameObjectList.size() - 1; i++) {
                GameObject a = gameObjectList.get(i);
                for (int j = i + 1; j < gameObjectList.size(); j++) {
                    GameObject b = gameObjectList.get(j);
                    if (a.checkCollision(b) && ((a != shuttle && b != shuttle) || deathCooldown <= 0)) {
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
    }

    private void resetGame() {
        lives = 3;
        level = 0;
        score = 0;
        gameOver = false;
        restartGame = false;
        resetGameObjectsList();
    }

    public void killShuttle() {
        lives--;
        if (lives == 0) {
            gameOver = true;
            restartCooldown = 120;
            deathCooldown = 0;
        } else {
            deathCooldown = 180;
        }
        shuttle.setFiringEnabled(false);
    }

    public void addGameObject(GameObject gameObject) {
        waitingList.add(gameObject);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public boolean areEnemyDead() {
        for (GameObject gameObject: gameObjectList) {
            if(gameObject.getClass() == Asteroid.class || gameObject.getClass() == UFO.class
                    || gameObject.getClass() == LargeUFO.class) {
                return false;
            }
        }
        return true;
    }

    private void resetGameObjectsList() {
        waitingList.clear();
        gameObjectList.clear();
        gameObjectList.add(shuttle);
    }
}
