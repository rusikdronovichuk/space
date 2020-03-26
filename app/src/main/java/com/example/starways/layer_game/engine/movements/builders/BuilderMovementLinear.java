package com.example.starways.layer_game.engine.movements.builders;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.movements.implementations.ConstantLinearMovement;
import com.example.starways.layer_game.engine.movements.implementations.RandomLinearMovement;
import com.example.starways.layer_game.engine.physical_objects.PhysicalObject2D;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Class used to encapsulate the logic of initializing LinearMovements
            (ConstantLinearMovement, RandomLinearMovement)
        o Parameters in function calls are used to ensure that the User pass all parameters
            used for the proper building of movements

 */
public class BuilderMovementLinear {

    public static ConstantLinearMovement buildConstantLinearMovement(
            PhysicsEngine2DV1 engine,
            float x0Min,
            float x0Max,
            float x1Min,
            float x1Max,
            boolean bouncing
    ) {
        ConstantLinearMovement movement = new ConstantLinearMovement(engine);
        movement.setupBoundaries(x0Min, x0Max, x1Min, x1Max);
        movement.setBouncing(bouncing);

        return movement;
    }

    public static RandomLinearMovement buildRandomLinearMovement(
            PhysicsEngine2DV1 engine,
            float x0Min,
            float x0Max,
            float x1Min,
            float x1Max,
            boolean bouncing,
            boolean alongX0,
            boolean alongX1,
            long teleportTime
    ) {
        RandomLinearMovement movement = new RandomLinearMovement(engine);
        movement.setupBoundaries(x0Min, x0Max, x1Min, x1Max);
        movement.setBouncing(bouncing);
        movement.setupTeleportData(alongX0, alongX1, teleportTime);

        return movement;
    }
}
