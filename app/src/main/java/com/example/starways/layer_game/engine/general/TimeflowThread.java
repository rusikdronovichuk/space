package com.example.starways.layer_game.engine.general;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Abstract class which needs to be extended by the User.
        o An instance of SpaceTime2D must be passed to the constructor
            in this thread extension to allow this thread to be aware of
            required time increment.
        o Allows time-flow (game loop) for the whole game.
        o Work needs to be implemented by the User and it should contain types of
            update method, prepare method and rendering method
        o Update should be a wrapper around game engines wrapper
        o Prepare method should implement some additional logic such as
            collision management, etc.
        o Rendering method should implement some rendering (f.e. draw() etc)

    @To be implemented by the User:
        o void work()

 */
public abstract class TimeflowThread extends Thread {

    private volatile boolean running;
    protected long lastTime;

    protected  PhysicsEngine2DV1 physicsEngine2DV1;

    public TimeflowThread(PhysicsEngine2DV1 physicsEngine2DV1) {
        this.physicsEngine2DV1 = physicsEngine2DV1;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        lastTime = System.currentTimeMillis();
        this.running = running;
    }

    @Override
    public void run() {
        while(running) {
            work();
        }
    }

    //Needs to be implemented by the User
    abstract protected void work();
}
