package com.example.starways.layer_game.engine.physical_objects;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Abstract class which needs to be extended by the User.
        o Represents a type of the object which physical properties
            such as position and speed, can be modified by the Engine
            via update method.
        o Allows real-time changes of the objects which will be draw
            on canvas or other.
        o checkAndPhysicsResolveCollision should manage only physics part of the collision.
            The rest, such as impact on health bar, etc, should be managed outside.
            That is the reason why for this method is expected for the User to implement it,
             so that "true" is returned if collision happened.

    @To be implemented by the User:
        o void updatePhysics()
        o boolean checkAndPhysicsResolveCollision(PhysicalObject2D physicalObject2D)
 */
public abstract class PhysicalObject2D {

    //Kinematics data
    protected Vector2D velocity;

    //Dynamics data
    protected Vector2D acceleration;
    protected float mass;

    //General
    protected Vector2D center;
    protected PhysicsEngine2DV1 engine;

    public PhysicalObject2D(
            Vector2D center,
            Vector2D velocity,
            Vector2D acceleration,
            float mass,
            PhysicsEngine2DV1 engine
    ) {
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
        this.center = center;
        this.engine = engine;
    }

    public Vector2D getCenter() {
        return center;
    }

    public void setCenter(Vector2D center) {
        this.center = center;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    //Needs to be implemented by the User
    abstract public void updatePositions(long dt);
    abstract public boolean checkAndPhysicsResolveCollision(PhysicalObject2D physicalObject2D);
}
