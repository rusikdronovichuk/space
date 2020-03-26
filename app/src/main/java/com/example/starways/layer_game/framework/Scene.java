package com.example.starways.layer_game.framework;

import android.graphics.Canvas;
import android.view.MotionEvent;


public interface Scene {
    public void prepare();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent motionEvent);
}
