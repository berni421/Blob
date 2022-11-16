package com.elbourn.android.blob;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();
    AThread thread = null;
    AJob job = null;
    ASurfaceView surfaceview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "start onCreate");
        setContentView(R.layout.activity_main);
        surfaceview = findViewById(R.id.surfaceview01);
        Log.i(TAG, "end onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "start onResume");

        job = new AJob(surfaceview);
        thread = new AThread(job);
        surfaceview.setThread(thread);
        job.setThread(thread);

        Log.i(TAG, "end onResume");
    }
}