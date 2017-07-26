package com.androidgames.framework;

/**
 * Created by Manu on 7/13/2017.
 */

public interface Music {

    public void play();
    public void stop();
    public void pause();

    public void setLooping(boolean looping);
    public void setVolume(float volume);

    public boolean isPlaying();
    public boolean isStoped();
    public boolean isLooping();

    public void dispose();

}
