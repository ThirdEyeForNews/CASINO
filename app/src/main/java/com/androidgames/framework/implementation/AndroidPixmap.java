package com.androidgames.framework.implementation;

import android.graphics.Bitmap;

import com.androidgames.framework.Graphics;
import com.androidgames.framework.Pixmap;

/**
 * Created by ammu on 7/13/2017.
 */

public class AndroidPixmap implements Pixmap {

    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format){
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.PixmapFormat getFormat() {

        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
