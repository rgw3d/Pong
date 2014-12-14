package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * I just moved it to another class instead of having it in the same class as the Board
 * Created by rgw3d on 12/3/2014.
 */
public class KeyControl extends KeyAdapter {
    public Paddle Paddle1;
    public Paddle Paddle2;

    public KeyControl(Paddle a, Paddle b) {
        Paddle1 = a;
        Paddle2 = b;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_W) || (key == KeyEvent.VK_S)) {
            Paddle1.keyReleased(e);
        }
        if ((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
            Paddle2.keyReleased(e);
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_W) || (key == KeyEvent.VK_S)) {
            Paddle1.keyPressed(e);
        }
        if ((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
            Paddle2.keyPressed(e);
        }
    }
}