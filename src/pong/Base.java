package pong;

import javax.swing.*;

public class Base extends JFrame {

    public int width = 1500;
    public int height = 750;

    public Base() {
        setTitle("Pong");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
        setResizable(false);

        Board board = new Board(width, height);
        setContentPane(board);
        pack();

        board.InitDimensions();
        board.InitGameObjects();
        board.InitKeyListener();
        board.StartTimer();

    }

    public static void main(String args[]) {
        new Base();
    }
}
