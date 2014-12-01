package pong;

import java.awt.Color;
import javax.swing.JPanel;

public class Board extends JPanel 
{
	private boolean victory; 
	
	
	public Board() {
		setFocusable(true);
		setVisible(true);
		setBackground(Color.CYAN);
		setDoubleBuffered(true);
		victory = false;
	}
	
}
