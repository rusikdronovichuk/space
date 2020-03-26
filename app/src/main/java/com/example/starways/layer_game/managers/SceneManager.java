package com.example.starways.layer_game.managers;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.framework.Scene;
import com.example.starways.layer_game.media.sounds.GameSoundPool;
import com.example.starways.layer_game.views.GameplayScene;

import java.util.ArrayList;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Responsible for holding the proper scene when GamePanel is called.

 */
public class SceneManager {

    public static int ACTIVE_SCENE;

    private ArrayList<Scene> scenes;

    private PhysicsEngine2DV1 engine;

    public SceneManager(PhysicsEngine2DV1 engine, GameSoundPool soundPool, int level) {
        ACTIVE_SCENE = 0;
        this.engine = engine;
        scenes = new ArrayList<>();
        scenes.add(new GameplayScene(engine, soundPool, level));
    }

    public void receiveTouch(MotionEvent motionEvent) {
        scenes.get(ACTIVE_SCENE).receiveTouch(motionEvent);
    }

    public void prepare() {
        scenes.get(ACTIVE_SCENE).prepare();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
