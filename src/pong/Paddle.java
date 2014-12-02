package pong;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Paddle {

	private int x, y;
	
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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	
}
