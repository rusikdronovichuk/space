package com.example.starways.layer_game.managers;

import android.graphics.Canvas;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.game_objects.enemy_objects.RectangleEnemyGameObject;
import com.example.starways.layer_game.game_objects.player_objects.PlayerGameObject;
import com.example.starways.layer_game.game_objects.weapon_objects.WeaponCircular;
import com.example.starways.layer_game.logics.logics_general.GameStatusValues;
import com.example.starways.layer_game.media.sounds.GameSoundPool;
import com.example.starways.layer_game.media.sounds.SoundIDs;

import java.util.concurrent.CopyOnWriteArrayList;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Responsible for all GameObjects which needs to be drawn when the GameplayScene is on.
        o Each GameObject which needs to be drawn needs to be registered here into three
            different classes (arrays): As Player, as Enemies or as Weapons fired.
        o If other GameObjects are needed, all what is need is to add a new array of objects
            and implement simple drawing method for them which needs be called in the main
            draw(Canvas canvas) method.

 */
public class GameObjectsManager {

    private GameSoundPool soundPool;

    private PlayerGameObject player;
    private CopyOnWriteArrayList<RectangleEnemyGameObject> enemies;
    private CopyOnWriteArrayList<WeaponCircular> weapons;

    public GameObjectsManager(GameSoundPool soundPool) {
        enemies = new CopyOnWriteArrayList<>();
        weapons = new CopyOnWriteArrayList<>();
        this.soundPool = soundPool;
    }

    public void draw(Canvas canvas) {
        for (WeaponCircular weapon: weapons
        ) {
            weapon.draw(canvas);
        }

        for (RectangleEnemyGameObject enemy: enemies
             ) {
            enemy.draw(canvas);
        }

        player.draw(canvas);
    }


    public void registerPlayer(PlayerGameObject player) {
        this.player = player;
    }

    public void registerEnemy(RectangleEnemyGameObject enemy) {
        enemies.add(enemy);
    }

    public void registerWeapon(WeaponCircular weapon) {
        weapons.add(weapon);
    }

    public void shootWeapons(PhysicsEngine2DV1 engine) {
        for (RectangleEnemyGameObject enemy: enemies
        ) {
            if (enemy.isWeaponPrepared()) {
                WeaponCircular weapon = enemy.shootWeapon();

                registerWeapon(weapon);
                engine.getManager().register(weapon);

                //Enemy laser fire
                soundPool.playLaserFires(SoundIDs.LASER_FIRE_0);
            }
        }
        if (player.isWeaponPrepared()) {
            WeaponCircular weapon = player.shootWeapon();
            registerWeapon(weapon);
            engine.getManager().register(weapon);

            //Player laser fire
            soundPool.playLaserFires(SoundIDs.LASER_FIRE_1);
        }
    }

    public int updateHealths() {
        for (WeaponCircular weapon: weapons
        ) {
            if (player.checkAndPhysicsResolveCollision(weapon)) {
                weapons.remove(weapon);

                soundPool.playExplosion(SoundIDs.EXPLOSION_0);
            }
            for(RectangleEnemyGameObject enemy: enemies
                ) {
                if (enemy.checkAndPhysicsResolveCollision(weapon)) {
                    weapons.remove(weapon);

                    soundPool.playExplosion(SoundIDs.EXPLOSION_1);
                }
            }
        }

        if (player.getHealth() == 0)
            return GameStatusValues.PLAYER_LOST;

        boolean allDefeated = true;
        for (RectangleEnemyGameObject enemy: enemies
             ) {
            if (enemy.getHealth() != 0) {
                allDefeated = false;
                break;
            }
        }
        if (allDefeated)
            return GameStatusValues.PLAYER_WON;

        return GameStatusValues.GAME_NOT_OVER;
    }

    public void removeWeapons() {
        for (WeaponCircular weapon: weapons
        ) {
            if (weapon.isEnd())
                weapons.remove(weapon);
        }
    }
}
