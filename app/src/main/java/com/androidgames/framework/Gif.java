package com.androidgames.framework;

import android.graphics.*;

/**
 * Created by ammu on 7/21/2017.
 */

public interface Gif {

    public void draw(Canvas canvas,int x, int y);

    public void setTime(int time);
    public boolean isGifStarted();
    public void setGifStarted(boolean gifStarted);
    public long getGifStart();

    public void setGifStart(long gifStart);

    public int getHight();
    public int getWidth();

    public int duration();
}
