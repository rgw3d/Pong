package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Board extends JPanel 
{
	private boolean victory; 
	private Paddle paddle1, paddle2;
	
	public Board() {
		setFocusable(true);
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		setDoubleBuffered(true);
		victory = false;
		
		paddle1 = new Paddle(1);
		paddle2 = new Paddle(2);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect(paddle1.getX(), paddle1.getY(), 10, 70);
		g2d.fillRect(paddle2.getX(), paddle2.getY(), 10, 70);
		
		
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	
	
	
	
	
}
