package com.coursework.gameobjects;

import com.coursework.main.GameEngine;
import com.coursework.util.TexturePool;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Veniamin Zinevych on 13.05.2016.
 */
public class AsteroidIce extends Asteroid {
    public AsteroidIce(Random random) {
        super(random);
    }

    public AsteroidIce(Asteroid parent, AsteroidType type, Random random) {
        super(parent, type, random);
    }

    @Override
    public void handleCollision(GameObject gameObject, GameEngine gameEngine) {
        if (gameObject.getClass() == Bullet.class) {
            if (type != AsteroidType.Small) {
                AsteroidType spawnType = AsteroidType.values()[type.ordinal() - 1];
                for(int i = 0; i<2; i++) {
                    gameEngine.addGameObject(new AsteroidIce(this, spawnType, gameEngine.getRandom()));
                }
            }
            notAlive();
            gameEngine.addScore(type.getScore());
        }
    }

    @Override
    protected BufferedImage getTexture() {
        return TexturePool.getAsteroidIceTexture();
    }
}
