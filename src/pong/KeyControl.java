package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * I just moved it to another class instead of having it in the same class as the Board
 * Created by rgw3d on 12/3/2014.
 */
public class KeyControl extends KeyAdapter {
    public Paddle paddle1;
    public Paddle paddle2;
    public KeyControl(Paddle a, Paddle b){
        paddle1 = a;
        paddle2 = b;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_S)) {
            paddle1.keyReleased(e);
        }
        if((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
            paddle2.keyReleased(e);
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_S)) {
            paddle1.keyPressed(e);
        }
        if((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
            paddle2.keyPressed(e);
        }
    }
}