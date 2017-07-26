package com.androidgames.framework.implementation;

import android.media.SoundPool;

import com.androidgames.framework.Sound;

/**
 * Created by Manu on 7/13/2017.
 */

public class AndroidSound implements Sound {

    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId){
        this.soundPool = soundPool;
        this.soundId = soundId;
    }

    @Override
    public void play(int volume) {
        soundPool.play(soundId,volume,volume,0,0,1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
