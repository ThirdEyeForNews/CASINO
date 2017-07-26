package com.androidgames.framework.implementation;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.view.View;

import com.androidgames.framework.Gif;

import java.io.InputStream;

/**
 * Created by Manu on 7/21/2017.
 */

public class AndroidGif implements Gif {
     Movie movie;
    boolean isGifStarted = false;
    public long gifStart = 0;

    public AndroidGif(Movie movie){
        this.movie = movie;
    }
    @Override
    public void draw(Canvas canvas,int x,int y){
        movie.draw(canvas,x,y);
    }

    @Override
    public void setTime(int time){
        movie.setTime(time);
    }

    @Override
    public long getGifStart() {
        return gifStart;
    }

    @Override
    public void setGifStart(long gifStart) {
        this.gifStart = gifStart;
    }

    @Override
    public boolean isGifStarted() {
        return isGifStarted;
    }

    @Override
    public void setGifStarted(boolean gifStarted) {
        isGifStarted = gifStarted;
    }

    @Override
    public int getWidth() {
        return movie.width();
    }

    @Override
    public int getHight() {
        return movie.height();
    }

    public int duration(){
        return movie.duration();
    }

}
