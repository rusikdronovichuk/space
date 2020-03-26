package com.example.starways.layer_game.engine.physical_objects;

import android.graphics.RectF;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Represents a type of the PhysicalObject2D which has the shape of a rectangle

 */
public abstract class RectanglePhysicalObject extends PhysicalObject2D {

    protected float width;
    protected float height;

    protected RectF rectangle;

    public RectanglePhysicalObject(
            Vector2D center,
            Vector2D velocity,
            Vector2D acceleration,
            float mass,
            PhysicsEngine2DV1 engine,
            float width,
            float height
    ) {
        super(center, velocity, acceleration, mass, engine);
        this.width = width;
        this.height = height;
        this.engine = engine;
        this.rectangle = new RectF(
                center.getX0() - width / 2,
                center.getX1() - height / 2,
                center.getX0() + width / 2,
                center.getX1() + height / 2
        );
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public RectF getRectangle() {
        return rectangle;
    }
}
