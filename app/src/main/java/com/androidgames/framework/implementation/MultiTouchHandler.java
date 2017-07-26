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

public class MultiTouchHandler implements TouchHandler {

    private static final int MAX_TOUCHPOINTS = 10;
    boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];
    int[] touchX = new int[MAX_TOUCHPOINTS];
    int[] touchY = new int[MAX_TOUCHPOINTS];
    int[] id = new int[MAX_TOUCHPOINTS];

    Pool<Input.TouchEvent> touchEventPool;
    List<Input.TouchEvent> touchEvents = new ArrayList<>();
    List<Input.TouchEvent> touchEventBuffer = new ArrayList<>();

    float scaleX;
    float scaleY;

    public MultiTouchHandler(View view,float scaleX,float scaleY) {
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
        synchronized (this) {
            int index;
            return (index = getIndex(pointer)) < 0 || index >= MAX_TOUCHPOINTS ? false : isTouched[index];
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {
            int index;
            return (index = getIndex(pointer)) < 0 || index >= MAX_TOUCHPOINTS ? 0 : touchX[index];
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {
            int index;
            return (index = getIndex(pointer)) < 0 || index >= MAX_TOUCHPOINTS ? 0 : touchY[index];
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
        synchronized (this){
            int action = motionEvent.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex = (motionEvent.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
            int pointerCount = motionEvent.getPointerCount();
            Input.TouchEvent touchEvent;
            for (int i=0;i<MAX_TOUCHPOINTS;i++){
                if(i>=pointerCount){
                    isTouched[i] = false;
                    id[i] = -1;
                    continue;
                }
                int pointerId = motionEvent.getPointerId(i);
                if(motionEvent.getAction()!= MotionEvent.ACTION_MOVE && i != pointerIndex)
                    continue;
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = touchX[i] = (int) (motionEvent.getX()*scaleX);
                        touchEvent.y = touchY[i] = (int) (motionEvent.getY()*scaleY);
                        isTouched[i] = true;
                        id[i] = pointerId;
                        touchEventBuffer.add(touchEvent);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL:
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = Input.TouchEvent.TOUCH_UP;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = touchX[i] = (int) (motionEvent.getX()*scaleX);
                        touchEvent.y = touchY[i] = (int) (motionEvent.getY()*scaleY);
                        isTouched[i] = false;
                        id[i] = -1;
                        touchEventBuffer.add(touchEvent);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = touchX[i] = (int) (motionEvent.getX()*scaleX);
                        touchEvent.y = touchY[i] = (int) (motionEvent.getY()*scaleY);
                        isTouched[i] = true;
                        id[i] = pointerId;
                        touchEventBuffer.add(touchEvent);
                        break;
                }
            }
            return true;
        }
    }

    private int getIndex(int pointerId){
        for (int i=0;i<MAX_TOUCHPOINTS;i++)
            if(id[i] == pointerId)
                return i;
        return -1;
    }
}
