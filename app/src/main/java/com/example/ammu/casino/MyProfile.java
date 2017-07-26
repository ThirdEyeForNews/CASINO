package com.example.ammu.casino;

import android.content.pm.ActivityInfo;

import com.androidgames.framework.Game;
import com.androidgames.framework.Graphics;
import com.androidgames.framework.Input;
import com.androidgames.framework.Screen;

import java.util.List;

/**
 * Created by ammu on 7/20/2017.
 */

public class MyProfile extends Screen {
    static boolean achivementClicked = false;
    public MyProfile(Game game) {
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
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                if(inBounds(event,150,250,150,40)) {
                    achivementClicked = true;
                }
            }else if(event.type == Input.TouchEvent.TOUCH_UP){
                if(inBounds(event,150,140,40,30)){
                    Settings.soundEnabled = !Settings.soundEnabled;
                }
                if(inBounds(event,150,250,150,40)) {
                    achivementClicked = false;
                    //Achivement
                }
                if(inBounds(event,10,270,40,40)){
                    game.setScreen(new MainScreen(game));
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
        g.drawPixmap(Assets.MY_PROFILE_BK,0,0,480,320,0,0,Assets.MY_PROFILE_BK.getWidth(),Assets.MY_PROFILE_BK.getHight());
        if(achivementClicked)
            g.drawPixmap(Assets.ACHIVEMENT_CLICK,150,250,150,40,0,0,Assets.ACHIVEMENT_CLICK.getWidth(),Assets.ACHIVEMENT_CLICK.getHight());
        else
            g.drawPixmap(Assets.ACHIVEMENT_NO_CLICK,150,250,150,40,0,0,Assets.ACHIVEMENT_NO_CLICK.getWidth(),Assets.ACHIVEMENT_NO_CLICK.getHight());

        g.drawPixmap(Assets.BACK,10,270,40,40,0,0,Assets.BACK.getWidth(),Assets.BACK.getHight());
        if (Settings.soundEnabled)
            g.drawPixmap(Assets.SOUND_TOGGLE_ON,150,140,40,30,0,0,Assets.SOUND_TOGGLE_ON.getWidth(),Assets.SOUND_TOGGLE_ON.getHight());
        else
            g.drawPixmap(Assets.SOUND_TOGGLE_OFF,150,140,40,30,0,0,Assets.SOUND_TOGGLE_OFF.getWidth(),Assets.SOUND_TOGGLE_OFF.getHight());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
