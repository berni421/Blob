package com.elbourn.android.blob;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.abs;

class Blobs {
    String TAG = getClass().getSimpleName();
    ArrayList<Blob> blobs = null;
    int numberOfBlobs = 8;
    long lastTime;

    Blobs (ASurfaceView surfaceview) {
        blobs = new ArrayList<Blob>();
        for (int i = 0; i < numberOfBlobs; i++) {
            Blob blob = new Blob(surfaceview.getW(), surfaceview.getH());
            if (collides(blob)) {
                i--;
            } else {
                blobs.add(blob);
            }
        }
        lastTime = System.currentTimeMillis();
    }

    boolean collide(Blob blobi, Blob blobj) {
        float distance = PVector.dist(blobi.position, blobj.position);
        return abs(distance) < Blob.blobSize;
    }

    boolean collides(Blob blob) {
        for (int i=0; i<blobs.size(); i++) {
            Blob blobi = blobs.get(i);
            if (collide(blob, blobi)) {
                return true;
            }
        }
        return false;
    }

    void avoid(Blob blobi, Blob blobj) {
        blobi.speed = blobi.speed.negate();
        blobj.speed = blobj.speed.negate();
    }

    void avoidCollisions() {
        for (int i=0; i<blobs.size(); i++) {
            for (int j=0; j<blobs.size(); j++) {
                if (i == j) break;
                Blob blobi = blobs.get(i);
                Blob blobj = blobs.get(j);
                if (collide(blobi, blobj)) {
                    avoid(blobi, blobj);
                }
            }
        }
    }

    public void update() {
        long now = System.currentTimeMillis();
        float elapsed = (now - lastTime) / 1000f;
        Log.i(TAG, "now: " + now);
        Log.i(TAG, "elapsed: " + elapsed);
        Log.i(TAG, "lastTime: " + lastTime);
        for (int i = 0; i < blobs.size(); i++) {
            blobs.get(i).update(elapsed);
        }
        avoidCollisions();
        lastTime = now;
    }

    void display(Canvas canvas) {
        for (int i = 0; i < blobs.size(); i++) {
            blobs.get(i).display(canvas);
        }
    }
}
