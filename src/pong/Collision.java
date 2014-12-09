package pong;

import java.awt.Rectangle;

/**
 * Created by rgw3d on 12/4/2014.
 * used for collision detection.
 * detectCollision() must be called to test
 */
public class Collision {

    Board Board;
    Paddle Paddle1,Paddle2;
    Wall Top,Bottom,Left,Right;
    Ball Ball;

    private double DegreeDeflectionRange = 30;


    public Collision(Paddle paddle1, Paddle paddle2, Ball ball, Wall top, Wall bottom, Wall left, Wall right, Board board){
        Paddle1 = paddle1;
        Paddle2 = paddle2;
        Ball = ball;
        Top = top;
        Bottom = bottom;
        Left = left;
        Right = right;
        Board = board;
    }

    public void detectCollision(){
        Rectangle p1 = Paddle1.getBounds();
        Rectangle p2 = Paddle2.getBounds();
        Rectangle wTop = Top.getBounds();
        Rectangle wBot = Bottom.getBounds();
        Rectangle wLeft = Left.getBounds();
        Rectangle wRight = Right.getBounds();
        Rectangle b1 = Ball.getBounds();

        if(p1.intersects(b1) || p2.intersects(b1) || wLeft.intersects(b1) || wRight.intersects(b1)){
            if(wLeft.intersects(b1) || wRight.intersects(b1)){
                if(wLeft.intersects(b1))
                    Board.setState(pong.Board.GameState.paddle2Win);
                else if (wRight.intersects(b1))
                    Board.setState(pong.Board.GameState.paddle1Win);
                return;
            }


            changeAngle(b1, p1, p2);
            Ball.incrementMag(1);

            while(p1.intersects(b1) || p2.intersects(b1)){
                Ball.move();
                p1 = Paddle1.getBounds();
                p2 = Paddle2.getBounds();
                b1 = Ball.getBounds();
            }
        }
        else if( wTop.intersects(b1) || wBot.intersects(b1) ){
            System.out.println(Ball.getAngle());
            Ball.changeAngle(-Ball.getAngle());
            System.out.println(Ball.getAngle());
            while(wTop.intersects(b1) || wBot.intersects(b1)){
                Ball.move();
                b1 = Ball.getBounds();
            }
        }
    }

    public void changeAngle(Rectangle b1, Rectangle p1, Rectangle p2){
        if(p1.intersects(b1) ){
            System.out.println(Ball.getAngle());
            double p1Y = p1.getY()+p1.getHeight()/2;
            double b1Y = b1.getY()+b1.getHeight()/2;
            double displacment = b1Y-p1Y;
            Ball.changeAngle((int)(displacment*(DegreeDeflectionRange /(p1.getHeight()/2))));
            System.out.println(Ball.getAngle());
        }


        else if (p2.intersects(b1)) {
            System.out.println(Ball.getAngle());
            double p2Y = p2.getY() + p2.getHeight() / 2;
            double b1Y = b1.getY() + b1.getHeight() / 2;
            double displacment =  p2Y - b1Y;
            Ball.changeAngle(180 + (int) (displacment * (DegreeDeflectionRange / (p2.getHeight() / 2))));
            System.out.println(Ball.getAngle());
        }


        else{
            Ball.changeAngle(-Ball.getAngle()+180);
        }

    }
}
