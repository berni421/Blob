package com.elbourn.android.blob;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

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
        SeekBar seekBar = findViewById(R.id.seekBar01);
        TextView textView = findViewById(R.id.textView01);
        new Bar(seekBar, textView);
        ASurfaceView surfaceView = findViewById(R.id.surfaceView01);
        new Processing(surfaceView);
        Log.i(TAG, "end onResume");
    }
    

}