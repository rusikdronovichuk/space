package com.example.starways.layer_game.logics.logics_gameobjects;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.game_objects.enemy_objects.RectangleEnemyGameObject;
import com.example.starways.layer_game.game_objects.player_objects.PlayerGameObject;
import com.example.starways.layer_game.builders.builders_scene.general_builders.BuilderScene;
import com.example.starways.layer_game.managers.GameObjectsManager;
import com.example.starways.layer_game.media.sounds.GameSoundPool;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Utility object used to encapsulate and centralize all the logic around GameObjects
            and the Manager of them.

 */
public class SceneAgents {

    private PlayerGameObject player;
    private RectangleEnemyGameObject enemy;
    private GameObjectsManager manager;

    public SceneAgents(int level, GameSoundPool soundPool, PhysicsEngine2DV1 engine) {
        manager = new GameObjectsManager(soundPool);

        player = BuilderScene.buildScenePlayer(level, engine);
        enemy = BuilderScene.buildSceneEnemy(level, engine);

        engine.getManager().register(player);
        manager.registerPlayer(player);

        engine.getManager().register(enemy);
        manager.registerEnemy(enemy);
    }

    public PlayerGameObject getPlayer() {
        return player;
    }

    public RectangleEnemyGameObject getEnemy() {
        return enemy;
    }

    public GameObjectsManager getManager() {
        return manager;
    }
}
