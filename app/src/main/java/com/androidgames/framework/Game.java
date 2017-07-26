package com.androidgames.framework;

/**
 * Created by Manu on 7/13/2017.
 */

public interface Game {

    public Input getInput();
    public FileIO getFileIO();
    public Graphics getGraphics();
    public Audio getAudio();

    public void setScreen(Screen screen);
    public Screen getCurrentScreen();
    public Screen getStartScreen();
    public void setOrientation(int orientation);

}
