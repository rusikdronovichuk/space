package com.example.starways.layer_game.builders.builders_scene.general_builders;

import android.graphics.Bitmap;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.game_objects.enemy_objects.RectangleEnemyGameObject;
import com.example.starways.layer_game.game_objects.player_objects.PlayerGameObject;
import com.example.starways.layer_game.inputs.ButtonMove;
import com.example.starways.layer_game.inputs.ButtonShoot;

/*
    @Author:
        Vasilije Becic

    @Description:
        o This class is used to encapsulate the logic of
            initializing GameObjects for the GamePlayScene.

 */
public class BuilderScene {

    public static PlayerGameObject buildScenePlayer(int level, PhysicsEngine2DV1 engine) {
        return BuilderSceneAgents.buildScenePlayer(level, engine);
    }

    public static RectangleEnemyGameObject buildSceneEnemy(int level, PhysicsEngine2DV1 engine) {
        return BuilderSceneAgents.buildSceneEnemy(level, engine);
    }

    public static ButtonShoot buildButtonShoot(PhysicsEngine2DV1 engine) {
        return BuilderSceneControls.buildButtonShoot(engine);
    }

    public static ButtonMove buildButtonMove(PhysicsEngine2DV1 engine) {
        return BuilderSceneControls.buildButtonMove(engine);
    }

    public static Bitmap buildSceneBackground(int level) {
        return BuilderSceneAmbient.buildSceneBackground(level);
    }
}
