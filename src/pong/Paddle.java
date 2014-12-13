package pong;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle implements GameObject {

	private int x;
    private int y;
    private int Speed = 2;
    private int WallWidth = 30;
    private int BoardHeight;
    private int BoardWidth;
    private int PaddleWidth,PaddleHeight;

    private boolean UpPressed = false;
    private boolean DownPressed = false;


    /**
     * @param distFromLeft distance from either the right or left wall.
     * @param distFromTop distance from either the top or the bottom.
     * @param boardWidth width of the board
     * @param boardHeight boardHeight of the board
     * @param speed how many pixles the paddle moves. if set to zero, the default of 2 is used.
     * @param wallWidth used to go up to the wall and not go over it.  Used to calculate the Boarder distance of the paddle
     * @param paddleWidth width that the paddle is drawn
     * @param paddleHeight height that the paddle is drawn
     *
     */
    public Paddle(int distFromLeft, int distFromTop, int boardWidth, int boardHeight, int speed, int wallWidth, int paddleWidth, int paddleHeight){
        x = distFromLeft;
        y = distFromTop;

        if(speed!=0){
            Speed = speed;
        }
        BoardHeight = boardHeight;
        BoardWidth = boardWidth;

        if(wallWidth !=0){
            WallWidth = wallWidth;
        }

        PaddleHeight = paddleHeight;
        PaddleWidth = paddleWidth;

    }
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

    public int getObjWidth() { return PaddleWidth; }

    public int getObjHeight() { return PaddleHeight; }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getObjWidth(), getObjHeight());
    }

    public void move() {
        int dy;
        if(UpPressed && !DownPressed)
            dy = -Speed;
        else if (!UpPressed && DownPressed)
            dy = Speed;
        else
            dy = 0;

        if(dy !=0 &&(y>=WallWidth && y<=BoardHeight-(WallWidth+PaddleHeight))){
            y+= dy;
        }
        else if(dy != 0){//this is if it wants to move but it cant because it is not in the bounds.
            if(!(y>=WallWidth) && y<=BoardHeight-(WallWidth+PaddleHeight)){//this tests to see what bounds it is out of
                y=WallWidth;//then it will set it to the highest possible value that is still in the bounds
            }
            else if(y>=WallWidth && !(y<=BoardHeight-(WallWidth+PaddleHeight))){
                y = BoardHeight-(WallWidth+PaddleHeight);
            }
        }
    }
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
				
		if(((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP))) {
            UpPressed = true;
        }

        if(((key == KeyEvent.VK_S) || (key == KeyEvent.VK_DOWN)) ) {
            DownPressed = true;
        }



		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
        if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP)){
            UpPressed = false;
        }
		
		if((key == KeyEvent.VK_S) || (key == KeyEvent.VK_DOWN)) {
			DownPressed = false;
		}
	}


	
}
