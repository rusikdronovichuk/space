package com.example.starways.layer_game.builders.builders_game_object.weapon_builders;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.engine.movements.framework.Movement;
import com.example.starways.layer_game.game_objects.weapon_objects.WeaponCircular;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Builder with static methods used to build Weapon objects
        o Components of these objects such as movements and resourceID,
            need to be instantiated outside of this builder and passed as an arguments.

 */
public class BuilderWeapon {

    public static WeaponCircular buildWeaponCircular(
            Vector2D center,
            Vector2D velocity,
            Vector2D acceleration,
            float mass,
            float radius,
            PhysicsEngine2DV1 engine,
            Movement movement,
            int damage,
            boolean enemyWeapon,
            int resourceID
    ) {
        WeaponCircular weapon = new WeaponCircular(
                center,
                velocity,
                acceleration,
                mass,
                radius,
                engine
        );
        weapon.setupMovement(movement);
        weapon.setDamage(damage);
        weapon.setEnemyWeapon(enemyWeapon);
        weapon.setupSprite(resourceID);

        return weapon;
    }
}
