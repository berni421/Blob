package com.elbourn.android.blob;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class ASurfaceView extends SurfaceView implements SurfaceHolder.Callback {

   String TAG = getClass().getSimpleName();
   AThread thread;
   SurfaceHolder holder = null;
   int width = 1024; // interim values while surface is prepared. see SurfaceChanged() below
   int height = 2048;

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
      this.width = width;
      this.height = height;
      Log.i(TAG, "end surfaceChanged");
   }

   public int getW() {
      return width;
   }

   public int getH() {
      return height;
   }

   @Override
   public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

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
}
