package pong;

import java.awt.*;
import javax.swing.JFrame;

public class Base extends JFrame {

    public int width = 1800;
    public int height = 750;
	
	public static void main(String args[]) {
		new Base();
	}

	public Base() {
		add(new Board(width,height));
		setTitle("Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setVisible(true);
		setResizable(false);
	}
}
