package com.elbourn.android.blob;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BlobFragment extends Fragment {

    static String APP = BuildConfig.APPLICATION_ID;
    static String TAG = Fragment.class.getSimpleName();
    View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blob, container, false);
        return view;
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Log.i(TAG, "start onViewCreated");
////        startAndSetup(view);
//        Log.i(TAG, "end onViewCreated");
//    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "start onResume");
        if (view != null) startAndSetup(view);
        Log.i(TAG, "end onResume");
    }

    void startAndSetup(View view) {
        SeekBar seekBar = view.findViewById(R.id.seekBar01);
        TextView textView = view.findViewById(R.id.textView01);
        new Bar(seekBar, textView);
        ASurfaceView surfaceView = view.findViewById(R.id.surfaceView01);
        Context context = getActivity();
        new Processing(context, surfaceView);

    }
}
