package com.example.starways.layer_game.builders.builders_scene.general_builders;

import com.example.starways.R;
import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.inputs.ButtonMove;
import com.example.starways.layer_game.inputs.ButtonShoot;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Utility object used to build important inputs and User Controls.

 */
public class BuilderSceneControls {

    public static ButtonShoot buildButtonShoot(PhysicsEngine2DV1 engine) {
        Vector2D center = new Vector2D(
                engine.getSpaceTime2D().getSpaceWidth() * 0.90f,
                engine.getSpaceTime2D().getSpaceHeight() * 0.90f
        );
        return new ButtonShoot(
                center,
                engine.getSpaceTime2D().getSpaceHeight() * 0.08f,
                R.drawable.button_shoot_0
        );
    }

    public static ButtonMove buildButtonMove(PhysicsEngine2DV1 engine) {
        Vector2D center = new Vector2D(
                engine.getSpaceTime2D().getSpaceWidth() * 0.10f,
                engine.getSpaceTime2D().getSpaceHeight() * 0.84f
        );
        return new ButtonMove(
                center,
                engine.getSpaceTime2D().getSpaceHeight() * 0.12f,
                R.drawable.spaceship_3,
                R.drawable.spaceship_3
        );
    }
}
