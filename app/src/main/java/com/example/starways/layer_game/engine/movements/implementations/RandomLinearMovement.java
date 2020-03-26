package com.example.starways.layer_game.engine.movements.implementations;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.EngineRandom;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.engine.movements.builders.BuilderMovementLinear;
import com.example.starways.layer_game.engine.movements.framework.Movement;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Class designed to encapsulate logic of random zig-zag movement on line
            in given boundaries and directions

 */
public class RandomLinearMovement extends Movement {

    private boolean alongX0;
    private boolean alongX1;

    private long teleportTime;
    private long lastTime;

    public RandomLinearMovement(PhysicsEngine2DV1 engine) { super(engine); }

    public void setupTeleportData(boolean alongX0, boolean alongX1, long teleportTime) {
        this.alongX0 = alongX0;
        this.alongX1 = alongX1;
        this.teleportTime = teleportTime;
        lastTime = System.currentTimeMillis();
    }

    @Override
    public void setupVelocities(boolean alongX0, boolean alongX1, boolean firstRisingX0, boolean firstRisingX1) {

    }

    @Override
    public void updatePosition(long dt) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= teleportTime) {
            lastTime = currentTime;

            Vector2D newCenter = physicalObject2D.getCenter().getCopy();
            if (alongX0)
                newCenter.setX0(EngineRandom.getRandom(Math.round(x0Min), Math.round(x0Max)));
            if (alongX1)
                newCenter.setX1(EngineRandom.getRandom(Math.round(x1Min), Math.round(x1Max)));

            physicalObject2D.setCenter(newCenter);
        }
    }

    @Override
    public Movement getCopy() {
        return BuilderMovementLinear.buildRandomLinearMovement(
                engine,
                x0Min,
                x0Max,
                x1Min,
                x1Max,
                bouncing,
                alongX0,
                alongX1,
                teleportTime
        );
    }
}
