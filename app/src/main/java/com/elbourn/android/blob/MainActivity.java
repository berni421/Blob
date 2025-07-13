package com.elbourn.android.blob;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.OnBackPressedCallback;

public class MainActivity extends OptionsMenu {
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "start onCreate");
        setContentView(R.layout.activity_main);

        // Handle back pressed
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // the 3 lines below replace the functionality of super.onBackPressed();
                setEnabled(false);
                getOnBackPressedDispatcher().onBackPressed();
                setEnabled(true);
                // any additional code
                Log.i(TAG, "handleOnBackPressed");

                finishAffinity();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

        Log.i(TAG, "end onCreate");
    }
}