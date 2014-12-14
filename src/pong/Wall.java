package pong;

import java.awt.*;

/**
 * Created by rgw3d on 12/4/2014.
 * used as walls that the ball can reflect off of
 */
public class Wall implements GameObject {

    private int x, y;
    private int WallWidth, WallHeight;

    public Wall(int distFromLeft, int distFromTop, int wallWidth, int wallHeight) {
        this.x = distFromLeft;
        this.y = distFromTop;
        WallWidth = wallWidth;
        WallHeight = wallHeight;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WallWidth, WallHeight);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getObjWidth() {
        return WallWidth;
    }

    public int getObjHeight() {
        return WallHeight;
    }

    public void move() {
    }

}
