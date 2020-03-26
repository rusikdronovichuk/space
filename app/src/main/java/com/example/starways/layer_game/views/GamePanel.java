package com.example.starways.layer_game.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.starways.commons.device_info.ScreenParameters;
import com.example.starways.layer_game.engine.constants.ProposedScales;
import com.example.starways.layer_game.engine.constants.ProposedTimes;
import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.logics.logics_general.GameloopThread;
import com.example.starways.layer_game.managers.SceneManager;
import com.example.starways.layer_game.media.sounds.GameSoundPool;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Responsible for the overall game.
        o All of the GameLoops logic goes via this class. It holds Engine, Gameloop Thread
            which callback will update, render and rest, this class and via it, SceneManager
            and overall scene.
        o The object of this class is here chosen to be a type of SurfaceView.

 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private int level;

    private GameloopThread gameloopThread;
    private SceneManager manager;
    private PhysicsEngine2DV1 engine;

    private GameSoundPool soundPool;

    public GamePanel(Context context) {
        super(context);
        initialize(context);
        setFocusable(true);
    }

    public GamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
        setFocusable(true);
    }

    public GamePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
        setFocusable(true);
    }

    public void setLevel(int level) { this.level = level; }

    public void prepare() {
        manager.prepare();
    }

    public void destroyGamePanel() {
        boolean retry = true;
        while(retry) {
            try {
                soundPool.soundPoolRelease();
                gameloopThread.setRunning(false);
                gameloopThread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        manager.draw(canvas);
        postInvalidate();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        soundPool = new GameSoundPool();
        engine = new PhysicsEngine2DV1(
                ScreenParameters.SCREEN_WIDTH,
                ScreenParameters.SCREEN_HEIGHT,
                ProposedTimes.DT_FAST,
                ScreenParameters.SCREEN_WIDTH,
                ProposedScales.MEDIUM_SCALE,
                ProposedScales.MEDIUM_SCALE
        );
        manager = new SceneManager(engine, soundPool, level);

        if (gameloopThread == null) {
            gameloopThread = new GameloopThread(
                    engine,
                    holder,
                    this
            );
            gameloopThread.setRunning(true);
            gameloopThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        destroyGamePanel();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.receiveTouch(event);
        return true;
    }

    private void initialize(Context context) {
        getHolder().addCallback(this);
        ScreenParameters.CURRENT_CONTEXT = context;
    }
}
