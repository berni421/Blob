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

    int x, y, dx, dy, displayWidth, displayHeight;
    int blobSize = 16;
    Paint paint = null;
//    RectF r = null;
    float lastTime;

    public Blob(int width, int height) {
        Log.i(TAG, "start Blob");
        Log.i(TAG, "width: " + width);
        Log.i(TAG, "height: " + height);
        this.displayWidth = width;
        this.displayHeight = height;
        x = new Random().nextInt(width-blobSize);
        y = new Random().nextInt(height-blobSize);
        dx = new Random().nextInt(width / 100) - width / 100;
        dy = new Random().nextInt(height / 100) - height / 100;
        paint = new Paint();
        paint.setColor(Color.GREEN);
//        r = new RectF(x, y, x + blobSize, y + blobSize);
        lastTime = System.currentTimeMillis();
        Log.i(TAG, "end Blob");
    }

    public void update() {
        float now = System.currentTimeMillis();
        float elapsed = (now - lastTime) / 1000.0f;
        updateBlob(elapsed);
        lastTime = now;
    }

    public void updateBlob(float elapsed) {
        Log.i(TAG, "start update");
        if (x + dx*elapsed < 0 || x + blobSize + dx*elapsed > displayWidth) {
            dx = -dx;
        }
        if (y + dy*elapsed < 0 || y + blobSize + dy*elapsed > displayHeight) {
            dy = -dy;
        }
        x = x + dx;
        y = y + dy;
//        r = new RectF(x, y, x + blobSize, y + blobSize);
        Log.i(TAG, "x: " + x);
        Log.i(TAG, "y: " + y);
        Log.i(TAG, "dx: " + dx);
        Log.i(TAG, "dy: " + dy);
        Log.i(TAG, "end update");
    }

    public void display(Canvas canvas) {
        Log.i(TAG, "start display");
        displayWidth = canvas.getWidth();
        displayHeight = canvas.getHeight();
//        canvas.drawRect(r, paint);
        canvas.drawCircle(x, y, blobSize,paint);
        Log.i(TAG, "start display");
    }
}