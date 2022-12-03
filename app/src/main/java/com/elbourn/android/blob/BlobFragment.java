package com.elbourn.android.blob;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BlobFragment extends Fragment {

    static String APP = BuildConfig.APPLICATION_ID;
    static String TAG = Fragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blob, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "start onViewCreated");
        startAndSetup(view);
        Log.i(TAG, "end onViewCreated");
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
