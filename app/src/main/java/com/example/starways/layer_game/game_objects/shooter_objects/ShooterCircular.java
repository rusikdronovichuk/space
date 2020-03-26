package com.example.starways.layer_game.game_objects.shooter_objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.framework.GameObject;
import com.example.starways.layer_game.game_objects.weapon_objects.WeaponCircular;
import com.example.starways.layer_game.builders.builders_scene.general_builders.SpriteFactory;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Shooter used to fire circular weapons.
        o Weapon which will be fired is field weapon
            and fired weapons will be copies of that field.

 */
public class ShooterCircular implements GameObject {

    private static final long FIRING_TIME_0 = 500;

    private boolean firing;

    private int fire;
    private int empty;

    private long timeFireStarted;

    private RectF rectangle;
    private Bitmap sprite;

    private WeaponCircular weapon;

    public ShooterCircular(WeaponCircular weapon, int resourceID) {
        this.weapon = weapon;
        empty = resourceID;
        fire = resourceID;
        sprite = SpriteFactory.getBitmap(resourceID);
        firing = false;
    }

    public ShooterCircular(WeaponCircular weapon, int empty, int fire) {
        this.weapon = weapon;
        this.empty = empty;
        this.fire = fire;
        sprite = SpriteFactory.getBitmap(this.empty);
        firing = false;
    }

    public void prepareFire() {
        sprite = SpriteFactory.getBitmap(fire);
        firing = true;
        timeFireStarted = System.currentTimeMillis();
    }

    public void setRectangle(RectF rectangle) { this.rectangle = rectangle; }

    public WeaponCircular getWeapon() {
        return weapon;
    }

    public void setupWeapon(WeaponCircular weapon) {
        this.weapon = weapon;
    }

    public WeaponCircular shootWeapon(Vector2D center) {
        WeaponCircular newWeapon = weapon.getCopy();
        newWeapon.setCenter(center);

        return newWeapon;
    }

    @Override
    public void draw(Canvas canvas) {
        if (firing) {
            if (System.currentTimeMillis() - timeFireStarted >= FIRING_TIME_0) {
                firing = false;
                sprite = SpriteFactory.getBitmap(empty);
            }
        }
        canvas.drawBitmap(
                sprite,
                null,
                rectangle,
                null
        );
    }
}
