package com.elbourn.android.blob;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowInsets;

import androidx.annotation.NonNull;

public class ASurfaceView extends SurfaceView implements SurfaceHolder.Callback {

   String TAG = getClass().getSimpleName();
   AThread thread;
   SurfaceHolder holder = null;
   static int[] width = {1024}; // interim values while surface is prepared. see SurfaceChanged() below
   static int[] height = {2048};

   public ASurfaceView(Context context, AttributeSet attrs) {
      super(context, attrs);
      Log.i(TAG, "start ASurfaceView");
      holder = getHolder();
      holder.addCallback(this);
      Log.i(TAG, "start ASurfaceView");
   }

   void setThread(AThread thread) {
      this.thread = thread;
   }

   @Override
   public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
      Log.i(TAG, "start surfaceCreated");
      if (thread != null) {
         thread.startThread();
      }
      Log.i(TAG, "end surfaceCreated");
   }

   @Override
   public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
      Log.i(TAG, "start surfaceChanged");
      this.width[0] = width;
      this.height[0] = height;
      Log.i(TAG, "end surfaceChanged");
   }

   public static int getW() {
      return width[0];
   }

   public static int getH() {
      return height[0];
   }

   @Override
   public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
      if (thread != null) {
         thread.stopThread();
      }
   }

   public Canvas getCanvas() {
      Log.i(TAG, "start getCanvas");
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         return holder.lockHardwareCanvas();
      } else {
         return holder.lockCanvas(null);
      }
   }

   public void putCanvas(Canvas canvas) {
      Log.i(TAG, "start putCanvas");
      holder.unlockCanvasAndPost(canvas);
   }

   @Override
   public void onWindowFocusChanged(boolean hasWindowFocus) {
      super.onWindowFocusChanged(hasWindowFocus);
      if (thread != null) {
         if (!hasWindowFocus)
            thread.stopThread();
      }
   }

   @Override
   public boolean performClick() {
      Log.i(TAG, "start performClick");
      return super.performClick();
   }

   public static int statusBarHeight(android.content.res.Resources res) {
      return (int) (24 * res.getDisplayMetrics().density);
   }
}

