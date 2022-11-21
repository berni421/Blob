package com.elbourn.android.blob;

import android.util.Log;

public class PVector {
    String TAG = getClass().getSimpleName();
    float x;
    float y;

    PVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PVector set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public PVector copy() {
        return new PVector(x, y);
    }

    public PVector get() {
        return copy();
    }

    public PVector negate() {
        return new PVector(-x, -y);
    }

    public PVector mult(float m) {
        return new PVector(x*m, y*m);
    }

    public static PVector xmult(PVector v1, PVector v2) {
        return new PVector(v1.x * v2.x, v1.y * v2.y);
    }

    public float mag() {
        return (float) Math.sqrt(x*x + y*y);
    }

    static public PVector add(PVector v1, PVector v2) {
        return new PVector(v1.x + v2.x, v1.y + v2.y);
    }

    static public PVector sub(PVector v1, PVector v2) {
        return new PVector(v1.x - v2.x, v1.y - v2.y);
    }

    static public float dist(PVector v1, PVector v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    public float heading() {
        return (float) Math.atan2(y, x);
    }

    public void dump(String label) {
        Log.i(TAG, label + " x,y: " + x + "," + y);
    }

    public PVector normalize() {
        float m = mag();
        if (m != 0 && m != 1) {
            div(m);
        }
        return this;
    }

    public PVector div(float n) {
        x /= n;
        y /= n;
        return this;
    }
}
