package com.example.starways.layer_game.builders.builders_game_object.agent_object_builders;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.engine.movements.framework.Movement;
import com.example.starways.layer_game.game_objects.enemy_objects.RectangleEnemyGameObject;
import com.example.starways.layer_game.game_objects.player_objects.PlayerGameObject;
import com.example.starways.layer_game.game_objects.shooter_objects.ShooterCircular;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Builder with static methods used to build Enemy or Player GameObjects.
        o Components of these objects such as Shooters, need to be instantiated outside of this
            builder and passed as an arguments.

 */
public class BuilderAgents {

    public static RectangleEnemyGameObject buildRectangleEnemy(
            Vector2D center,
            Vector2D velocity,
            Vector2D acceleration,
            float mass,
            PhysicsEngine2DV1 engine,
            float width,
            float height,
            Movement enemyMovement,
            ShooterCircular shooterCircular,
            int health,
            long shootingTimeInterval,
            int resourceID
    ) {
        RectangleEnemyGameObject enemy = new RectangleEnemyGameObject(
                center, velocity, acceleration, mass, engine, width, height
        );
        enemy.setupCircularShooter(shooterCircular);
        enemy.setupMovement(enemyMovement);
        enemy.setupHealthBar(health);
        enemy.setupTime(shootingTimeInterval);
        enemy.setupSprite(resourceID);

        return enemy;
    }

    public static PlayerGameObject buildPlayer(
            Vector2D center,
            Vector2D velocity,
            Vector2D acceleration,
            float mass,
            PhysicsEngine2DV1 engine,
            float width,
            float height,
            ShooterCircular shooterCircular,
            int health,
            int resourceID
    ) {
        PlayerGameObject player = new PlayerGameObject(
                center, velocity, acceleration, mass, engine, width, height
        );
        player.setupCircularShooter(shooterCircular);
        player.setupHealthBar(health);
        player.setupSprite(resourceID);

        return player;
    }
}
