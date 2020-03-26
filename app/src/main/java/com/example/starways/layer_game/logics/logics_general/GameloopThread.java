package com.example.starways.layer_game.logics.logics_general;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.general.TimeflowThread;
import com.example.starways.layer_game.views.GamePanel;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Concrete class used to implement TimeflowThread which will drive the Engine
            and via that drive the updates, rendering and other important methods for the
            GameLoop.

 */
public class GameloopThread extends TimeflowThread {

    private static Canvas canvas = null;

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;

    public GameloopThread(
            PhysicsEngine2DV1 physicsEngine2DV1,
            SurfaceHolder surfaceHolder,
            GamePanel gamePanel
    ) {
        super(physicsEngine2DV1);
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    protected void work() {
        long currentTime = System.currentTimeMillis();
        long dt = currentTime - lastTime;
        canvas = null;
        if (dt > this.physicsEngine2DV1.getSpaceTime2D().getDt()) {
            lastTime = currentTime;
            try {
                canvas = surfaceHolder.lockCanvas();

                //Maybe better to better identify why is this happening (lockCanvas returns null)
                // When "Back button" is pressed which causes surface to be destroyed
                // and which causes that here non-null surfaceHolder gotten from constructor
                // now returns null canvas
                if (canvas != null) {
                    synchronized (surfaceHolder) {
                        update(dt);
                        prepare();
                        rendering(canvas);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private void update(long dt) {
        this.physicsEngine2DV1.updatePositions(dt);
    }

    private void prepare() {
        gamePanel.prepare();
    }

    private void rendering(Canvas canvas) {
        gamePanel.draw(canvas);
    }
}
