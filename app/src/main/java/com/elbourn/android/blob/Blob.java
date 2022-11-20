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

class Blob {

    String TAG = getClass().getSimpleName();

    PVector position, speed;
    int displayWidth, displayHeight, attraction;
    public static int blobSize = 128;
    Paint paint = null;

    public Blob(int width, int height, int colour) {
        Log.i(TAG, "start Blob");
        Log.i(TAG, "width: " + width);
        Log.i(TAG, "height: " + height);
        displayWidth = width;
        displayHeight = height;
        int x = new Random().nextInt(width - 2 * blobSize) + blobSize;
        int y = new Random().nextInt(height - 2 * blobSize) + blobSize;
        position = new PVector(x, y);
        int dmax = blobSize*4;
        int dx = new Random().nextInt(dmax) + -dmax/2;
        int dy = new Random().nextInt(dmax) + -dmax/2;
        speed = new PVector(dx, dy);
        paint = new Paint();
        paint.setColor(colour);
        attraction = 0;
        Log.i(TAG, "end Blob");
    }

    public void setPosition(int x, int y) {
        position.set(x, y);
    }

    public void setSpeed(int x, int y) {
        speed.set(x, y);
    }

    public void setAttraction(int attraction) {
        this.attraction = attraction;
    }

    public void update(float elapsed) {
        Log.i(TAG, "start updateBlob");
        if (paint.getColor() != Color.GREEN) return;
        speed.dump("speed");
        PVector elapsedAttraction = Blobs.attraction(this);
        PVector elapsedSpeed = PVector.add(speed.mult(elapsed), elapsedAttraction.mult(elapsed));
        elapsedSpeed.dump("elapsedSpeed");
        PVector newSpeed = null;
        PVector newPosition = PVector.add(position, elapsedSpeed);
        if (newPosition.x < blobSize/2 || newPosition.x + blobSize/2 > displayWidth) {
            newSpeed = new PVector(-speed.x, speed.y);
        }
        if (newPosition.y < blobSize/2 || newPosition.y + blobSize/2 > displayHeight) {
            newSpeed = new PVector(speed.x, -speed.y);
        }
        if (newSpeed == null) {
            position = newPosition;
        } else {
            speed = newSpeed;
            elapsedSpeed = newSpeed.mult(elapsed);
            position = PVector.add(position, elapsedSpeed);
        }
        Log.i(TAG, "end updateBlob");
    }

    public void display(Canvas canvas) {
        Log.i(TAG, "start display");
        displayWidth = canvas.getWidth();
        displayHeight = canvas.getHeight();
        canvas.drawCircle(position.x, position.y, blobSize/2, paint);
        Log.i(TAG, "end display");
    }
}