package pong;

import java.awt.*;
import javax.swing.JFrame;

public class Base extends JFrame {
	
	public static void main(String args[]) {
		new Base();
	}

	public Base() {
		add(new Board());
		setTitle("Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //hekked m8
		setSize(500, 500);
		setVisible(true);
		setResizable(false);
	}
}
