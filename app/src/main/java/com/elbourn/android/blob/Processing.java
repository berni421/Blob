package com.elbourn.android.blob;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

class Processing extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    ASurfaceView surfaceview = null;
    Blobs blobs = null;

    Processing(ASurfaceView surfaceview) {
        Log.i(TAG, "start Ajob");
        this.surfaceview = surfaceview;
        AThread thread = new AThread(this);
        surfaceview.setThread(thread);
        Log.i(TAG, "end Ajob");
    }

    public void setup() {
        Log.i(TAG, "start setup");
        blobs = new Blobs(surfaceview);
        Log.i(TAG, "end setup");
    }

    public void draw() {
        Canvas canvas = surfaceview.getCanvas();
        canvas.drawColor(Color.GRAY);
        blobs.update();
        blobs.display(canvas);
        surfaceview.putCanvas(canvas);
    }


}
