package com.elbourn.android.blob;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.abs;

class Blobs {
    String TAG = getClass().getSimpleName();
    static ArrayList<Blob> blobs = null;
    int numberOfBlobs = 16;
    long lastUpdateTime;
    ASurfaceView surfaceView = null;

    Blobs (ASurfaceView surfaceView) {
        Log.i(TAG, "start Blobs");
        this.surfaceView = surfaceView;
        blobs = new ArrayList<Blob>();
        long now = System.currentTimeMillis();
        long stopTime = now + 2000;
        for (int i = 0; i < numberOfBlobs; i++) {
            Blob blob = new Blob();
            addBlob(blob);
            now = System.currentTimeMillis();
            if (stopTime < now) {
                Log.i(TAG, "Blobs: time limit reached");
                break;
            }
        }
        Log.i(TAG, "end Blobs");
        lastUpdateTime = System.currentTimeMillis();
    }

    public void addBlob(Blob blob) {
        long now = System.currentTimeMillis();
        long stopTime = now + 2000;
        while (collides(blob)) {
            blob = new Blob();
            now = System.currentTimeMillis();
            if (stopTime < now) {
                Log.i(TAG, "addBlob: time limit reached");
                break;
            }
        }
        blobs.add(blob);
    }

    public void addBlobRaw(Blob blob) {
        blobs.add(blob);
    }

    boolean collide(Blob blobi, Blob blobj) {
        float distance = PVector.dist(blobi.position, blobj.position);
        return abs(distance) < Blob.blobSize;
    }

    boolean collides(Blob blob) {
        int  blobCount = blobs.size();
        for (int i=0; i<blobCount; i++) {
            Blob blobi = blobs.get(i);
            if (collide(blob, blobi)) {
                return true;
            }
        }
        return false;
    }

    void avoid(Blob blobi, Blob blobj) {
        float velocityi = blobi.speed.mag();
        PVector newDirectioni = PVector.sub(blobi.position, blobj.position).normalize();
        blobi.speed = newDirectioni.mult(velocityi);

        float velocityj = blobj.speed.mag();
        PVector newDirectionj = PVector.sub(blobj.position, blobi.position).normalize();
        blobj.speed = newDirectionj.mult(velocityj);
    }

    void avoidCollisions() {
        int  blobCount = blobs.size();
        for (int i=0; i<blobCount; i++) {
            for (int j=0; j<blobCount; j++) {
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
        float elapsed = (now - lastUpdateTime) / 1000f;
        Log.i(TAG, "now: " + now);
        Log.i(TAG, "elapsed: " + elapsed);
        Log.i(TAG, "lastTime: " + lastUpdateTime);
        int  blobCount = blobs.size();
        for (int i=0; i<blobCount; i++) {
            blobs.get(i).update(elapsed);
        }
        avoidCollisions();
        lastUpdateTime = now;
    }

    static PVector attraction(Blob blob) {
        PVector a = new PVector(0,0);
        int  blobCount = blobs.size();
        for (int i=0; i<blobCount; i++) {
            Blob blobi = blobs.get(i);
            if (blobi.paint.getColor() == Color.RED) {
                PVector direction = PVector.sub(blobi.position, blob.position);
                a = PVector.add(a, direction);
            }
        }
        float dSize = a.mag();
        float aSize = 0;
        float fixup = 4*ASurfaceView.getH()*ASurfaceView.getW();
        if (dSize != 0) {
            aSize = (fixup) / (dSize * dSize);
            a = a.normalize().mult(aSize);
        }
        Log.i("Blobs", "aSize: " + aSize);
        a.dump("attraction");
        return a;
    }

    void display(Canvas canvas) {
        int  blobCount = blobs.size();
        for (int i=0; i<blobCount; i++) {
            blobs.get(i).display(canvas);
        }
    }
}
