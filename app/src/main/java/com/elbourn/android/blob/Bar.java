package com.elbourn.android.blob;

import android.widget.SeekBar;
import android.widget.TextView;

public class Bar {
    static SeekBar[] seekBar = {null};
    TextView textView;
    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            textView.setText("" + progress);
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    Bar(SeekBar seekBar, TextView textView) {
        this.seekBar[0] = seekBar;
        this.textView = textView;
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        int progress = seekBar.getProgress();
        textView.setText(""+progress);
    }

    public static int getValue() {
        return seekBar[0].getProgress();
    }
}
