package pong;

import javax.swing.JFrame;

public class Base extends JFrame {

    public int width = 1500;
    public int height = 750;

    public static void main(String args[]) {
        new Base();
    }

    public Base() {
        setTitle("Pong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
        setResizable(false);

        Board board = new Board(width,height);
        setContentPane(board);
        pack();

        board.InitDimensions();
        board.InitGameObjects();
        board.InitKeyListener();
        board.StartTimer();
        //TODO: board.setWidth , board.setHeight.
        //TODO: board.startTimer();
        //add(new Board(width,height));

    }
}
