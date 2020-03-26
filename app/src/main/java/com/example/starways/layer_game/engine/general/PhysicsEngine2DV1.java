package com.example.starways.layer_game.engine.general;

import com.example.starways.layer_game.engine.managers.PhysicalObjectsManager;
import com.example.starways.layer_game.engine.physics_data.SpaceTime2D;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Class designed as Facade to the Physics engine system
        o It's responsibility is to update positions of the instances of the PhysicalObjects2D,
            as collision management should be done outside, because of its' the potential impact
            on non-physical parts such as health bars, etc, and as player object will be
            controlled by the player, which is in majority of the cases
            outside of PhysicsEngine2DV1 responsibility.

 */
public class PhysicsEngine2DV1 {

    private boolean ended;

    //Space-time data
    private SpaceTime2D spaceTime2D;

    //Physical objects management
    private PhysicalObjectsManager manager;

    public PhysicsEngine2DV1(
            float spaceWidth,
            float spaceHeight,
            long dt,
            float dx,
            int timeScale,
            int spaceScale
    ) {
        spaceTime2D = new SpaceTime2D(
                spaceWidth,
                spaceHeight,
                dt,
                dx,
                timeScale,
                spaceScale
        );
        manager = new PhysicalObjectsManager(this);
        ended = false;
    }

    public void setEnded() {
        ended = true;
    }

    public SpaceTime2D getSpaceTime2D() {
        return spaceTime2D;
    }

    public void setSpaceTime2D(SpaceTime2D spaceTime2D) {
        this.spaceTime2D = spaceTime2D;
    }

    public PhysicalObjectsManager getManager() {
        return manager;
    }

    public void updatePositions(long dt) {
        if (ended == false)
            manager.updatePositions(dt);
    }

}
