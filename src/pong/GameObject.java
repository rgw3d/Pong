package pong;

import java.awt.*;

/**
 * Created by rgw3d on 12/5/2014.
 * Inherited by all objects used in the game
 * Used to make sure that every object can return an x, y, width, height, and bounds
 * Also makes everything have a move method
 */
public interface GameObject {
    public int getX();

    public int getY();

    public int getObjWidth();

    public int getObjHeight();

    public Rectangle getBounds();

    public void move();


}
