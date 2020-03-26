package com.example.starways.layer_game.game_objects.enemy_objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.engine.movements.framework.Movement;
import com.example.starways.layer_game.engine.physical_objects.PhysicalObject2D;
import com.example.starways.layer_game.engine.physical_objects.RectanglePhysicalObject;
import com.example.starways.layer_game.engine.physics_data.CollisionsChecker;
import com.example.starways.layer_game.framework.GameObject;
import com.example.starways.layer_game.game_objects.rest_objects.HealthBar;
import com.example.starways.layer_game.game_objects.shooter_objects.ShooterCircular;
import com.example.starways.layer_game.game_objects.weapon_objects.WeaponCircular;
import com.example.starways.layer_game.builders.builders_scene.general_builders.SpriteFactory;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Represents any kind of Enemy GameObject which is in the shape of the rectangle.
        o RectanglePhysicalObject provides the physics, interface GameObject ensures that
            this object must be rendered.
        o It consists of a physical object with its movement, HealthBar object and ShooterObject.
        o All of the components (Shooter, Movement)
            are built outside of this class and are passed into this class's
            object as already finished instances.

 */
public class RectangleEnemyGameObject extends RectanglePhysicalObject implements GameObject {

    private boolean weaponPrepared;

    private long lastTimeShooting;
    private long shootingTimeInterval;

    private Bitmap sprite;

    private HealthBar healthBar;

    private Movement movement;
    private ShooterCircular shooterCircular;

    public RectangleEnemyGameObject(
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

    public void setupCircularShooter(ShooterCircular shooterCircular) {
        this.shooterCircular = shooterCircular;
        this.shooterCircular.setRectangle(rectangle);
    }

    public void setupSprite(int resourceID) { sprite = SpriteFactory.getBitmap(resourceID); }

    public void setupTime(long shootingTimeInterval) {
        this.shootingTimeInterval = shootingTimeInterval;
        lastTimeShooting = System.currentTimeMillis();
    }

    public void setupMovement(Movement movement) {
        this.movement = movement;
        this.movement.registerPhysicalObject(this);
    }

    public void setupHealthBar(int health) {
        healthBar = new HealthBar(
                health,
                center.getX0() - width / 2,
                center.getX1() - height * 3 / 5
        );
    }

    public int getHealth() {
        return healthBar.getHealth();
    }

    public void prepareWeapon() { this.weaponPrepared = true; }

    public boolean isWeaponPrepared() {
        return weaponPrepared;
    }

    public Movement getMovement() { return movement; }

    public WeaponCircular shootWeapon() {
        weaponPrepared = false;
        return shooterCircular.shootWeapon(center);
    }

    @Override
    public void updatePositions(long dt) {
        movement.updatePosition(dt);

        rectangle.left = center.getX0() - width / 2;
        rectangle.top = center.getX1() - height / 2;
        rectangle.right = center.getX0() + width / 2;
        rectangle.bottom = center.getX1() + height / 2;

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeShooting >= shootingTimeInterval) {
            lastTimeShooting = currentTime;
            weaponPrepared = true;
        }

        //Health bar position needs to be updated
        updateHealth();
    }

    @Override
    public boolean checkAndPhysicsResolveCollision(PhysicalObject2D physicalObject2D) {
        boolean collided = false;
        if (physicalObject2D instanceof WeaponCircular) {
            WeaponCircular plasma = (WeaponCircular) physicalObject2D;

            if (plasma.isEnemyWeapon() == false) {
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
        float x0 = center.getX0() - width / 2;
        float x1 = center.getX1() - height * 3 / 5;

        healthBar.updateHealth(x0, x1);
    }
}
