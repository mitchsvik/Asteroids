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
    private BufferedImage fire;

    public Shuttle() {
        super(new Vector2d(Canvas.FIELD_SIZE/2, Canvas.FIELD_SIZE/2), new Vector2d(0,0), 10.0, 0);
        try {
            material = ImageIO.read(new File("resources\\images\\backgrounds\\shuttleMain.png"));
        } catch (IOException e) {
            material = new BufferedImage((int)radius*2, (int)radius*2, 1);
        }
        try {
            fire = ImageIO.read(new File("resources\\images\\backgrounds\\shuttleFire.png"));
        } catch (IOException e) {
            fire = new BufferedImage((int)radius*2, (int)radius*2, 1);
        }
        rotation = -Math.PI / 2;
    }

    @Override
    public void handleCollision(GameObject gameObject, GameEngine gameEngine) {

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
