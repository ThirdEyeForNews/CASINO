package com.androidgames.framework;

/**
 * Created by ammu on 7/13/2017.
 */

public interface Pixmap {

    public int getWidth();
    public int getHight();

    public Graphics.PixmapFormat getFormat();

    public void dispose();

}
