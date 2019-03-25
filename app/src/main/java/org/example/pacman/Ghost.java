package org.example.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ghost {

    private int ghostx;
    private int ghosty;
    private Bitmap ghostBitmap;
    public boolean isAlive;
    public int lastChangedDirectionTime;

    public Ghost(int ghostx, int ghosty, Context context) {
        this.ghostx = ghostx;
        this.ghosty = ghosty;
        this.isAlive = true;
        this.lastChangedDirectionTime = 0;
    }

    public Ghost (){}


    public int getGhostx() {
        return ghostx;
    }
    public void setGhostx(int ghostx) {
        this.ghostx = ghostx;
    }

    public int getGhosty() {
        return ghosty;
    }
    public void setGhosty(int ghosty) {
        this.ghosty = ghosty;
    }

    public boolean getAlive(){
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getlcdt(){
        return lastChangedDirectionTime;
    }
    public void setlcdt(int lastChangedDirectionTime) { lastChangedDirectionTime = lastChangedDirectionTime; }


}
