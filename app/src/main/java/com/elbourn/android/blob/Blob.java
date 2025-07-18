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
        int minWidth = 2*size;
        int maxWidth = ASurfaceView.getW() - 2 * size;
        if (maxWidth < 1) maxWidth = ASurfaceView.getW();
        int x = minWidth + new Random().nextInt(maxWidth);
        int minHeight = 2*size;
        int maxHeight = ASurfaceView.getH() - 2 * size;
        if (maxHeight < 1) maxHeight = ASurfaceView.getH();
        int y = minHeight + new Random().nextInt(maxHeight);
        setPosition(x, y);
        setSize(size);
        paint = new Paint();
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
        position = new PVector(x, y);
    }

    public void setSpeed(int x, int y) {
        speed.set(x, y);
    }

    public void update(float elapsed, PVector attraction) {
//        Log.i(TAG, "start updateBlob");
        PVector elapsedSpeed = PVector.add(speed, attraction).mult(elapsed);
        if (elapsedSpeed.mag() > size) elapsedSpeed.normalize().mult(size);
        PVector proposedPosition = PVector.add(position, elapsedSpeed);
        if (proposedPosition.x < size/2 || proposedPosition.x + size/2 > ASurfaceView.getW()) {
            speed = new PVector(-(speed.x+attraction.x), speed.y+attraction.y)
                    .mult(Blobs.collisionLoss);
            if (speed.mag() > size) speed.normalize().mult(size);
            elapsedSpeed = speed.mult(elapsed);
        }
        if (proposedPosition.y < size/2 || proposedPosition.y + size/2 > ASurfaceView.getH()) {
            speed = new PVector(speed.x+attraction.x, -(speed.y+attraction.y))
                    .mult(Blobs.collisionLoss);
            if (speed.mag() > size) speed.normalize().mult(size);
            elapsedSpeed = speed.mult(elapsed);
        }
        position = PVector.add(position, elapsedSpeed);
        speed.dump("speed");
//        Log.i(TAG, "end updateBlob");
    }

    public void display(Canvas canvas) {
//        Log.i(TAG, "start display");
        canvas.drawCircle(position.x, position.y, size/2, paint);
//        Log.i(TAG, "end display");
    }
}