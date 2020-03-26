package com.example.starways.layer_game.builders.builders_game_object.shooter_builders;

import com.example.starways.layer_game.game_objects.shooter_objects.ShooterCircular;
import com.example.starways.layer_game.game_objects.weapon_objects.WeaponCircular;


/*
    @Author:
        Vasilije Becic

    @Description:
        o Builder with static methods used to build Shooter objects.
        o Components of these objects such as weapons, need to be instantiated outside of this
            builder and passed as an arguments.

 */
public class BuilderShooter {

    public static ShooterCircular buildShooterCircular(
            WeaponCircular weaponCircular,
            int resourceID
    ) {
        return new ShooterCircular(weaponCircular, resourceID);
    }

    public static ShooterCircular buildShooterCircular(
            WeaponCircular weaponCircular,
            int empty,
            int fire
    ) {
        if (empty == fire)
            return new ShooterCircular(weaponCircular, empty);
        return new ShooterCircular(weaponCircular, empty, fire);
    }
}
