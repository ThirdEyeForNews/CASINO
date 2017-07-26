package com.androidgames.framework.implementation;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;

import com.androidgames.framework.Input;
import com.androidgames.framework.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ammu on 7/13/2017.
 */

public class KeyBoardHandler implements View.OnKeyListener {

    boolean[] pressedKey = new boolean[128];
    Pool<Input.KeyEvent> keyEventPool;
    List<Input.KeyEvent> keyEventBuffer = new ArrayList<>();
    List<Input.KeyEvent> keyEvents = new ArrayList<>();

    public KeyBoardHandler(View view){
        Pool.PoolObjetcFactory<Input.KeyEvent> factory = new Pool.PoolObjetcFactory<Input.KeyEvent>() {
            @Override
            public Input.KeyEvent createObject() {
                return new Input.KeyEvent();
            }
        };
        keyEventPool = new Pool<Input.KeyEvent>(factory,100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();

    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return false;
        synchronized (this){
            Input.KeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char)event.getUnicodeChar();
            if (event.getAction() == KeyEvent.ACTION_DOWN){
                keyEvent.type = Input.KeyEvent.KEY_DOWN;
                if(keyCode >0 && keyCode<127)
                    pressedKey[keyCode] = true;
            }
            if (event.getAction() == KeyEvent.ACTION_UP){
                keyEvent.type = Input.KeyEvent.KEY_UP;
                if(keyCode >0 && keyCode<127)
                    pressedKey[keyCode] = false;
            }
            keyEventBuffer.add(keyEvent);
        }
        return true;
    }

    public boolean isKeyPressed(int keyCode){
        return keyCode<0 && keyCode>127 ? false:pressedKey[keyCode];
    }

    public List<Input.KeyEvent> getKeyEvents(){
        synchronized (this){
            int len;
            for(int i=0;i<(len = keyEvents.size());i++){
                keyEventPool.free(keyEvents.get(i));
            }
            keyEvents.clear();
            keyEvents.addAll(keyEventBuffer);
            keyEventBuffer.clear();
            return keyEvents;
        }
    }
}
