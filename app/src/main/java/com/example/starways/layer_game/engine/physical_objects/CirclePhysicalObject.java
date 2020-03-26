package com.example.starways.layer_game.engine.physical_objects;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Represents a type of the PhysicalObject2D which has the shape of a circle

 */
public abstract class CirclePhysicalObject extends PhysicalObject2D {

    protected float radius;

    public CirclePhysicalObject(
            Vector2D center,
            Vector2D velocity,
            Vector2D acceleration,
            float mass,
            float radius,
            PhysicsEngine2DV1 engine
    ) {
        super(center, velocity, acceleration, mass, engine);
        this.radius = radius;
        this.engine = engine;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
