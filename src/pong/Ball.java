package pong;

import java.awt.*;
import java.util.Random;

/**
 * Created by rgw3d on 12/3/2014.
 * Used for the ball
 */
public class Ball implements GameObject {

    private double x,y;
    private int Magnitude = 2;
    private int Angle = 0;//in degrees
    private int BoardWidth, BoardHeight;
    private int BallWidth,BallHeight;
    private int MaxMagnitude = 8;

    /**
     * @param distFromLeft distance from either the right or left wall.
     * @param distFromTop distance from either the top or the bottom.
     * @param boardWidth width of the board
     * @param boardHeight height of the board
     * @param magnitude magnitude of the ball moves. if set to zero, the default of 2 is used.
     * @param ballWidth width that the ball is drawn
     * @param ballHeight height that the ball is drawn
     */
    public Ball(int distFromLeft, int distFromTop,  int boardWidth, int boardHeight, int magnitude, Board.GameState state, int ballWidth, int ballHeight) {

        x = distFromLeft;
        y = distFromTop;

        BoardWidth = boardWidth;
        BoardHeight = boardHeight;

        if (magnitude != 0) {
            Magnitude = magnitude;
        }

        if(state == Board.GameState.paddle1Serve){
            Angle = 45 - (int)(90 * (new Random()).nextDouble());
        }
        else if(state == Board.GameState.paddle2Serve){
            Angle = 225 - (int)(90 * (new Random()).nextDouble());
        }
        else{
            if((new Random()).nextDouble()>.5)
                Angle = 45 - (int)(90 * (new Random()).nextDouble());
            else
                Angle = 225 - (int)(90 * (new Random()).nextDouble());
        }

        BallWidth = ballWidth;
        BallHeight = ballHeight;

    }


    public int getX() {
        return  (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getObjWidth() { return BallWidth;}

    public int getObjHeight() { return BallHeight; }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, BallWidth, BallHeight);
    }

    public void move() {
        y+=Math.sin(Math.toRadians(Angle))*Magnitude;
        x+=Math.cos(Math.toRadians(Angle))*Magnitude;

    }

    public int getAngle() { return Angle; }

    public void incrementMag(double x){
        if(Magnitude<MaxMagnitude)
            Magnitude+=x;
    }
    public void changeAngle(int x){
        Angle = x;
    }





}
