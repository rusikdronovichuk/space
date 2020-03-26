package com.example.starways.layer_game.builders.builders_scene.general_builders;

import com.example.starways.R;
import com.example.starways.layer_game.engine.constants.ProposedSpeeds;
import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.engine.movements.framework.Movement;
import com.example.starways.layer_game.builders.builders_game_object.agent_object_builders.BuilderAgents;
import com.example.starways.layer_game.builders.builders_game_object.shooter_builders.BuilderShooter;
import com.example.starways.layer_game.builders.builders_game_object.weapon_builders.BuilderWeapon;
import com.example.starways.layer_game.game_objects.enemy_objects.RectangleEnemyGameObject;
import com.example.starways.layer_game.game_objects.player_objects.PlayerGameObject;
import com.example.starways.layer_game.game_objects.shooter_objects.ShooterCircular;
import com.example.starways.layer_game.game_objects.weapon_objects.WeaponCircular;
import com.example.starways.layer_game.logics.logics_gameobjects.GameObjectHealths;
import com.example.starways.layer_game.logics.logics_gameobjects.GameObjectSizes;
import com.example.starways.layer_game.logics.logics_gameobjects.WeaponDamages;
import com.example.starways.layer_game.engine.movements.builders.BuilderMovementLinear;
import com.example.starways.layer_game.builders.builders_scene.movement_builders.BuilderLevelEnemy;
import com.example.starways.layer_game.builders.builders_scene.movement_builders.BuilderLevelEnemyWeapon;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Utility object used to encapsulate the logic for building the SceneAgents (Enemy
            and Player GameObjects)

 */
public class BuilderSceneAgents {

    private static final int TRANSITION_0 = 4;

    public static RectangleEnemyGameObject buildSceneEnemy(int level, PhysicsEngine2DV1 engine) {
        //Setting up the weapon for the shooter
        WeaponCircular weaponCircular = BuilderLevelEnemyWeapon.buildWeaponCircular(level, engine);

        //Setting up the the shooter with its weapon
        ShooterCircular shooterCircular = BuilderShooter.buildShooterCircular(
                weaponCircular,
                R.drawable.spaceship_3
        );

        return BuilderLevelEnemy.buildRectangleEnemy(
                level,
                engine,
                shooterCircular
        );
    }

    public static PlayerGameObject buildScenePlayer(int level, PhysicsEngine2DV1 engine) {
        float x0Min =  engine.getSpaceTime2D().getSpaceWidth() * 0.25f;
        float x0Max = engine.getSpaceTime2D().getSpaceWidth() * 0.75f;
        float x1Min = 0;
        float x1Max = engine.getSpaceTime2D().getSpaceHeight() * 1.25f;

        Vector2D center = new Vector2D(
                engine.getSpaceTime2D().getSpaceWidth() / 2,
                engine.getSpaceTime2D().getSpaceHeight() / 2
        );

        //Setting up the weapon for the shooter
        Movement weaponMovement = BuilderMovementLinear.buildConstantLinearMovement(
                engine,
                x0Min,
                x0Max,
                x1Min,
                x1Max,
                false
        );
        WeaponCircular weaponCircular = BuilderWeapon.buildWeaponCircular(
                center,
                new Vector2D(ProposedSpeeds.SPEED_SLOW, ProposedSpeeds.SPEED_SLOW),
                new Vector2D(),
                GameObjectSizes.MASS_1,
                GameObjectSizes.WEAPON_RADIUS_2,
                engine,
                weaponMovement,
                WeaponDamages.DAMAGE_0,
                false,
                R.drawable.weapon_plasma_ball_1
        );
        weaponCircular.getMovement().setupVelocities(
                false,
                true,
                false,
                false
        );

        //Setting up the the shooter with its weapon
        ShooterCircular shooterCircular = BuilderShooter.buildShooterCircular(
                weaponCircular,
                R.drawable.spaceship_3,
                R.drawable.spaceship_3
        );

        //Setting up the the player with its shooter
        PlayerGameObject player = BuilderAgents.buildPlayer(
                center,
                new Vector2D(),
                new Vector2D(),
                GameObjectSizes.MASS_1,
                engine,
                GameObjectSizes.PLAYER_WIDTH_0,
                GameObjectSizes.PLAYER_WIDTH_0,
                shooterCircular,
                GameObjectHealths.HEALTH_SMALL,
                R.drawable.spaceship_3
        );
        player.setupBoundaries(
                x0Min,
                x0Max,
                x1Min,
                x1Max
        );

        return player;
    }
}
