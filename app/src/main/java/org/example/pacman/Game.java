package org.example.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import static android.widget.Toast.LENGTH_LONG;

/**
 *
 * This class should contain all your game logic
 */

public class Game {
    //context is a reference to the activity
    final int UP = 1;
    final int DOWN = 2;
    final int RIGHT = 3;
    final int LEFT = 4;
    public int direction=0;
    public int ghostDirection = 0;

    final int GUP = 1;
    final int GDOWN = 2;
    final int GRIGHT = 3;
    final int GLEFT = 4;

    private Context context;
    private int points = 0;
    private TextView highScoreView;
    private int highscore = 0;

    private Bitmap pacBitmap;
    private Bitmap coinBitmap;
    private Bitmap ghostBitmap;

    //textview reference to points
    private TextView pointsView;
    private int pacx, pacy;
    private int ghostx, ghosty;

    //the list of goldcoins - initially empty
    private ArrayList<GoldCoin> coins = new ArrayList<>();

    //a reference to the gameview
    private GameView gameView;
    private int h,w; //height and width of screen

    public Ghost ghost= new Ghost();

    public boolean reset;

    public Game(Context context, TextView view, TextView highScoreView)
    {
        this.context = context;
        this.pointsView = view;
        this.highScoreView = highScoreView;

        pacBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman);
        coinBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.star);
        ghostBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost);
    }

    public void setGameView(GameView view) { this.gameView = view; }

    public void newGame()
    {
        reset = false;
        ghost.setAlive(true);

        ghostx = new Random().nextInt(900);
        ghosty = new Random().nextInt(800);

        pacx = 50;
        pacy = 400; //just some starting coordinates
        //reset the points
        if (highscore < points) {
            highscore = points;
            highScoreView.setText("High score: " + points);
        }
        points = 0;

        coins = new ArrayList<>();

        pointsView.setText(context.getResources().getString(R.string.points)+" "+points);

        for (int i = 0;i < 6;i++) {

            int randx = new Random().nextInt(900);
            int randy = new Random().nextInt(800);

            coins.add(new GoldCoin(randx, randy));
        }

        gameView.invalidate(); //redraw screen
    }

    public void setSize(int h, int w)
    {
        this.h = h;
        this.w = w;
    }

    public void movePacmanRight(int pixels)
    {
        //still within our boundaries?
        if (pacx+pixels+pacBitmap.getWidth()<w) {
            pacx = pacx + pixels;
            doCollisionCheck();
            ghostCollision();
            direction = RIGHT;
            gameView.invalidate();

        }
    }
    public void movePacmanLeft(int pixels){
        if (pacx-pixels>0){
            pacx = pacx - pixels;
            doCollisionCheck();
            ghostCollision();
            direction = LEFT;
            gameView.invalidate();

        }
    }
    public void movePacmanUp (int pixels){
        if (pacy-pixels>0){
            pacy = pacy - pixels;
            doCollisionCheck();
            ghostCollision();
            direction = UP;
            gameView.invalidate();

        }
    }
    public void movePacmanDown (int pixels){
        if (pacy+pixels+pacBitmap.getHeight()<h){
            pacy = pacy + pixels;
            doCollisionCheck();
            ghostCollision();
            direction = DOWN;
            gameView.invalidate();

        }
    }
    public void moveGhostRight(int pixels)
    {
        //still within our boundaries?
        if (ghostx+pixels+ghostBitmap.getWidth()<w) {
            ghostx = ghostx + pixels;
            ghostDirection = GRIGHT;
            gameView.invalidate();
        }
    }

    public void moveGhostLeft (int pixels){
        if (ghostx-pixels>0){
            ghostx = ghostx - pixels;
            ghostDirection = GLEFT;
            gameView.invalidate();
        }
    }
    public void moveGhostUp (int pixels){
        if (ghosty-pixels>0){
            ghosty = ghosty - pixels;
            ghostDirection = GUP;
            gameView.invalidate();

        }
    }

    public void moveGhostDown (int pixels){
        if (ghosty+pixels+ghostBitmap.getHeight()<h){
            ghosty = ghosty + pixels;
            ghostDirection = GDOWN;
            gameView.invalidate();
        }
    }

    public void doCollisionCheck()
    {
        int x = 0;
        for (GoldCoin coin : coins) {
            if(!coin.gettaken() && Math.sqrt(Math.pow((pacx+pacBitmap.getWidth()/2)-(coin.getx()+coinBitmap.getWidth()/2),2)
                    +Math.pow((pacy+pacBitmap.getHeight()/2)-(coin.gety()+coinBitmap.getHeight()/2),2)) < 50)
            {
                coin.settaken(true);
                points++;
                pointsView.setText("Points: "+Integer.toString(points));
            }
            if(points == 6) {
                Toast.makeText(this.context,"You won!",Toast.LENGTH_SHORT).show();
                reset = true;
                //newGame();
            }
            //Log.d("collision", "doCollisionCheck: "+Math.sqrt(Math.pow(pacx-coin.getx(),2)+Math.pow(pacy-coin.gety(),2)));
            //Log.d("collision", "Points: "+points);
        }
    }

    public void ghostCollision(){
        double distance = Math.sqrt(Math.pow((pacx+pacBitmap.getWidth()/2)-(ghostx+ghostBitmap.getWidth()/2),2)
                         +Math.pow((pacy+pacBitmap.getHeight()/2)-(ghosty+ghostBitmap.getHeight()/2),2));
        Log.d("dist",distance+"");
        if(distance<60){
            if(ghost.getAlive() == true){
                Toast.makeText(this.context, "You lost.", Toast.LENGTH_SHORT).show();
                reset = true;
                //newGame();
            }
        }
    }

    public int getPacx() { return pacx; }
    public int getPacy() { return pacy; }
    public Bitmap getPacBitmap() { return pacBitmap; }

    public int getPoints() { return points; }

    public ArrayList<GoldCoin> getCoins() { return coins; }
    public Bitmap getCoinBitmap() { return coinBitmap; }

    public int getGhostx() { return ghostx; }
    public int getGhosty() { return ghosty; }
    public Bitmap getGhost(){return ghostBitmap; }

}

