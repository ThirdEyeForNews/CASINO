package com.androidgames.framework.implementation;

import android.view.MotionEvent;
import android.view.View;

import com.androidgames.framework.Input;
import com.androidgames.framework.Pool;
import com.androidgames.framework.TouchHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ammu on 7/13/2017.
 */

public class SingleTouchHandler implements TouchHandler {

    boolean isTouched;
    int touchX;
    int touchY;
    Pool<Input.TouchEvent> touchEventPool;
    List<Input.TouchEvent> touchEvents = new ArrayList<>();
    List<Input.TouchEvent> touchEventBuffer = new ArrayList<>();
    float scaleX;
    float scaleY;

    public SingleTouchHandler(View view,float scaleX,float scaleY) {
        Pool.PoolObjetcFactory<Input.TouchEvent> factory = new Pool.PoolObjetcFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };
        touchEventPool = new Pool<Input.TouchEvent>(factory,100);
        view.setOnTouchListener(this);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this){
            return pointer==0?isTouched:false;
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this){
            return touchX;
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this){
            return touchY;
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this){
            int len;
            for(int i=0;i<(len = touchEvents.size());i++){
                touchEventPool.free(touchEvents.get(i));
            }
            touchEvents.clear();
            touchEvents.addAll(touchEventBuffer);
            touchEventBuffer.clear();
            return touchEvents;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        synchronized (this) {
            Input.TouchEvent touchEvent = touchEventPool.newObject();
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type = Input.TouchEvent.TOUCH_UP;
                    isTouched = false;
                    break;
            }
            touchEvent.x = touchX = (int) (motionEvent.getX() * scaleX);
            touchEvent.y = touchY = (int) (motionEvent.getY() * scaleY);
            touchEventBuffer.add(touchEvent);
            return true;
        }
    }
}
