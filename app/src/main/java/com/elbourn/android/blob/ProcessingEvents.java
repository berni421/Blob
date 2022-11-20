package com.elbourn.android.blob;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

class ProcessingEvents {
    String TAG = getClass().getSimpleName();
    int redBlobsRemaining = 3;

    ProcessingEvents(ASurfaceView surfaceView) {
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int yFix = getStatusBarHeight(surfaceView.getContext());
                actionOnTouch(surfaceView, event.getRawX(), event.getRawY()-yFix);
                surfaceView.performClick();
                return false;
            }
        });
    }

    void actionOnTouch(ASurfaceView surfaceView, float x, float y) {
        Log.i(TAG, "x: " + x);
        if (redBlobsRemaining > 0) {
            // add red blob to Blobs
            Blob blob = new Blob(surfaceView.getW(), surfaceView.getH(), Color.RED);
            blob.setPosition((int) x, (int) y - Blob.blobSize);
            blob.setSpeed(0, 0);
            Processing.blobs.addBlob(blob);
            redBlobsRemaining--;
        }
        Log.i(TAG, "y: " + y);
    }

    public static int getStatusBarHeight(final Context context) {
        final Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            return resources.getDimensionPixelSize(resourceId);
        else
            return (int) Math.ceil((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? 24 : 25) * resources.getDisplayMetrics().density);
    }
}
