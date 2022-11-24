package com.elbourn.android.blob;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

class ProcessingEvents {
    String TAG = getClass().getSimpleName();
    int blobsRemaining = 3;
    long lastTime = System.currentTimeMillis();
    Blobs blobs = null;

    ProcessingEvents(ASurfaceView surfaceView) {
        this.blobs = blobs;
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int yFix = getStatusBarHeight(Resources.getSystem().newTheme().getResources());
                    actionOnTouch(event.getRawX(), event.getRawY() - yFix);
                    return true;
                }
                return surfaceView.performClick();
            }
        });
    }

    void setBlobs(Blobs blobs) {
        this.blobs = blobs;
    }


    void actionOnTouch(float x, float y) {
        Log.i(TAG, "start actionOntouch");
        Log.i(TAG, "Touch x,y: " + x + "," + y);
        long now = System.currentTimeMillis();
        long readyTime = lastTime + 1000;
        long diff = now - readyTime;
        Log.i(TAG, "diff: " + diff);
        if (now < readyTime) {
            Log.i(TAG, "too soon for another red blob.");
            return;
        }
        if (blobsRemaining == 0) {
            Log.i(TAG, "no red blobs remaining");
            return;
        }
        // add special blob to Blobs
        Blob blob = new Blob(Blobs.largeSize);
        blob.setPosition((int) x, (int) y - blob.size);
        blob.setSpeed(0, 0);
        blob.setColor(Color.YELLOW);
        blob.setMass(Blobs.largeMass);
        Blobs.addBlobRaw(blob);
        blobsRemaining--;
        lastTime = System.currentTimeMillis();
        Log.i(TAG, "end actionOnTouch");
    }

    int getStatusBarHeight(android.content.res.Resources res) {
        return (int) (24 * res.getDisplayMetrics().density);
    }
}
