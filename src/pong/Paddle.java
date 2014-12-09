package pong;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle implements GameObject {

	private int x, y, dy;
    private int Speed = 2;
    private int WallWidth = 30;
    private int BoardHeight;
    private int BoardWidth;
    private int PaddleWidth,PaddleHeight;


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
        BoardHeight = boardWidth;
        BoardWidth = boardHeight;

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
        if((dy<0 && y> WallWidth)||(dy>0 && y< BoardWidth -(WallWidth +PaddleHeight+PaddleHeight/2)))
            y += dy;
    }
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
				
		if(((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP))) {
			dy = -Speed;
        }
		
		if(((key == KeyEvent.VK_S) || (key == KeyEvent.VK_DOWN)) ) {
			dy = Speed;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP) || (key == KeyEvent.VK_S) || (key == KeyEvent.VK_DOWN)) {
			dy = 0;
		}
	}


	
}
