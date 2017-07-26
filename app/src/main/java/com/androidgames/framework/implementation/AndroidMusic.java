package com.androidgames.framework.implementation;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.androidgames.framework.Music;

import java.io.IOException;

/**
 * Created by Manu on 7/13/2017.
 */

public class AndroidMusic implements Music,MediaPlayer.OnCompletionListener {

    MediaPlayer mediaPlayer;
    boolean isPrepared = false;

    public AndroidMusic(AssetFileDescriptor assetFileDescriptor){
        this.mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        }catch (Exception e){
            throw new RuntimeException("Couldn't load music");
        }
    }

    @Override
    public void play() {
        if (mediaPlayer.isPlaying())
            return;
        try{
            synchronized (this){
                if(!isPrepared)
                    mediaPlayer.prepare();
                mediaPlayer.start();
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        synchronized (this){
            isPrepared = false;
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume,volume);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStoped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        synchronized (this){
            isPrepared = false;
        }
    }
}
