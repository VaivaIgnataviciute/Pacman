package org.example.pacman;

/**
 * This class should contain information about a single GoldCoin.
 * such as x and y coordinates (int) and whether or not the goldcoin
 * has been taken (boolean)
 */

public class GoldCoin {
    private int coinx;
    private int coiny;
    private boolean taken;

    public void setx(int x) {
        this.coinx = x;
    }
    public int getx() {
        return coinx;
    }
    public void sety(int y) {
        this.coinx = y;
    }
    public int gety() {
        return coiny;
    }
    public void settaken(boolean t) {
        this.taken = t;
    }
    public boolean gettaken() {
        return taken;
    }


    public GoldCoin(int coinx, int coiny)
    {
        this.coinx = coinx;
        this.coiny = coiny;
        this.taken = false;
    }

}
