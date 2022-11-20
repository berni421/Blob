package com.elbourn.android.blob;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "start onCreate");
        setContentView(R.layout.activity_main);
        Log.i(TAG, "end onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "start onResume");
        ASurfaceView surfaceView = findViewById(R.id.surfaceview01);
        new Processing(surfaceView);
        Log.i(TAG, "end onResume");
    }
}