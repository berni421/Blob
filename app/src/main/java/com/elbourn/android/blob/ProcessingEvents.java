package com.elbourn.android.blob;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

class ProcessingEvents {
    String TAG = getClass().getSimpleName();
    int redBlobsRemaining = 3;

    ProcessingEvents(ASurfaceView surfaceView) {
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

    void actionOnTouch(float x, float y) {
        Log.i(TAG, "start actionOntouch");
        Log.i(TAG, "Touch x,y: " + x + "," + y);
        if (redBlobsRemaining > 0) {
            // add red blob to Blobs
            Blob blob = new Blob();
            blob.setPosition((int) x, (int) y - Blob.blobSize);
            blob.setSpeed(0, 0);
            blob.setColor(Color.RED);
            Processing.blobs.addBlobRaw(blob);
            redBlobsRemaining--;
        }
        Log.i(TAG, "end actionOnTouch");
    }

    int getStatusBarHeight(android.content.res.Resources res) {
        return (int) (24 * res.getDisplayMetrics().density);
    }
}
