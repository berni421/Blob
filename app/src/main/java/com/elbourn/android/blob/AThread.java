package com.elbourn.android.blob;

import android.util.Log;
import android.view.MotionEvent;

public class AThread extends Thread {

    String TAG = getClass().getSimpleName();
    private Thread thread = null;
    private boolean threadStarted = false;
    private boolean stopRequested = false;
    private Processing job = null;
    int maxLoop = 1000;
    long loopSleepTime = 100;
    long runTime = 60000;

    AThread(Processing job) {
        Log.i(TAG, "start AThread");
        this.job = job;
        Log.i(TAG, "end AThread");
    }

    public synchronized void startThread() {
        if (threadStarted)
            throw new IllegalStateException("Worker Thread may only be started once and is already running!");
        thread = new Thread(this::runWorkingLoop);
        thread.start();
        threadStarted = true;
    }

    private void runWorkingLoop() {
        Log.i(TAG, "start runWorkingLoop");
        int loop = 0;
        long endTime = System.currentTimeMillis() + runTime;
        if (!stopRequested) {
            try {
                job.setup(); // needs a limit
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (!stopRequested) {
            try {
                Log.i(TAG, "Iteration: " + loop);
                job.draw();
                Thread.sleep(loopSleepTime);
                loop++;
                if (loop > maxLoop) {
                    stopThread();
                }
                long now = System.currentTimeMillis();
//                Log.i(TAG, "now: " + now);
                if (now > endTime) {
                    stopThread();
                }
            } catch (final Exception e) {
                e.printStackTrace();
                break;
            }
        }
        Log.i(TAG, "end runWorkingLoop");
    }

    public synchronized void stopThread() {
        if (!threadStarted)
            throw new IllegalStateException("Worker Thread is not even running yet!");
        stopRequested = true;
        thread.interrupt();
    }
}
