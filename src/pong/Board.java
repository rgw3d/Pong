package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener 
{
	private boolean victory; 
	private Paddle paddle1, paddle2;
	private Timer tickTimer;
	private int B_WIDTH;
	private int B_HEIGHT;
	
	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		setDoubleBuffered(true);
		victory = false;
		
		paddle1 = new Paddle(1);
		paddle2 = new Paddle(2);
		
		tickTimer = new Timer(5,this);
		tickTimer.start();

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
	
	public void addNotify() {
		super.addNotify();
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight();   
	}
	
	public void actionPerformed(ActionEvent e) {
		
		paddle1.move();
		paddle2.move();
		repaint();
	}


	
	private class TAdapter extends KeyAdapter { 

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_W) { //THESE CAN'T BE || OR STATEMENTS FOR SOME REASON
				paddle1.keyReleased(e);
			}
			if(key == KeyEvent.VK_UP) {
				paddle2.keyReleased(e);
			}
			if(key == KeyEvent.VK_S) {
				paddle1.keyReleased(e);
			}
			if(key == KeyEvent.VK_DOWN) {
				paddle2.keyReleased(e);
			}
			
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_W) {
				paddle1.keyPressed(e);
			}
			if(key == KeyEvent.VK_UP) {
				paddle2.keyPressed(e);
			}
			if(key == KeyEvent.VK_S) {
				paddle1.keyPressed(e);
			}
			if(key == KeyEvent.VK_DOWN) {
				paddle2.keyPressed(e);
			}
		}
	}
}



