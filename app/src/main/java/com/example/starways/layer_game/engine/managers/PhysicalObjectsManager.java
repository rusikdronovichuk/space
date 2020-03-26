package com.example.starways.layer_game.engine.managers;

import com.example.starways.layer_game.engine.general.PhysicsEngine2DV1;
import com.example.starways.layer_game.engine.physical_objects.PhysicalObject2D;

import java.util.concurrent.CopyOnWriteArrayList;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Abstract class which needs to be extended by the User.
        o Every PhysicalObject2D for which physics the User wants to be updated
            by the engine, needs to be registered here via register(PhysicalObject2D object)
            method.
        o Allows real-time changes of the objects which will be draw
            on canvas or other.
        o As player controlled object will be controlled by the player,
            it should not be registered here.

    @To be implemented by the User:
        o void update();

 */
public class PhysicalObjectsManager {

    //List of all physical objects which physics will be updated by the engine
    private CopyOnWriteArrayList<PhysicalObject2D> physicalObjects;

    protected PhysicsEngine2DV1 engine;

    public PhysicalObjectsManager(PhysicsEngine2DV1 engine) {
        this.engine = engine;
        physicalObjects = new CopyOnWriteArrayList<>();
    }

    public void updatePositions(long dt) {
        for (PhysicalObject2D physicalObject: physicalObjects
             ) {
            physicalObject.updatePositions(dt);
        }
    }

    public CopyOnWriteArrayList<PhysicalObject2D> getPhysicalObjects() {
        return physicalObjects;
    }

    public void register(PhysicalObject2D object) {
        physicalObjects.add(object);
    }

    public void unregister(PhysicalObject2D object) {
        physicalObjects.remove(object);
    }
}
