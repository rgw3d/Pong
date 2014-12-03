package pong;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Paddle {

	private int x, y, dy;
	
	public Paddle(int whichPaddle) {
		if(whichPaddle == 1) {
			x = 50;
			y = 250;
		}
		else {
			x = 450;
			y = 250;
		}	
		
	}
	
	public void move() {
		y += dy;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
				
		if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP)) {
			dy = -2;
		}
		
		if((key == KeyEvent.VK_S) || (key == KeyEvent.VK_DOWN)) {
			dy = 2;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP) || (key == KeyEvent.VK_S) || (key == KeyEvent.VK_DOWN)) {
			dy = 0;
		}
	}
	
}
