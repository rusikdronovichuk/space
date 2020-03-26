package com.example.starways.layer_game.builders.builders_scene.movement_builders;

import com.example.starways.R;
import com.example.starways.layer_game.engine.constants.ProposedSpeeds;
import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.engine.movements.builders.BuilderMovementLinear;
import com.example.starways.layer_game.engine.movements.framework.Movement;
import com.example.starways.layer_game.builders.builders_game_object.agent_object_builders.BuilderAgents;
import com.example.starways.layer_game.game_objects.enemy_objects.RectangleEnemyGameObject;
import com.example.starways.layer_game.game_objects.shooter_objects.ShooterCircular;
import com.example.starways.layer_game.logics.logics_gameobjects.GameObjectHealths;
import com.example.starways.layer_game.logics.logics_gameobjects.GameObjectSizes;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Utility object used to provide appropriate enemy state for each level.
        o The "enemy state" is abstraction for the parameters such as:
            x Type of enemy
            x Its health
            x Its movement
            x ResourceID
            x Shooter
            x Etc

 */
public class BuilderLevelEnemy {

    private static final int CHECK_POINT_0 = 4;

    public static RectangleEnemyGameObject buildRectangleEnemy(
            int level,
            PhysicsEngine2DV1 engine,
            ShooterCircular shooterCircular
    ) {
        float x0Min =  engine.getSpaceTime2D().getSpaceWidth() * 0.20f;
        float x0Max = engine.getSpaceTime2D().getSpaceWidth() * 0.80f;
        float x1Min = 0;
        float x1Max = engine.getSpaceTime2D().getSpaceHeight() * 1.25f;

        Vector2D center = new Vector2D(
                engine.getSpaceTime2D().getSpaceWidth() / 2,
                GameObjectSizes.START_0_X1
        );

        Movement enemyMovement;
        Vector2D velocity;
        int health;

        if (level < CHECK_POINT_0) {
            enemyMovement = BuilderMovementLinear.buildConstantLinearMovement(
                    engine,
                    x0Min,
                    x0Max,
                    x1Min,
                    x1Max,
                    true
            );
        } else {
            long teleportTime;
            switch (level) {
                case 4: {
                    teleportTime = 2000;
                    break;
                }
                case 5: {
                    teleportTime = 1000;
                    break;
                }
                default: {
                    teleportTime = 2000;
                    break;
                }
            }
            enemyMovement = BuilderMovementLinear.buildRandomLinearMovement(
                    engine,
                    x0Min,
                    x0Max,
                    x1Min,
                    x1Max,
                    false,
                    true,
                    false,
                    teleportTime
            );
        }

        switch (level) {
            case 1: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_SLOW, ProposedSpeeds.SPEED_SLOW);
                health = GameObjectHealths.HEALTH_SMALL;
                break;
            }
            case 2: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_MEDIUM, ProposedSpeeds.SPEED_MEDIUM);
                health = GameObjectHealths.HEALTH_MEDIUM;
                break;
            }
            case 3: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_FAST, ProposedSpeeds.SPEED_FAST);
                health = GameObjectHealths.HEALTH_LARGE;
                break;
            }
            case 4: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_SLOW, ProposedSpeeds.SPEED_SLOW);
                health = GameObjectHealths.HEALTH_SMALL;
                break;
            }
            case 5: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_MEDIUM, ProposedSpeeds.SPEED_SLOW);
                health = GameObjectHealths.HEALTH_MEDIUM;
                break;
            }
            default: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_SLOW, ProposedSpeeds.SPEED_SLOW);
                health = GameObjectHealths.HEALTH_SMALL;
            }
        }

        RectangleEnemyGameObject enemy = BuilderAgents.buildRectangleEnemy(
                center,
                velocity,
                new Vector2D(),
                GameObjectSizes.MASS_1,
                engine,
                GameObjectSizes.ENEMY_WIDTH_0,
                GameObjectSizes.ENEMY_HEIGHT_0,
                enemyMovement,
                shooterCircular,
                health,
                GameObjectSizes.TIME_TO_SHOOT_0,
                R.drawable.spaceship_3
        );

        //Setting up the the enemy with its shooter
        enemy.getMovement().setupVelocities(
                true,
                false,
                true,
                false
        );

        return enemy;
    }
}
