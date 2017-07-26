package com.example.ammu.casino;

import android.widget.TextView;

import com.androidgames.framework.Game;
import com.androidgames.framework.Graphics;
import com.androidgames.framework.Input;
import com.androidgames.framework.Screen;
import com.androidgames.framework.implementation.AndroidGame;

import java.util.List;

/**
 * Created by ammu on 7/14/2017.
 */

public class MainScreen extends Screen {


    public MainScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len;
        for (int i=0;i<(len=touchEvents.size());i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){
                if(inBounds(event,0,0,40,40)){
                    Settings.soundEnabled = !Settings.soundEnabled;
                }
                if(inBounds(event,260,30,200,60)){
                    game.setScreen(new GameScreen(game));
                }
                if(inBounds(event,260,90,200,60)){
                    //multi play
                }
                if(inBounds(event,260,150,200,60)){
                    game.setScreen(new MyProfile(game));
                }
                if(inBounds(event,260,210,200,60)){
                    //Quit
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event,int x, int y, int width, int hight){
        return (event.x>x && event.x<x +width-1 && event.y>y && event.y<y+hight-1)?true:false;
    }

    @Override
    public void present(float deltaTime) {
       Graphics g = game.getGraphics();
        g.drawPixmap(Assets.MAIN_BACKGROUND,0,0,480,320,0,0,Assets.MAIN_BACKGROUND.getWidth(),Assets.MAIN_BACKGROUND.getHight());
        g.drawPixmap(Assets.MAIN_CASINO,70,40,170,90,0,0,1200,500);
        g.drawPixmap(Assets.MAIN_GIRL,78,90,150,200,0,0,Assets.MAIN_GIRL.getWidth(),Assets.MAIN_GIRL.getHight());
        g.drawPixmap(Assets.MAIN_SINGLE_PLAY,260,30,200,60,0,0,Assets.MAIN_SINGLE_PLAY.getWidth(),Assets.MAIN_SINGLE_PLAY.getHight());
        g.drawPixmap(Assets.MAIN_MULTI_PLAY,260,90,200,60,0,0,Assets.MAIN_MULTI_PLAY.getWidth(),Assets.MAIN_MULTI_PLAY.getHight());
        g.drawPixmap(Assets.MAIN_MY_PROFILE,260,150,200,60,0,0,Assets.MAIN_MY_PROFILE.getWidth(),Assets.MAIN_MY_PROFILE.getHight());
        g.drawPixmap(Assets.MAIN_QUIT,260,210,200,60,0,0,Assets.MAIN_QUIT.getWidth(),Assets.MAIN_QUIT.getHight());


        if (Settings.soundEnabled)
            g.drawPixmap(Assets.SOUND_ON,5,5,40,40,0,0,Assets.SOUND_ON.getWidth(),Assets.SOUND_ON.getHight());
        else
            g.drawPixmap(Assets.SOUND_OFF,5,5,40,40,0,0,Assets.SOUND_OFF.getWidth(),Assets.SOUND_OFF.getHight());

    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
