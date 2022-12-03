package com.elbourn.android.blob;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Blobs {
    String TAG = getClass().getSimpleName();
    static ArrayList<Blob> blobs = null;
    int numberOfBlobs = 64;
    long lastUpdateTime;
    ASurfaceView surfaceView = null;
    static float universalGravitationalConstant = (float) 6.67428e-11;
    static float smallMass = 1;                // planet
    static int smallSize = 64;                 // planet
    static float largeMass = 333000*smallMass; // sun
    static int largeSize = 2*smallSize;        // sun
    static float collisionLoss = 0.75f;


    Blobs(ASurfaceView surfaceView) {
        Log.i(TAG, "start Blobs");
        this.surfaceView = surfaceView;
        blobs = new ArrayList<Blob>();
        long now = System.currentTimeMillis();
        long stopTime = now + 2000;
        for (int i = 0; i < numberOfBlobs; i++) {
            Blob blob = new Blob(smallSize);
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
            blob = new Blob(smallSize);
            now = System.currentTimeMillis();
            if (stopTime < now) {
                Log.i(TAG, "addBlob: time limit reached");
                break;
            }
        }
        blob.setColor(Color.BLUE);
        blob.setMass(smallMass);
        blob.setSize(smallSize);
        blobs.add(blob);
    }

    public static void addBlobRaw(Blob blob) {
        blobs.add(blob);
    }

    boolean collide(Blob blobi, Blob blobj) {
        float distance = abs(PVector.dist(blobi.position, blobj.position)) - (blobi.size + blobj.size) / 2f;
        boolean collide = (distance <= 0);
        return collide;
    }

    boolean collides(Blob blob) {
        int blobCount = blobs.size();
        for (int i = 0; i < blobCount; i++) {
            Blob blobi = blobs.get(i);
            if (blob.equals(blobi)) continue;
            if (collide(blob, blobi)) {
                return true;
            }
        }
        return false;
    }

    void avoidCollisions() {
        int blobCount = blobs.size();
        for (int i = 0; i < blobCount; i++) {
            for (int j = 0; j < blobCount; j++) {
                if (i == j) continue;
                Blob blobi = blobs.get(i);
                Blob blobj = blobs.get(j);
                if (collide(blobi, blobj)) {
                    float velocity = blobi.speed.mult(collisionLoss).mag();
                    blobi.speed = PVector.sub(blobi.position, blobj.position).normalize().mult(velocity);
                }
            }
        }
    }

    public void update() {
        long now = System.currentTimeMillis();
        float elapsed = (float) (now - lastUpdateTime) / 1000;
        int blobCount = blobs.size();
        for (int i = 0; i < blobCount; i++) {
            Blob blob = blobs.get(i);
            if (blob.mass > smallMass) continue;
            PVector attraction = attraction(blob);
            blob.update(elapsed, attraction);
        }
        avoidCollisions();
        lastUpdateTime = now;
    }

    static PVector attraction(Blob blob) {
        PVector a = new PVector(0, 0);
        int blobCount = blobs.size();
        for (int i = 0; i < blobCount; i++) {
            Blob blobi = blobs.get(i);
            if (blobi.equals(blob)) continue;
            PVector direction = PVector.sub(blobi.position, blob.position);
            float distance = direction.mag();
            float fix = (float) Math.pow(10,Bar.getValue());
            Log.i("Blobs", "fix: " + fix);
            float force = fix * universalGravitationalConstant * (blobi.mass * blob.mass) / (distance * distance);
            a = PVector.add(a, direction.normalize().mult(force));
        }
        a.dump("attraction");
        return a;
    }

    void display(Canvas canvas) {
        int blobCount = blobs.size();
        for (int i = 0; i < blobCount; i++) {
            blobs.get(i).display(canvas);
        }
    }

}
