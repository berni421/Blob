package com.elbourn.android.blob;

import android.graphics.Canvas;

import java.util.ArrayList;

import static java.lang.Math.abs;

class Blobs {
    String TAG = getClass().getSimpleName();
    ArrayList<Blob> blobs = new ArrayList<Blob>();
    int numberOfBlobs = 512;

    Blobs (ASurfaceView surfaceview) {
        for (int i = 0; i < numberOfBlobs; i++) {
            Blob blob = new Blob(surfaceview.getW(), surfaceview.getH());
            if (collides(blob, blobs)) {
                i--;
            } else {
                blobs.add(blob);
            }
        }
    }

    boolean collide(Blob blobi, Blob blobj) {
        int ix = blobi.x;
        int iy = blobi.y;
        int jx = blobj.x;
        int jy = blobj.y;
        int blobsize = blobi.blobSize;
        if (abs(ix - jx) < blobsize && abs(iy - jy) < blobsize) {
            return true;
        }
        return false;
    }

    boolean collides(Blob blob, ArrayList<Blob> blobs) {
        for (int i=0; i<blobs.size(); i++) {
            Blob blobi = blobs.get(i);
            if (collide(blob, blobi)) {
                return true;
            }
        }
        return false;
    }

    void avoid(Blob blobi, Blob blobj) {
        blobi.dx = -blobi.dx;
        blobi.dy = -blobi.dy;
        blobj.dx = -blobj.dx;
        blobj.dy = -blobj.dy;
        blobj.update();
        blobi.update();
    }

    void avoidCollision(ArrayList<Blob> blobs) {
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

    void update() {
        for (int i = 0; i < blobs.size(); i++) {
            blobs.get(i).update();
        }
        avoidCollision(blobs);
    }

    void display(Canvas canvas) {
        for (int i = 0; i < blobs.size(); i++) {
            blobs.get(i).display(canvas);
        }
    }
}
