package com.coursework.gameobjects;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Shuttle implements GameObject {
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Shuttle){
            return true;
        }
        return false;
    }
}
