package com.example.ammu.casino;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.androidgames.framework.Screen;
import com.androidgames.framework.implementation.AndroidGame;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AndroidGame{

    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
