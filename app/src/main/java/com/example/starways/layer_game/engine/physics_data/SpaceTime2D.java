package com.example.starways.layer_game.engine.physics_data;

/*
    @Author:
        Vasilije Becic
    @Description:
        o Class responsible for holding all the necessary information
            about 2 dimensional space-time of the game.
        o dt, dx will be used by TimeflowThread extensions and movements
            to generate necessary things such as on-time update and draw calls etc.
 */
public class SpaceTime2D {

    //Space size data
    private float spaceWidth;
    private float spaceHeight;

    //Space-time flow data
    private long dt;
    private float dx;

    //If the user wants relative movement
    private int timeScale;
    private int spaceScale;
    private float unitSpeed;

    public SpaceTime2D(
            float spaceWidth,
            float spaceHeight,
            long dt,
            float dx,
            int timeScale,
            int spaceScale
    ) {
        this.spaceWidth = spaceWidth;
        this.spaceHeight = spaceHeight;
        this.dt = dt;
        this.dx = dx;
        this.timeScale = timeScale;
        this.spaceScale = spaceScale;
        this.unitSpeed = dx / dt;
    }

    public float getSpaceWidth() {
        return spaceWidth;
    }

    public void setSpaceWidth(float spaceWidth) {
        this.spaceWidth = spaceWidth;
    }

    public float getSpaceHeight() {
        return spaceHeight;
    }

    public void setSpaceHeight(float spaceHeight) {
        this.spaceHeight = spaceHeight;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public int getTimeScale() {
        return timeScale;
    }

    public void setTimeScale(int timeScale) {
        this.timeScale = timeScale;
    }

    public int getSpaceScale() {
        return spaceScale;
    }

    public void setSpaceScale(int spaceScale) {
        this.spaceScale = spaceScale;
    }

    public float getUnitSpeed() {
        return unitSpeed;
    }

    public void setUnitSpeed(float unitSpeed) {
        this.unitSpeed = unitSpeed;
    }
}
