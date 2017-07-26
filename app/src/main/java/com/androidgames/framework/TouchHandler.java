package com.androidgames.framework;

import android.view.View;

import java.util.List;

/**
 * Created by ammu on 7/13/2017.
 */

public interface TouchHandler extends View.OnTouchListener {

    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);
    public int getTouchY(int pointer);

    public List<Input.TouchEvent> getTouchEvents();

}
