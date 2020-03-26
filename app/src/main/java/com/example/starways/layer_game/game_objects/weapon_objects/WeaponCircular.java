package com.example.starways.layer_game.game_objects.weapon_objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.engine.movements.framework.Movement;
import com.example.starways.layer_game.engine.physical_objects.CirclePhysicalObject;
import com.example.starways.layer_game.engine.physical_objects.PhysicalObject2D;
import com.example.starways.layer_game.framework.GameObject;
import com.example.starways.layer_game.builders.builders_game_object.weapon_builders.BuilderWeapon;
import com.example.starways.layer_game.builders.builders_scene.general_builders.SpriteFactory;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Represents circular shaped weapon
        o Should be build in steps
            (Like for the rest GameObjects,
            Builder class and its method parameters can be used to ensure that the all
            needed parameters for proper weapon construction are passed by the User):
                x Constructor is called first, next four steps second
                x Damage needs to be set
                x Movement needs to be set
                x Sprite needs to be set
                x EnemyWeapon indicator needs to be set for the damage update

 */
public class WeaponCircular extends CirclePhysicalObject implements GameObject {

    //Indicator to object manager if weapons object exited the screen
    // (so it needs to be terminated)
    private boolean end;
    private boolean enemyWeapon;

    private int damage;
    private int resourceID; //Used for copy

    private Bitmap sprite;

    private Movement movement;

    public WeaponCircular(
            Vector2D center,
            Vector2D velocity,
            Vector2D acceleration,
            float mass,
            float radius,
            PhysicsEngine2DV1 engine
    ) {
        super(center, velocity, acceleration, mass, radius, engine);
        end = false;
    }

    public boolean isEnd() {
        return end;
    }

    //In order for other non-weapon game objects to signal Weapon that they collided
    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isEnemyWeapon() { return enemyWeapon; }

    public void setEnemyWeapon(boolean enemyWeapon) { this.enemyWeapon = enemyWeapon; }

    public int getDamage() { return damage; }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setupMovement(Movement movement) {
        this.movement = movement;
        this.movement.registerPhysicalObject(this);
    }

    public void setupSprite(int resourceID) {
        this.resourceID = resourceID;
        sprite = SpriteFactory.getBitmap(resourceID);
    }

    public Movement getMovement() { return movement; }

    public WeaponCircular getCopy() {
        return BuilderWeapon.buildWeaponCircular(
                center,
                velocity,
                acceleration,
                mass,
                radius,
                engine,
                movement.getCopy(),
                damage,
                enemyWeapon,
                resourceID
        );
    }

    @Override
    public void updatePositions(long dt) {
        movement.updatePosition(dt);

        if (movement.isBouncing() == false) {
            if (
                            (center.getX0() < movement.getX0Min())
                            ||
                            (center.getX0() > movement.getX0Max())
                            ||
                            (center.getX1() < movement.getX1Min())
                            ||
                            (center.getX1() > movement.getX1Max())
            ) {
                end = true;
            }
        }
    }

    @Override
    public boolean checkAndPhysicsResolveCollision(PhysicalObject2D physicalObject2D) {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        RectF rectangle = new RectF(
                center.getX0() - radius,
                center.getX1() - radius,
                center.getX0() + radius,
                center.getX1() + radius
        );
        canvas.drawBitmap(
                sprite,
                null,
                rectangle,
                null
        );
    }
}
