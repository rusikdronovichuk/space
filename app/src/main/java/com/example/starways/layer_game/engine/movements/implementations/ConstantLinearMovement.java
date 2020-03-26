package com.example.starways.layer_game.engine.movements.implementations;

import android.util.Log;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.movements.builders.BuilderMovementLinear;
import com.example.starways.layer_game.engine.movements.framework.Movement;
import com.example.starways.layer_game.engine.mathematics.Vector2D;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Class designed to encapsulate logic of zig-zag movement on line with constant speed
            in given boundaries and directions.
        o After the constructor, setupBoundaries and setBouncing, setupVelocity needs to be called
            to setupVelocities for movement in the given directions
        o firstRisingX0 means that the first movement will be in the direction of rising x0
            (in Android, it means to right) and it matters only if alongX0 is true.
            If alongX0 is true and firstRisingX0 is false, then first movement will be in the
            direction of the falling x0 (in Android, it means to the left)
        o firstRisingX1 is in the same logic as firstRisingX0, just for the x1 axis.
        o fixForBoundaries works only for center of the PhysicalObject2D.
            If the User is working with, for example, PhysicalObject2D of the shape of the
            rectangle, and wants that boundaries of the rectangle are not passing over
            the boundaries of the movement, that needs to be taken into account in
            the process of calling the constructor of ConstantLinearMovement and
            x0/x1 Min/Max need to be calculated before this movement object is created
            (here, x0Min would be x0 - width / 2 etc)

 */
public class ConstantLinearMovement extends Movement {

    //Used for the copy
    private boolean alongX0;
    private boolean alongX1;
    private boolean firstRisingX0;
    private boolean firstRisingX1;

    public ConstantLinearMovement(PhysicsEngine2DV1 engine) { super(engine); }

    @Override
    public void setupVelocities(
            boolean alongX0,
            boolean alongX1,
            boolean firstRisingX0,
            boolean firstRisingX1
    ) {
        this.alongX0 = alongX0;
        this.alongX1 = alongX1;
        this.firstRisingX0 = firstRisingX0;
        this.firstRisingX1 = firstRisingX1;

        Vector2D velocity = physicalObject2D.getVelocity();

        if (alongX0 == false)
            velocity.setX0(0);
        if (alongX1 == false)
            velocity.setX1(0);

        if (alongX0 == true) {
            if (firstRisingX0 == true) {
                if (velocity.getX0() < 0)
                    velocity.setX0(velocity.getX0() * (-1));
            } else {
                if (velocity.getX0() > 0)
                    velocity.setX0(velocity.getX0() * (-1));
            }
        }
        if (alongX1 == true) {
            if (firstRisingX1 == true) {
                if (velocity.getX1() < 0)
                    velocity.setX1(velocity.getX1() * (-1));
            } else {
                if (velocity.getX1() > 0)
                    velocity.setX1(velocity.getX1() * (-1));
            }
        }

        physicalObject2D.setVelocity(velocity);
    }

    @Override
    public void updatePosition(long dt) {
        if (bouncing)
            fixForBoundaries();

        //v_t(i + 1) = v_t(i) + a * dt
        Vector2D deltaV = physicalObject2D.getAcceleration().getCopy();
        deltaV.multiplyWithScalar(1.0f * dt / engine.getSpaceTime2D().getTimeScale());
        Vector2D newVelocity = Vector2D.add(physicalObject2D.getVelocity(), deltaV);
        physicalObject2D.setVelocity(newVelocity);

        //x_t(i + 1) = x_t(i) + v * dt
        Vector2D deltaX = physicalObject2D.getVelocity().getCopy();
        deltaX.multiplyWithScalar(1.0f * dt / engine.getSpaceTime2D().getTimeScale());
        Vector2D newPosition = Vector2D.add(physicalObject2D.getCenter(), deltaX);
        physicalObject2D.setCenter(newPosition);

        //DEBUG
        //Log.d("PHYSICS", "Velocity: " + newVelocity.getX0() + " : " + newVelocity.getX1());
        //Log.d("PHYSICS", "Position: " + newPosition.getX0() + " : " + newPosition.getX1());
    }

    @Override
    public Movement getCopy() {
        return BuilderMovementLinear.buildConstantLinearMovement(
                engine,
                x0Min,
                x0Max,
                x1Min,
                x1Max,
                bouncing
        );
    }

    private void fixForBoundaries() {
        Vector2D position = physicalObject2D.getCenter();
        Vector2D velocity = physicalObject2D.getVelocity();
        if (
                ((position.getX0() <= x0Min) && (velocity.getX0() < 0))
            ||
                ((position.getX0() >= x0Max) && (velocity.getX0() > 0))
        ) {
            velocity.setX0(velocity.getX0() * (-1));
        }
        if (
                ((position.getX1() <= x1Min) && (velocity.getX1() < 0))
            ||
                ((position.getX1() >= x1Max) && (velocity.getX1() > 0))
        ) {
            velocity.setX1(velocity.getX1() * (-1));
        }
        physicalObject2D.setVelocity(velocity);
    }

}
