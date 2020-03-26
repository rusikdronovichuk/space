package com.example.starways.layer_game.builders.builders_scene.movement_builders;

import com.example.starways.R;
import com.example.starways.layer_game.engine.constants.ProposedSpeeds;
import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.engine.movements.builders.BuilderMovementLinear;
import com.example.starways.layer_game.engine.movements.framework.Movement;
import com.example.starways.layer_game.builders.builders_game_object.weapon_builders.BuilderWeapon;
import com.example.starways.layer_game.game_objects.weapon_objects.WeaponCircular;
import com.example.starways.layer_game.logics.logics_gameobjects.GameObjectSizes;
import com.example.starways.layer_game.logics.logics_gameobjects.WeaponDamages;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Utility object used to provide appropriate enemy weapon state for each level.
        o The "enemy weapon state" is abstraction for the parameters such as:
            x Type of weapon
            x Its damage

 */
public class BuilderLevelEnemyWeapon {

    public static WeaponCircular buildWeaponCircular(int level, PhysicsEngine2DV1 engine) {
        float x0Min =  engine.getSpaceTime2D().getSpaceWidth() * 0.20f;
        float x0Max = engine.getSpaceTime2D().getSpaceWidth() * 0.80f;
        float x1Min = 0;
        float x1Max = engine.getSpaceTime2D().getSpaceHeight() * 1.25f;

        Vector2D center = new Vector2D(
                engine.getSpaceTime2D().getSpaceWidth() / 2,
                GameObjectSizes.START_0_X1
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

        Vector2D velocity;
        int damage;

        switch (level) {
            case 1: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_SLOW, ProposedSpeeds.SPEED_SLOW);
                damage = WeaponDamages.DAMAGE_0;
                break;
            }
            case 2: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_SLOW, ProposedSpeeds.SPEED_SLOW);
                damage = WeaponDamages.DAMAGE_0;
                break;
            }
            case 3: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_MEDIUM, ProposedSpeeds.SPEED_MEDIUM);
                damage = WeaponDamages.DAMAGE_1;
                break;
            }
            case 4: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_SLOW, ProposedSpeeds.SPEED_SLOW);
                damage = WeaponDamages.DAMAGE_1;
                break;
            }
            case 5: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_MEDIUM, ProposedSpeeds.SPEED_SLOW);
                damage = WeaponDamages.DAMAGE_1;
                break;
            }
            default: {
                velocity = new Vector2D(ProposedSpeeds.SPEED_SLOW, ProposedSpeeds.SPEED_SLOW);
                damage = WeaponDamages.DAMAGE_0;
            }
        }

        WeaponCircular weaponCircular = BuilderWeapon.buildWeaponCircular(
                center,
                velocity,
                new Vector2D(),
                GameObjectSizes.MASS_1,
                GameObjectSizes.WEAPON_RADIUS_2,
                engine,
                weaponMovement,
                damage,
                true,
                R.drawable.weapon_plasma_ball_2
        );
        weaponCircular.getMovement().setupVelocities(
                false,
                true,
                false,
                true
        );
        return weaponCircular;
    }
}
