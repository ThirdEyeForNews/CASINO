package com.example.ammu.casino;

import com.androidgames.framework.Game;
import com.androidgames.framework.Graphics;
import com.androidgames.framework.Screen;

/**
 * Created by Manu on 7/14/2017.
 */

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        //For Main page
        Assets.MAIN_BACKGROUND = g.newPixmap("main_backgroung.png", Graphics.PixmapFormat.RGB565);
        Assets.MAIN_CASINO = g.newPixmap("casino.png", Graphics.PixmapFormat.ARGB4444);
        Assets.MAIN_GIRL = g.newPixmap("main_girl.png", Graphics.PixmapFormat.ARGB4444);
        Assets.MAIN_SINGLE_PLAY = g.newPixmap("single_play.png", Graphics.PixmapFormat.ARGB4444);
        Assets.MAIN_MULTI_PLAY = g.newPixmap("multi_play.png", Graphics.PixmapFormat.ARGB4444);
        Assets.MAIN_MY_PROFILE = g.newPixmap("my_profile.png", Graphics.PixmapFormat.ARGB4444);
        Assets.MAIN_QUIT = g.newPixmap("quit.png", Graphics.PixmapFormat.ARGB4444);
        Assets.SOUND_ON = g.newPixmap("sound_on.png", Graphics.PixmapFormat.ARGB4444);
        Assets.SOUND_OFF = g.newPixmap("sound_off.png", Graphics.PixmapFormat.ARGB4444);

        //For My Profile page
        Assets.MY_PROFILE_BK = g.newPixmap("my_profile_bk.png", Graphics.PixmapFormat.ARGB4444);
        Assets.ACHIVEMENT_NO_CLICK = g.newPixmap("achivement_no_click.png", Graphics.PixmapFormat.ARGB4444);
        Assets.ACHIVEMENT_CLICK = g.newPixmap("achivement_click.png", Graphics.PixmapFormat.ARGB4444);
        Assets.BACK = g.newPixmap("back.png", Graphics.PixmapFormat.ARGB4444);
        Assets.SOUND_TOGGLE_ON = g.newPixmap("sound_toggle_on.png", Graphics.PixmapFormat.ARGB4444);
        Assets.SOUND_TOGGLE_OFF = g.newPixmap("sound_toggle_off.png", Graphics.PixmapFormat.ARGB4444);

        //For Game Page
        Assets.GAME_PAGE_BACKGROUND = g.newPixmap("game_table.png", Graphics.PixmapFormat.RGB565);
        Assets.LOADING_SINGER = g.newGifFile("singer.gif");
        Assets.CARD_FRONT = g.newPixmap("cardfront.png",Graphics.PixmapFormat.ARGB4444);
        Assets.CARD_BACK = g.newPixmap("cardback.png",Graphics.PixmapFormat.ARGB4444);
        Assets.ROBBEN =  g.newPixmap("robben.png",Graphics.PixmapFormat.ARGB4444);
        Assets.SACHIN =  g.newPixmap("sachin.png",Graphics.PixmapFormat.ARGB4444);
        Assets.SANIYA =  g.newPixmap("saniya.png",Graphics.PixmapFormat.ARGB4444);
        Assets.MARTINA =  g.newPixmap("martina.png",Graphics.PixmapFormat.ARGB4444);
        Assets.ME_BOY =  g.newPixmap("me_boy.png",Graphics.PixmapFormat.ARGB4444);
        Assets.ME_GIRL =  g.newPixmap("me_girl.png",Graphics.PixmapFormat.ARGB4444);
        Assets.PLAYER_BK =  g.newPixmap("player_bk.png",Graphics.PixmapFormat.ARGB4444);


        Settings.load(game.getFileIO());
        game.setScreen(new MainScreen(game));
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        if(Assets.MAIN_BACKGROUND!=null)
            g.drawPixmap(Assets.MAIN_BACKGROUND,0,0,320,480,0,0,Assets.MAIN_BACKGROUND.getWidth(),Assets.MAIN_BACKGROUND.getHight());

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
