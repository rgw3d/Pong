package pong;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle implements GameObject {

	private int x, y, dy;
    private int Speed = 2;
    private int BufferHeight = 30;
    private int BoardHeight;
    private int BoardWidth;
    private int PaddleWidth,PaddleHeight;


    /**
     * @param distFromLeft distance from either the right or left wall.
     * @param distFromTop distance from either the top or the bottom.
     * @param boardWidth width of the board
     * @param boardHeight boardHeight of the board
     * @param speed how many pixles the paddle moves. if set to zero, the default of 2 is used.
     * @param bufferHeight area from the top or bottom of the screen that the paddle can go up to
     * @param paddleWidth width that the paddle is drawn
     * @param paddleHeight height that the paddle is drawn
     *
     */
    public Paddle(int distFromLeft, int distFromTop, int boardWidth, int boardHeight, int speed, int bufferHeight, int paddleWidth, int paddleHeight){
        x = distFromLeft;
        y = distFromTop;

        if(speed!=0){
            Speed = speed;
        }
        BoardHeight = boardWidth;
        BoardWidth = boardHeight;

        if(bufferHeight!=0){
            BufferHeight = bufferHeight;
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
        if((dy<0 && y>BufferHeight)||(dy>0 && y< BoardWidth -BufferHeight*3))
            y += dy;
    }
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
				
		if(((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP))&& y>BufferHeight  ) {
			dy = -Speed;
        }
		
		if(((key == KeyEvent.VK_S) || (key == KeyEvent.VK_DOWN))&& y< BoardWidth -BufferHeight*2.5 ) {
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
