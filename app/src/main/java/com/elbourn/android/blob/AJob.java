package com.elbourn.android.blob;

import android.util.Log;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

public class AJob extends AppCompatActivity {
    String TAG = getClass().getSimpleName();

    SurfaceView surfaceView = null;
    AThread athread = null;
    Blob blob = null;

    AJob(ASurfaceView surfaceView) {
        Log.i(TAG, "start Ajob");
        this.surfaceView = surfaceView;
        blob = new Blob(surfaceView.getW(), surfaceView.getH());
        Log.i(TAG, "end Ajob");
    }

    public void task() {
        Log.i(TAG, "start task");
        try {
            // put job here
            blob.update();
            blob.display(surfaceView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "end task");
    }

    public void setThread(AThread athread) {
        this.athread = athread;
    }
}
