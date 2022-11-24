package com.elbourn.android.blob;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

class Processing {
    String TAG = getClass().getSimpleName();
    ASurfaceView surfaceView = null;
    ProcessingEvents processingEvents = null;
    Blobs blobs = null;

    Processing(ASurfaceView surfaceView) {
        Log.i(TAG, "start Ajob");
        this.surfaceView = surfaceView;
        AThread drawthread = new AThread(this);
        surfaceView.setThread(drawthread);
        processingEvents = new ProcessingEvents(surfaceView);
        Log.i(TAG, "end Ajob");
    }

    public void setup() {
        Log.i(TAG, "start setup");
        blobs = new Blobs(surfaceView);
        Log.i(TAG, "end setup");
    }

    public void draw() {
        Canvas canvas = surfaceView.getCanvas();
        canvas.drawColor(Color.GRAY);
        blobs.update();
        blobs.display(canvas);
        surfaceView.putCanvas(canvas);
    }
}
