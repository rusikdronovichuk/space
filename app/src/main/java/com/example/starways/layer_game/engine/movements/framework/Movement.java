package com.example.starways.layer_game.engine.movements.framework;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.physical_objects.PhysicalObject2D;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Abstract class which needs to be extended by the User.
        o Instances of this class are utility objects which can be used to
            encapsulate some common moving patterns such as zig-zag movement in the given
            boundaries with constant speed (ConstantLinearMovement) etc.
        o This class should be used inside implementation of the update() of the PhysicalObject2D.
        o updatePosition method will update the physics of the injected PhysicalObject2D.
        o Construction is in steps:
            x First the constructor is called
            x Then setupBoundaries and bouncing

    @To be implemented by the User:
        o protected void updatePosition(long currentTime, long lastTime);
 */
public abstract class Movement {

    //Bouncing indicator
    protected boolean bouncing;

    //Boundaries
    protected float x0Min;
    protected float x0Max;
    protected float x1Min;
    protected float x1Max;

    //Physics data
    protected PhysicalObject2D physicalObject2D;
    protected PhysicsEngine2DV1 engine;

    public Movement(PhysicsEngine2DV1 engine) {
        this.engine = engine;
    }

    public boolean isBouncing() {
        return bouncing;
    }

    public void setBouncing(boolean bouncing) {
        this.bouncing = bouncing;
    }

    public void registerPhysicalObject(PhysicalObject2D physicalObject2D) {
        this.physicalObject2D = physicalObject2D;
    }

    public float getX0Min() {
        return x0Min;
    }

    public float getX0Max() {
        return x0Max;
    }

    public float getX1Min() {
        return x1Min;
    }

    public float getX1Max() { return x1Max; }

    public void setupBoundaries(float x0Min, float x0Max, float x1Min, float x1Max) {
        this.x0Min = x0Min;
        this.x0Max = x0Max;
        this.x1Min = x1Min;
        this.x1Max = x1Max;
    }

    public abstract void setupVelocities(
            boolean alongX0,
            boolean alongX1,
            boolean firstRisingX0,
            boolean firstRisingX1
    );
    public abstract void updatePosition(long dt);
    public abstract Movement getCopy();
}
