package com.example.starways.layer_game.inputs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.framework.GameObject;
import com.example.starways.layer_game.builders.builders_scene.general_builders.SpriteFactory;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Input type of the GameObject
            used to allow user to fire weapons from its Player GameObject.

    @Info:
        o This will soon be changed (related to the ButtonCircular, as ButtonMove and ButtonShoot
            are, for now, identical classes)

 */
public class ButtonMove implements GameObject {

    private int resourceReleased;
    private int resourcePressed;

    private float radius;

    private RectF rectangle;
    private Vector2D center;
    private Bitmap sprite;

    public ButtonMove(Vector2D center, float radius, int resourceReleased, int resourcePressed) {
        this.center = center;
        this.radius = radius;
        this.resourceReleased = resourceReleased;
        this.resourcePressed = resourcePressed;

        sprite = SpriteFactory.getBitmap(resourceReleased);

        rectangle = new RectF(
                center.getX0() - radius,
                center.getX1() - radius,
                center.getX0() + radius,
                center.getX1() + radius
        );
    }

    public void press() {
        sprite = SpriteFactory.getBitmap(resourcePressed);
    }

    public void release() {
        sprite = SpriteFactory.getBitmap(resourceReleased);
    }

    public boolean contains(Vector2D point) {
        if (Vector2D.distance(center, point) <= radius)
            return true;
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                sprite,
                null,
                rectangle,
                null
        );
    }
}
