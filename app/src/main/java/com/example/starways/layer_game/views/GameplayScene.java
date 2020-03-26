package com.example.starways.layer_game.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.framework.Scene;
import com.example.starways.layer_game.game_objects.rest_objects.ResultText;
import com.example.starways.layer_game.inputs.ButtonMove;
import com.example.starways.layer_game.inputs.ButtonShoot;
import com.example.starways.layer_game.logics.logics_gameobjects.SceneAgents;
import com.example.starways.layer_game.logics.logics_general.GameStatusValues;
import com.example.starways.layer_game.builders.builders_scene.general_builders.BuilderScene;
import com.example.starways.layer_game.managers.SceneMotionEventManager;
import com.example.starways.layer_game.managers.SceneManager;
import com.example.starways.layer_game.media.sounds.GameSoundPool;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Responsible for everything that needs to be rendered - all of the objects, etc.
        o Also responsible for the User inputs.

 */
public class GameplayScene implements Scene {

    private boolean gameOver = false;
    private boolean playerLost = false;

    private boolean resultSoundPlayed = false;
    private boolean gameEngineSetEnded = false;

    private int level;

    private RectF backgroundRect;
    private Bitmap backgroundSprite;

    private ResultText resultText;

    private ButtonShoot buttonShoot;
    private ButtonMove buttonMove;
    private SceneAgents sceneAgents;

    private SceneMotionEventManager eventManager;

    private GameSoundPool soundPool;
    private PhysicsEngine2DV1 engine;

    public GameplayScene(PhysicsEngine2DV1 engine, GameSoundPool soundPool, int level) {
        reset(engine, soundPool, level);
    }

    public ButtonShoot getButtonShoot() { return buttonShoot; }

    public ButtonMove getButtonMove() { return buttonMove; }

    public SceneAgents getSceneAgents() { return sceneAgents; }

    @Override
    public void prepare() {
        sceneAgents.getManager().shootWeapons(engine);
        int status = sceneAgents.getManager().updateHealths();
        if (status != GameStatusValues.GAME_NOT_OVER) {
            gameOver = true;
            if (status == GameStatusValues.PLAYER_WON) {
                playerLost = false;
            } else {
                playerLost = true;
            }
        }
        sceneAgents.getManager().removeWeapons();
    }

    @Override
    public void draw(Canvas canvas) {
        if (gameOver) {
            drawTextCenter(canvas);
            if (gameEngineSetEnded == false) {
                engine.setEnded();
                gameEngineSetEnded = true;
            }

            if (resultSoundPlayed == false) {
                if (playerLost)
                    soundPool.playLose();
                else
                    soundPool.playWin();
                resultSoundPlayed = true;
            }

        } else {
            canvas.drawBitmap(backgroundSprite, null, backgroundRect, null);
            sceneAgents.getManager().draw(canvas);
            buttonShoot.draw(canvas);
            buttonMove.draw(canvas);
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent motionEvent) { eventManager.receiveTouch(motionEvent); }

    //  Each time a new object is created,
    //  it needs to be register into both engine's object manager (for update)
    // and GameObjectManager (for drawing)
    private void reset(PhysicsEngine2DV1 engine, GameSoundPool soundPool, int level){
        this.level = level;

        resultText = new ResultText();

        backgroundSprite = BuilderScene.buildSceneBackground(level);
        backgroundRect = new RectF(
                0,
                0,
                engine.getSpaceTime2D().getSpaceWidth(),
                engine.getSpaceTime2D().getSpaceHeight()
        );

        this.engine = engine;
        this.eventManager = new SceneMotionEventManager(this);

        initializeObjects(soundPool);
        initializeSounds(soundPool);
    }

    private void drawTextCenter(Canvas canvas) { resultText.drawTextCenter(canvas, playerLost); }

    private void initializeSounds(GameSoundPool soundPool) {
        this.soundPool = soundPool;
        this.soundPool.playPortal();
    }

    private void initializeObjects(GameSoundPool soundPool) {
        buttonShoot = BuilderScene.buildButtonShoot(engine);
        buttonMove = BuilderScene.buildButtonMove(engine);
        sceneAgents = new SceneAgents(level, soundPool, engine);
    }
}
