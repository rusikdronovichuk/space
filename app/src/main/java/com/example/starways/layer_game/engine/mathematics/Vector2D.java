package com.example.starways.layer_game.engine.mathematics;

public class Vector2D {

    private float x0;
    private float x1;

    public Vector2D() {
        x0 = 0;
        x1 = 0;
    }

    public Vector2D(float x0, float x1) {
        this.x0 = x0;
        this.x1 = x1;
    }

    public float getX0() {
        return x0;
    }

    public void setX0(float x0) {
        this.x0 = x0;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public Vector2D getCopy() {
        return new Vector2D(x0, x1);
    }

    public void add(Vector2D vector2D) {
        x0 += vector2D.x0;
        x1 += vector2D.x1;
    }

    public void subtract(Vector2D vector2D) {
        x0 -= vector2D.x0;
        x1 -= vector2D.x1;
    }

    public void multiplyWithScalar(float k) {
        x0 *= k;
        x1 *= k;
    }

    public static Vector2D add(Vector2D vector1, Vector2D vector2) {
        Vector2D vector2D = new Vector2D();
        vector2D.add(vector1);
        vector2D.add(vector2);
        return vector2D;
    }

    public static Vector2D subtract(Vector2D vector1, Vector2D vector2) {
        Vector2D vector2D = new Vector2D();
        vector2D.add(vector1);
        vector2D.subtract(vector2);
        return vector2D;
    }

    public static float dotProduct(Vector2D vector1, Vector2D vector2) {
        return vector1.x0 * vector2.x0 + vector1.x1 + vector2.x1;
    }

    public float length() {
        return (float)Math.sqrt(x0 * x0 + x1 * x1);
    }

    public static float distance(Vector2D vector1, Vector2D vector2) {
        return (float)Math.sqrt(
                        (vector1.x0 - vector2.x0) * (vector1.x0 - vector2.x0)
                        +
                        (vector1.x1 - vector2.x1) * (vector1.x1 - vector2.x1)
        );
    }
}
