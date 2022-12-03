package com.elbourn.android.blob;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ConcurrentModificationException;

import androidx.appcompat.app.AppCompatActivity;

class Processing {
    String TAG = getClass().getSimpleName();
    ASurfaceView surfaceView = null;
    ProcessingEvents processingEvents = null;
    Blobs blobs = null;

    Processing(Context context, ASurfaceView surfaceView) {
        Log.i(TAG, "start Ajob");
        this.surfaceView = surfaceView;
        AThread drawthread = new AThread(this);
        surfaceView.setThread(drawthread);
        processingEvents = new ProcessingEvents(context, surfaceView);
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
