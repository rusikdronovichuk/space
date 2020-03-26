package com.example.starways.layer_game.engine.physics_data;

import com.example.starways.layer_game.engine.mathematics.Vector2D;

public class CollisionsChecker {

    public static boolean checkCollisionRectangleCircle(
            Vector2D rectangle,
            float width,
            float height,
            Vector2D circle,
            float radius
    ) {
        Vector2D r = rectangle.getCopy(); //Making sure that no change to the circle is made
        Vector2D c = circle.getCopy();  //Same as for rectangle

        //Normalising coordinate system (new basis is rectangle center)
        c.setX0(Math.abs(c.getX0() - r.getX0()));
        c.setX1(Math.abs(c.getX1() - r.getX1()));
        r.setX0(0);
        r.setX1(0);

        if (c.getX1() <= height / 2) {
            if (c.getX0() <= (width / 2 + radius))
                return true;
            return false;
        }

        if (c.getX0() <= width / 2) {
            if (c.getX1() <= (height / 2 + radius))
                return true;
            return false;
        }

        if (
                (c.getX0() <= (width / 2 + radius))
                &&
                (c.getX1() <= (height / 2 + radius))
        ) {
            Vector2D node = new Vector2D(width / 2, height / 2);
            float distance = Vector2D.distance(node, c);
            if (distance <= radius)
                return true;
        }
        return false;
    }
}
