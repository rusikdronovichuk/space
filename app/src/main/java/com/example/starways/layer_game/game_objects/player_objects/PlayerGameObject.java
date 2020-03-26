package com.example.starways.layer_game.game_objects.player_objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.physical_objects.PhysicalObject2D;
import com.example.starways.layer_game.engine.physical_objects.RectanglePhysicalObject;
import com.example.starways.layer_game.engine.physics_data.CollisionsChecker;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.framework.GameObject;
import com.example.starways.layer_game.game_objects.rest_objects.HealthBar;
import com.example.starways.layer_game.game_objects.shooter_objects.ShooterCircular;
import com.example.starways.layer_game.game_objects.weapon_objects.WeaponCircular;
import com.example.starways.layer_game.builders.builders_scene.general_builders.SpriteFactory;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Represents User controlled object in the shape of the Rectangle
        o RectanglePhysicalObject provides the physics, interface GameObject ensures that
            this object must be rendered.
        o It consists of a physical object, HealthBar object and ShooterObject.
        o All of the components (Shooter) are built outside of this class
            and are passed into this class's object as already finished instances.
        o As movement is upon the User, no Movement is needed.

 */
public class PlayerGameObject extends RectanglePhysicalObject implements GameObject {

    private float x0Min;
    private float x0Max;
    private float x1Min;
    private float x1Max;

    private boolean weaponPrepared;

    private Bitmap sprite;

    private HealthBar healthBar;

    private ShooterCircular shooterCircular;

    public PlayerGameObject(
            Vector2D center,
            Vector2D velocity,
            Vector2D acceleration,
            float mass,
            PhysicsEngine2DV1 engine,
            float width,
            float height
    ) {
        super(center, velocity, acceleration, mass, engine, width, height);
        shooterCircular = null;
        weaponPrepared = false;
    }

    public void setupBoundaries(float x0Min, float x0Max, float x1Min, float x1Max) {
        this.x0Min = x0Min;
        this.x0Max = x0Max;
        this.x1Min = x1Min;
        this.x1Max = x1Max;
    }

    public void setupCircularShooter(ShooterCircular shooterCircular) {
        this.shooterCircular = shooterCircular;
        this.shooterCircular.setRectangle(rectangle);
    }

    public void setupSprite(int resourceID) { sprite = SpriteFactory.getBitmap(resourceID); }

    public void setupHealthBar(int health) {
        healthBar = new HealthBar(
                health,
                center.getX0() - width / 2,
                center.getX1() - height / 2 - 10
        );
    }

    public int getHealth() {
        return healthBar.getHealth();
    }

    public void prepareWeapon() {
        this.weaponPrepared = true;
        this.shooterCircular.prepareFire();
    }

    public boolean isWeaponPrepared() {
        return weaponPrepared;
    }

    public WeaponCircular shootWeapon() {
        weaponPrepared = false;
        return shooterCircular.shootWeapon(center);
    }

    //Special update method for PlayerGameObject
    public void updatePositions(float x0, float x1) {
        if (x0 < x0Min)
            x0 = x0Min;
        if (x0 > x0Max)
            x0 = x0Max;

        if (x1 < x1Min)
            x1 = x1Min;
        if (x1 > x1Max)
            x1 = x1Max;

        center.setX0(x0);
        center.setX1(x1);
        rectangle.set(
                x0 - width / 2,
                x1 - height / 2,
                x0 + width / 2,
                x1 + height / 2
        );
    }

    @Override
    public void updatePositions(long dt) {
        //In order to get real-time update (meaning via game engines thread) for the health bar
        updateHealth();
    }

    @Override
    public boolean checkAndPhysicsResolveCollision(PhysicalObject2D physicalObject2D) {
        boolean collided = false;
        if (physicalObject2D instanceof WeaponCircular) {
            WeaponCircular plasma = (WeaponCircular) physicalObject2D;

            if (plasma.isEnemyWeapon()) {
                collided = CollisionsChecker.checkCollisionRectangleCircle(
                        center,
                        width,
                        height,
                        plasma.getCenter(),
                        plasma.getRadius()
                );
                if (collided) {
                    healthBar.lowerHealth(plasma.getDamage());
                }
            }
        }
        return collided;
    }

    @Override
    public void draw(Canvas canvas) {
        shooterCircular.draw(canvas);
        canvas.drawBitmap(
                sprite,
                null,
                rectangle,
                null
        );
        healthBar.draw(canvas);
    }


    private void updateHealth() {
        float x0 = center.getX0() - width / 2 + 10;
        float x1 = center.getX1() + height / 2 + 10;

        healthBar.updateHealth(x0, x1);
    }

}
