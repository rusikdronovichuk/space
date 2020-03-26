package com.example.starways.layer_game.game_objects.rest_objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.starways.layer_game.framework.GameObject;
import com.example.starways.layer_game.logics.logics_gameobjects.GameObjectHealths;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Represents HealthBar for Enemy or Player GameObjects
        o In this implementation, it is drawn as two overlapped rectangles.
        o As it is not physical object, but an indicator object,
            it doesn't extends PhysicalObject2D. But as it needs to be drawn, it implements
            GameObject interface. Also, as health bar is changeable in the game, update
            method is provided.

 */
public class HealthBar implements GameObject {

    private int health;
    private float widthTotal;
    private float widthCurrent;
    private float height;

    private Paint colorRed;
    private Paint colorWhite;

    private RectF barTotal;
    private RectF barLeft;

    public HealthBar(int health, float x0, float x1) {
        this.health = health;

        height = GameObjectHealths.HEIGHT_1;
        widthTotal = height * health;
        widthCurrent = widthTotal;

        colorRed = new Paint();
        colorRed.setColor(Color.RED);
        colorWhite = new Paint();
        colorWhite.setColor(Color.WHITE);

        barTotal = new RectF(
                x0,
                x1,
                x0 + widthTotal,
                x1 + height
        );
        barLeft = new RectF(
                x0,
                x1,
                x0 + widthCurrent,
                x1 + height
        );
    }

    public int getHealth() {
        return health;
    }

    public void lowerHealth(int damage) {
        health -= damage;
        if (health < 0)
            health = 0;
        widthCurrent = height * health;
    }

    public void updateHealth(float x0, float x1) {
        barTotal.set(x0, x1, x0 + widthTotal, x1 + height);
        barLeft.set(x0, x1, x0 + widthCurrent, x1 + height);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(
                barTotal,
                colorWhite
        );
        canvas.drawRect(
                barLeft,
                colorRed
        );
    }
}
