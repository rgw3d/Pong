package pong;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by rgw3d on 12/4/2014.
 * used for collision detection.
 * detectCollision() must be called to test
 */
public class Collision {

    Board Board;
    Paddle Paddle1, Paddle2;
    Wall Top, Bottom, Left, Right;
    ArrayList<Ball> Balls = new ArrayList<Ball>();

    private double DegreeDeflectionRange = 30;


    public Collision(Paddle paddle1, Paddle paddle2, ArrayList<Ball> balls, Wall top, Wall bottom, Wall left, Wall right, Board board) {
        Paddle1 = paddle1;
        Paddle2 = paddle2;
        Balls = balls;
        Top = top;
        Bottom = bottom;
        Left = left;
        Right = right;
        Board = board;
    }

    public void detectCollision() {
        for (Ball ballObject : Balls) {

            Rectangle paddle1Rect = Paddle1.getBounds();
            Rectangle paddle2Rect = Paddle2.getBounds();
            Rectangle topWall = Top.getBounds();
            Rectangle botWall = Bottom.getBounds();
            Rectangle leftWall = Left.getBounds();
            Rectangle rightWall = Right.getBounds();
            Rectangle ballRect = ballObject.getBounds();

            if (paddle1Rect.intersects(ballRect) || paddle2Rect.intersects(ballRect) || leftWall.intersects(ballRect) || rightWall.intersects(ballRect)) {

                if (leftWall.intersects(ballRect) || rightWall.intersects(ballRect)) {
                    if (leftWall.intersects(ballRect))
                        Board.setState(pong.Board.GameState.paddle2Game);
                    else if (rightWall.intersects(ballRect))
                        Board.setState(pong.Board.GameState.paddle1Game);
                    return;
                }


                changeAngle(ballRect, paddle1Rect, paddle2Rect, ballObject);
                ballObject.incrementMag(1);

                while (paddle1Rect.intersects(ballRect) || paddle2Rect.intersects(ballRect)) {
                    ballObject.move();
                    paddle1Rect = Paddle1.getBounds();
                    paddle2Rect = Paddle2.getBounds();
                    ballRect = ballObject.getBounds();
                }
            } else if (topWall.intersects(ballRect) || botWall.intersects(ballRect)) {
                System.out.println(ballObject.getAngle());
                ballObject.changeAngle(-ballObject.getAngle());
                System.out.println(ballObject.getAngle());
                while (topWall.intersects(ballRect) || botWall.intersects(ballRect)) {
                    ballObject.move();
                    ballRect = ballObject.getBounds();
                }
            }
        }
    }

    public void changeAngle(Rectangle b1, Rectangle p1, Rectangle p2, Ball ballObject) {
        if (p1.intersects(b1)) {
            System.out.println(ballObject.getAngle());
            double p1Y = p1.getY() + p1.getHeight() / 2;
            double b1Y = b1.getY() + b1.getHeight() / 2;
            double displacment = b1Y - p1Y;
            ballObject.changeAngle((int) (displacment * (DegreeDeflectionRange / (p1.getHeight() / 2))));
            System.out.println(ballObject.getAngle());
        } else if (p2.intersects(b1)) {
            System.out.println(ballObject.getAngle());
            double p2Y = p2.getY() + p2.getHeight() / 2;
            double b1Y = b1.getY() + b1.getHeight() / 2;
            double displacment = p2Y - b1Y;
            ballObject.changeAngle(180 + (int) (displacment * (DegreeDeflectionRange / (p2.getHeight() / 2))));
            System.out.println(ballObject.getAngle());
        } else {
            ballObject.changeAngle(-ballObject.getAngle() + 180);
        }

    }
}
