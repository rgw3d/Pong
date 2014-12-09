package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

public class Board extends JPanel
{
	private Paddle Paddle1, Paddle2;
    private Ball Ball1;
    private Wall WallTop,WallBottom,WallLeft,WallRight;
    private KeyControl KeyControl;
    private ArrayList<GameObject> GameObjects;
	private Timer TickTimer;
	private int BoardWidth;
	private int BoardHeight;
    private int PaddleWidth = 10;
    private int PaddleHeight = 70;
    private int BallWidth = 25;
    private int BallHeight = 25;
    private int WallWidth = 20;
    private int PaddleSpeed = 2;
    private int PaddleDistanceFromWall = 100;
    private Collision collisionDetector;
    private int Paddle1WinCount = 0;
    private int Paddle2WinCount = 0;
    private GameState State = Board.GameState.play;
    private float FontSize = 200f;
    private long TimeDelayAfterWin =3000;//miliseconds

    private Date ResetDate = null;

	
	public Board(int width, int height) {
        setFocusable(true);
        setVisible(true);
        setBackground(Color.DARK_GRAY);
        setDoubleBuffered(true);

        BoardWidth = width;
        BoardHeight = height;

        Paddle1 = new Paddle(PaddleDistanceFromWall, BoardHeight /2, BoardWidth, BoardHeight,PaddleSpeed,WallWidth, PaddleWidth, PaddleHeight);
        Paddle2 = new Paddle(BoardWidth - PaddleDistanceFromWall, BoardHeight /2, BoardWidth, BoardHeight, PaddleSpeed,WallWidth,PaddleWidth,PaddleHeight);
        Ball1 = new Ball(BoardWidth /2, BoardHeight /2, BoardWidth, BoardHeight,2, getState() ,BallWidth , BallHeight);

        WallTop = new Wall(0, 0, BoardWidth,WallWidth);
        WallBottom = new Wall(0, BoardHeight -55, BoardWidth,WallWidth);
        WallRight = new Wall(BoardWidth - WallWidth,0,WallWidth, BoardHeight);
        WallLeft = new Wall(0,0, WallWidth, BoardHeight);


        collisionDetector = new Collision(Paddle1,Paddle2, Ball1,WallTop,WallBottom,WallLeft,WallRight,this);

        KeyControl = new KeyControl(Paddle1,Paddle2);
		addKeyListener(KeyControl);

        TickTimer = new Timer(5,new Update());
		TickTimer.start();

	}

    public class Update implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(State == GameState.play) {
                Paddle1.move();
                Paddle2.move();
                Ball1.move();
                collisionDetector.detectCollision();
            }
            else if(State == GameState.paddle1Win || State == GameState.paddle2Win) {

                if(State==GameState.paddle1Win) {
                    State = GameState.paddle1Serve;
                    Paddle1WinCount++;
                }
                else {
                    State = GameState.paddle2Serve;
                    Paddle2WinCount++;
                }
            }
            else if(State == GameState.paddle1Serve || State == GameState.paddle2Serve){
                if(ResetDate == null){
                    ResetDate = new Date();
                }
                else{
                    long time = (new Date()).getTime()-ResetDate.getTime();
                    if(time>=TimeDelayAfterWin){
                        State = GameState.play;
                        reset();
                    }
                }

            }

            repaint();



        }
    }
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;

        g.setFont(g.getFont().deriveFont(FontSize));

        g2d.drawString(""+Paddle1WinCount,BoardWidth/8,BoardHeight/4);
        g2d.drawString(""+Paddle2WinCount,BoardWidth*3/4,BoardHeight/4);
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect(Paddle1.getX(), Paddle1.getY(), PaddleWidth, PaddleHeight);
		g2d.fillRect(Paddle2.getX(), Paddle2.getY(), PaddleWidth, PaddleHeight);


        g2d.setColor(Color.BLUE);
        g2d.fillRect(WallTop.getX(), WallTop.getY(), WallTop.getObjWidth(),WallTop.getObjHeight());
        g2d.fillRect(WallBottom.getX(),WallBottom.getY(), WallBottom.getObjWidth(),WallBottom.getObjHeight());
        g2d.fillRect(WallLeft.getX(), WallLeft.getY(), WallLeft.getObjWidth(),WallLeft.getObjHeight());
        g2d.fillRect(WallRight.getX(),WallRight.getY(), WallRight.getObjWidth(),WallRight.getObjHeight());

        g2d.setColor(Color.CYAN);
        g2d.fillRect(BoardWidth /2-15,0,30, BoardHeight);

        g2d.setColor(Color.MAGENTA);
        g2d.fillOval(Ball1.getX(), Ball1.getY(), BallWidth, BallHeight);
		
		Toolkit.getDefaultToolkit().sync();

		g.dispose();
	}
	
	/*public void addNotify() {
		super.addNotify();
		BoardWidth = getWidth();
		BoardHeight = getHeight();
	}
*/
    public GameState getState(){
        return State;
    }

    public void setState(GameState state) {
        State = state;
    }

    public enum GameState {
        play,
        paddle1Serve,
        paddle2Serve,
        paddle1Win,
        paddle2Win,
        gameOver
    }

    public void reset(){
        Paddle1 = new Paddle(PaddleDistanceFromWall, BoardHeight /2, BoardWidth, BoardHeight,PaddleSpeed,WallWidth, PaddleWidth, PaddleHeight);
        Paddle2 = new Paddle(BoardWidth - PaddleDistanceFromWall, BoardHeight /2, BoardWidth, BoardHeight, PaddleSpeed,WallWidth,PaddleWidth,PaddleHeight);
        Ball1 = new Ball(BoardWidth /2, BoardHeight /2, BoardWidth, BoardHeight,2, getState() ,BallWidth , BallHeight);
        collisionDetector = new Collision(Paddle1,Paddle2, Ball1,WallTop,WallBottom,WallLeft,WallRight,this);
        removeKeyListener(KeyControl);
        KeyControl = new KeyControl(Paddle1,Paddle2);
        ResetDate = null;
        addKeyListener(KeyControl);

    }


}



