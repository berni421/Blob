package com.elbourn.android.blob;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class Blob {

    String TAG = getClass().getSimpleName();

    int x, y, dx, dy, displayWidth, displayHeight;
    int blobSize = 128;
    Paint paint = null;
    RectF r = null;

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
        paint.setColor(Color.RED);
        r = new RectF(x, y, x + blobSize, y + blobSize);
        Log.i(TAG, "end Blob");
    }

    public void update() {
        Log.i(TAG, "start update");
        if (x + dx < 0 || x + blobSize + dx > displayWidth) {
            dx = -dx;
        }
        if (y + dy < 0 || y + blobSize + dy > displayHeight) {
            dy = -dy;
        }
        x = x + dx;
        y = y + dy;
        r = new RectF(x, y, x + 128, y + 128);
        Log.i(TAG, "x: " + x);
        Log.i(TAG, "y: " + y);
        Log.i(TAG, "dx: " + dx);
        Log.i(TAG, "dy: " + dy);
        Log.i(TAG, "start update");
    }

    public void display(SurfaceView surfaceView) {
        Canvas canvas = surfaceView.getHolder().lockCanvas(null);
        displayWidth = canvas.getWidth();
        displayHeight = canvas.getHeight();
        canvas.drawColor(Color.GRAY);
        canvas.drawRoundRect(r, blobSize/4,blobSize/4, paint);
        surfaceView.getHolder().unlockCanvasAndPost(canvas);
    }
}