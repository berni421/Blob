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
    int displayWidth, displayHeight;
    public static int blobSize = 256;
    Paint paint = null;

    public Blob(int width, int height) {
        Log.i(TAG, "start Blob");
        Log.i(TAG, "width: " + width);
        Log.i(TAG, "height: " + height);
        displayWidth = width;
        displayHeight = height;
        int x = new Random().nextInt(width - 2 * blobSize) + blobSize;
        int y = new Random().nextInt(height - 2 * blobSize) + blobSize;
        position = new PVector(x, y);
        int dmax = blobSize;
        int dx = new Random().nextInt(dmax) + -dmax/2;
        int dy = new Random().nextInt(dmax) + -dmax/2;
        speed = new PVector(dx, dy);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        Log.i(TAG, "end Blob");
    }

    public void update(float elapsed) {
        Log.i(TAG, "start updateBlob");
        speed.dump("speed");
        PVector elapsedSpeed = speed.mult(elapsed);
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