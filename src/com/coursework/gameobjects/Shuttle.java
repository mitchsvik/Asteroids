package com.coursework.gameobjects;

import com.coursework.frames.Canvas;
import com.coursework.main.GameEngine;
import com.coursework.util.Vector2d;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Veniamin Zinevych on 28.04.2016.
 */
public class Shuttle extends GameObject {
    private BufferedImage material;
    private BufferedImage flame;

    private boolean pushPressed;
    private boolean rotateLeftPressed;
    private boolean rotateRightPressed;

    public Shuttle() {
        super(new Vector2d(Canvas.FIELD_SIZE/2.0, Canvas.FIELD_SIZE/2.0), new Vector2d(0.0, 0.0), 10.0, 0);

        try {
            material = ImageIO.read(new File("resources\\images\\backgrounds\\shuttleMain.png"));
        } catch (IOException e) {
            material = new BufferedImage((int)radius*2, (int)radius*2, 1);
        }
        try {
            flame = ImageIO.read(new File("resources\\images\\backgrounds\\shuttleFlame.png"));
        } catch (IOException e) {
            flame = new BufferedImage((int)radius*2, (int)radius*2, 1);
        }

        rotation = -Math.PI / 2;
        pushPressed = false;
        rotateLeftPressed = false;
        rotateRightPressed = false;
    }

    public void setPush(boolean bool) {
        pushPressed = bool;
    }

    public void setRotateLeft(boolean bool) {
        rotateLeftPressed = bool;
    }

    public void setRotateRight(boolean bool) {
        rotateRightPressed = bool;
    }

    public void reset() {
        rotation = -Math.PI/2;
        position.set(Canvas.FIELD_SIZE/2.0, Canvas.FIELD_SIZE/2.0);
        velocity.set(0.0, 0.0);
    }

    @Override
    public void update(GameEngine gameEngine) {
        super.update(gameEngine);

        if (rotateLeftPressed != rotateRightPressed) {
            rotate(rotateLeftPressed ? -0.0523 : 0.0523);
        }

        if (pushPressed) {
            velocity.add(new Vector2d(rotation).scale(0.036));
            if (velocity.getLength() > 6.5) {
                velocity.normalize().scale(6.5);
            }
        }

        if (velocity.getLength() > 0.0) {
            velocity.scale(0.095);
        }
    }

    @Override
    public void handleCollision(GameObject gameObject, GameEngine gameEngine) {
        gameEngine.killShuttle();
    }

    @Override
    public void draw(Graphics2D g, GameEngine gameEngine) {
        Shape shape = getShape();
        Rectangle r = shape.getBounds();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        AffineTransform centerTransform = AffineTransform.getTranslateInstance(-r.x+1, -r.y+1);

        g.transform(centerTransform);
        g.setClip(shape);
        g.drawImage(material, -(int)radius, -(int)radius, null);
        g.setClip(null);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(1f));
        g.draw(shape);
    }

    private Shape getShape() {
        GeneralPath p = new GeneralPath();
        p.moveTo(10,0);
        p.lineTo(-10,-8);
        p.lineTo(-10,-7);
        p.lineTo(-6,-5);
        p.lineTo(-6,5);
        p.lineTo(-10,7);
        p.lineTo(-10,8);
        p.closePath();
        return p;
    }
}
