package com.example.starways.layer_game.managers;

import android.util.Log;
import android.view.MotionEvent;

import com.example.starways.layer_game.engine.mathematics.Vector2D;
import com.example.starways.layer_game.views.GameplayScene;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Responsible for handling motion events passed from the scene.

    @INFO:
        o Moving (player, ID) will be replaced by the Move and that input,
            when it will be implemented.

 */
public class SceneMotionEventManager {

    private static final int POINTER_ID_EMPTY = - 1;

    private boolean movingPlayer = false;

    private int pointerMoveID;
    private int pointerMovingID;

    private GameplayScene scene;

    public SceneMotionEventManager(GameplayScene scene) {
        this.scene = scene;
        pointerMoveID = POINTER_ID_EMPTY;
        pointerMovingID = POINTER_ID_EMPTY;
    }

    public void receiveTouch(MotionEvent motionEvent) {
        int pointerIndex = motionEvent.getActionIndex();
        int pointerID = motionEvent.getPointerId(pointerIndex);
        int maskedAction = motionEvent.getActionMasked();

        Vector2D point = new Vector2D(motionEvent.getX(), motionEvent.getY());

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                //Remembering the exact pointers
                // which are over the buttonMove and player GameObject

                if (scene.getSceneAgents().getPlayer().getRectangle().contains(
                        point.getX0(),
                        point.getX1())
                ) {
                    movingPlayer = true;
                    pointerMovingID = pointerID;

                    //DEBUG
                    Log.d("POINTERS", "MovingID detected " + pointerMovingID);
                }

                if (scene.getButtonMove().contains(point)) {
                    scene.getButtonMove().press();
                    pointerMoveID = pointerID;

                    //DEBUG
                    Log.d("POINTERS", "MoveID detected " + pointerMoveID);
                }

                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (pointerID == pointerMovingID) {
                    if (movingPlayer)
                        scene.getSceneAgents().getPlayer().updatePositions(point.getX0(), point.getX1());
                }

                if (pointerID == pointerMoveID) {
                    if (scene.getButtonMove().contains(point)) {
                        //TO DO
                    }
                }
                break;
            }

            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP: {
                if (pointerID == pointerMovingID) {
                    movingPlayer = false;
                    pointerMovingID = POINTER_ID_EMPTY;

                    //DEBUG
                    Log.d("POINTERS", "MovingID released " + pointerMovingID);
                }

                if (pointerID == pointerMoveID) {
                    if (scene.getButtonMove().contains(point)) {
                        pointerMoveID = POINTER_ID_EMPTY;
                        scene.getButtonMove().release();

                        //DEBUG
                        Log.d("POINTERS", "MoveID released " + pointerMoveID);
                    }
                }

                //Important only when released so no need to track ID of pointer over this
                //button, for now
                if (pointerID != pointerMoveID && pointerID != pointerMovingID) {
                    if (scene.getButtonShoot().contains(point)) {
                        scene.getSceneAgents().getPlayer().prepareWeapon();
                    }
                }

                break;
            }
        }
    }
}
