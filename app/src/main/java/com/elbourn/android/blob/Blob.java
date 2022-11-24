package com.elbourn.android.blob;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class Blob {

    String TAG = getClass().getSimpleName();

    PVector position, speed;
    Paint paint = null;
    float mass;
    int size;

    Blob(int size) {
        Log.i(TAG, "start Blob");
        int x = new Random().nextInt(ASurfaceView.getW() - 2 * size) + size;
        int y = new Random().nextInt( ASurfaceView.getH() - 2 * size) + size;
        position = new PVector(x, y);
        paint = new Paint();
        setSize(size);
        Log.i(TAG, "end Blob");
    }

    void setSize(int size) {
        this.size = size;
        int dx = new Random().nextInt(2*size) - size;
        int dy = new Random().nextInt(2*size) - size;
        speed = new PVector(dx, dy);
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setPosition(int x, int y) {
        position.set(x, y);
    }

    public void setSpeed(int x, int y) {
        speed.set(x, y);
    }

    public void update(float elapsed, PVector attraction) {
        Log.i(TAG, "start updateBlob");
        if (speed == null) return;
        PVector elapsedSpeed = PVector.add(speed.mult(elapsed), attraction);
        if (elapsedSpeed.mag() > size) {
            elapsedSpeed.normalize().mult(size);
        }
        PVector newPosition = PVector.add(position, elapsedSpeed);
        if (newPosition.x < size/2 || newPosition.x + size/2 > ASurfaceView.getW()) {
            speed = new PVector(-speed.x - attraction.x, speed.y + attraction.y);
            position = PVector.add(position, speed.mult(elapsed));
            return;
        }
        if (newPosition.y < size/2 || newPosition.y + size/2 > ASurfaceView.getH()) {
            speed = new PVector(speed.x + attraction.x, -speed.y - attraction.y);
            position = PVector.add(position, speed.mult(elapsed));
            return;
        }
        position = newPosition;
        Log.i(TAG, "end updateBlob");
    }

    public void display(Canvas canvas) {
        Log.i(TAG, "start display");
        canvas.drawCircle(position.x, position.y, size/2, paint);
        Log.i(TAG, "end display");
    }
}