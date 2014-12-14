package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Board extends JPanel {
    private Paddle Paddle1, Paddle2;
    private ArrayList<Ball> Balls = null;
    private Wall WallTop, WallBottom, WallLeft, WallRight;
    private KeyControl KeyControl;
    private Timer TickTimer;
    private int BaseWidth;
    private int BaseHeight;
    private int BoardWidth;
    private int BoardHeight;
    private int PaddleWidth = 10;
    private int PaddleHeight = 70;
    private int BallWidth = 25;
    private int BallHeight = 25;
    private int WallWidth = 20;
    private int PaddleSpeed = 2;
    private int BallSpeed = 2;
    private int PaddleDistanceFromWall = 100;
    private Collision collisionDetector;
    private int Paddle1WinCount = 0;
    private int Paddle2WinCount = 0;
    private GameState State = GameState.paddle1Serve;
    private float FontSize = 200f;
    private long TimeDelayAfterWin = 3000;//miliseconds
    private Base base;
    private Date ResetDate = null;


    public Board(int width, int height) {
        setFocusable(true);
        setVisible(true);
        setBackground(Color.DARK_GRAY);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(width, height));
    }

    public void InitDimensions() {
        base = (Base) SwingUtilities.getWindowAncestor(this);
        if (base == null)
            System.out.println("Base is null!");

        BaseWidth = base.getWidth();
        BaseHeight = base.getHeight();
        BoardWidth = this.getWidth();
        BoardHeight = this.getHeight();
    }

    public void InitGameObjects() {
        Paddle1 = new Paddle(PaddleDistanceFromWall, BoardHeight / 2, BoardWidth, BoardHeight, PaddleSpeed, WallWidth, PaddleWidth, PaddleHeight);
        Paddle2 = new Paddle(BoardWidth - PaddleDistanceFromWall, BoardHeight / 2, BoardWidth, BoardHeight, PaddleSpeed, WallWidth, PaddleWidth, PaddleHeight);


        WallTop = new Wall(0, 0, BoardWidth, WallWidth);
        WallBottom = new Wall(0, BoardHeight - WallWidth, BoardWidth, WallWidth);
        WallRight = new Wall(BoardWidth - WallWidth, 0, WallWidth, BoardHeight);
        WallLeft = new Wall(0, 0, WallWidth, BoardHeight);

        Balls = new ArrayList<Ball>();
        Balls.add(new Ball(BoardWidth / 2, BoardHeight / 2, BoardWidth, BoardHeight, BallSpeed, getState(), BallWidth, BallHeight));
        //Balls.add(new Ball(BoardWidth /2, BoardHeight /2, BoardWidth, BoardHeight,BallSpeed, getState().getOpposite() ,BallWidth , BallHeight));
        collisionDetector = new Collision(Paddle1, Paddle2, Balls, WallTop, WallBottom, WallLeft, WallRight, this);

    }

    public void InitKeyListener() {
        KeyControl = new KeyControl(Paddle1, Paddle2);
        addKeyListener(KeyControl);
    }

    public void StartTimer() {
        TickTimer = new Timer(5, new Update());
        TickTimer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g.setFont(g.getFont().deriveFont(FontSize));

        g2d.drawString("" + Paddle1WinCount, BoardWidth / 8, BoardHeight / 4);
        g2d.drawString("" + Paddle2WinCount, BoardWidth * 3 / 4, BoardHeight / 4);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(Paddle1.getX(), Paddle1.getY(), PaddleWidth, PaddleHeight);
        g2d.fillRect(Paddle2.getX(), Paddle2.getY(), PaddleWidth, PaddleHeight);


        g2d.setColor(Color.BLUE);
        g2d.fillRect(WallTop.getX(), WallTop.getY(), WallTop.getObjWidth(), WallTop.getObjHeight());
        g2d.fillRect(WallBottom.getX(), WallBottom.getY(), WallBottom.getObjWidth(), WallBottom.getObjHeight());
        g2d.fillRect(WallLeft.getX(), WallLeft.getY(), WallLeft.getObjWidth(), WallLeft.getObjHeight());
        g2d.fillRect(WallRight.getX(), WallRight.getY(), WallRight.getObjWidth(), WallRight.getObjHeight());

        g2d.setColor(Color.CYAN);
        g2d.fillRect(BoardWidth / 2 - 15, 0, 30, BoardHeight);

        g2d.setColor(Color.MAGENTA);
        for (Ball i : Balls) {
            g2d.fillOval(i.getX(), i.getY(), BallWidth, BallHeight);
        }

        Toolkit.getDefaultToolkit().sync();

        g.dispose();
    }

    public GameState getState() {
        if (State == GameState.play)
            return State.getServeState();
        else
            return State;
    }

    public void setState(GameState state) {
        State = state;
    }

    public void reset() {
        InitGameObjects();
        removeKeyListener(KeyControl);
        InitKeyListener();
        ResetDate = null;

    }

    public enum GameState {
        play,
        paddle1Serve,
        paddle2Serve,
        paddle1Game,
        paddle2Game,
        paddle1Win,
        paddle2Win;

        private GameState opposite;
        private GameState serveState;

        static {
            play.opposite = play;
            play.serveState = paddle1Serve;
            paddle1Serve.opposite = paddle2Serve;
            paddle1Serve.serveState = paddle1Serve;
            paddle2Serve.opposite = paddle1Serve;
            paddle2Serve.serveState = paddle2Serve;
            paddle1Game.opposite = paddle2Game;
            paddle2Game.opposite = paddle1Game;
            paddle1Win.opposite = paddle2Win;
            paddle2Win.opposite = paddle1Win;

        }

        public GameState getOpposite() {
            return opposite;
        }

        public GameState getServeState() {
            return serveState;
        }

        public void setServeState(GameState serveState) {
            play.serveState = serveState;
        }
    }

    public class Update implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (State == GameState.play) {
                Paddle1.move();
                Paddle2.move();
                for (Ball i : Balls) {
                    i.move();
                }
                collisionDetector.detectCollision();
            } else if (State == GameState.paddle1Game || State == GameState.paddle2Game) {

                if (State == GameState.paddle1Game) {
                    State = GameState.paddle1Serve;
                    Paddle1WinCount++;
                } else {
                    State = GameState.paddle2Serve;
                    Paddle2WinCount++;
                }
            } else if (State == GameState.paddle1Serve || State == GameState.paddle2Serve) {
                if (ResetDate == null) {
                    ResetDate = new Date();
                } else {
                    long time = (new Date()).getTime() - ResetDate.getTime();
                    if (time >= TimeDelayAfterWin) {
                        if (State == GameState.paddle1Serve)
                            State.setServeState(GameState.paddle1Serve);
                        else
                            State.setServeState(GameState.paddle2Serve);
                        State = GameState.play;
                        reset();
                    }
                }

            }

            repaint();


        }
    }


}



