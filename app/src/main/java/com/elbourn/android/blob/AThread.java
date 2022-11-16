package com.elbourn.android.blob;

import android.util.Log;

public class AThread extends Thread {

    String TAG = getClass().getSimpleName();
    private Thread thread = null;
    private boolean threadStarted = false;
    private boolean stopRequested = false;

    private AJob job = null;

    AThread(AJob job) {
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
        int iteration = 0;
        int maxIteration = 1000;
        int loopSleepTime = 10;
        while (!stopRequested) {
            try {
                Log.i(TAG, "Iteration: " + iteration);
                job.task();
                Thread.sleep(loopSleepTime);
                iteration++;
                if (iteration > maxIteration) {
                    stopThread();
                }
            } catch (final Exception e) {
                break;
            }
        }
    }

    public synchronized void stopThread() {
        if (!threadStarted)
            throw new IllegalStateException("Worker Thread is not even running yet!");
        stopRequested = true;
        thread.interrupt();
    }
}
