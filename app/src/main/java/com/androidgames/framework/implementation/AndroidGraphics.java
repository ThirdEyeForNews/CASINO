package com.androidgames.framework.implementation;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Rect;

import com.androidgames.framework.Gif;
import com.androidgames.framework.Graphics;
import com.androidgames.framework.Pixmap;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Manu on 7/14/2017.
 */

public class AndroidGraphics implements Graphics {

    AssetManager assetManager;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics( AssetManager assetManager,Bitmap bitmap ){
        this.assetManager = assetManager;
        this.bitmap = bitmap;
        canvas = new Canvas(bitmap);
        paint = new Paint();
    }

    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) {

        Bitmap.Config config = null;
        if(format == PixmapFormat.RGB565)
            config = Bitmap.Config.RGB_565;
        else if (format == PixmapFormat.ARGB4444)
            config = Bitmap.Config.ARGB_4444;
        else
            config = Bitmap.Config.ARGB_8888;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;

        InputStream in =null;
        Bitmap bitmap = null;

        try{
            in = assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if(bitmap == null)
                throw  new RuntimeException("Couldn't load bitmap from asset '"+fileName+"'");
        }catch (IOException e){
            throw new RuntimeException("Couldn't load bitmap from asset '"+fileName+"'");
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        if (bitmap.getConfig() == Bitmap.Config.RGB_565)
            format = PixmapFormat.RGB565;
        else if (bitmap.getConfig() == Bitmap.Config.ARGB_4444)
            format = PixmapFormat.ARGB4444;
        else
            format = PixmapFormat.ARGB8888;

        return new AndroidPixmap(bitmap,format);
    }

    @Override
    public Gif newGifFile(String fileName) {
        InputStream in =null;
        Movie movie = null;
        try{
            in = assetManager.open(fileName);
            movie = Movie.decodeStream(in);
            if(movie == null)
                throw  new RuntimeException("Couldn't load GIF from asset '"+fileName+"'");
        }catch (IOException e){
            throw new RuntimeException("Couldn't load GIF from asset '"+fileName+"'");
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return new AndroidGif(movie);
    }

    @Override
    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16,(color & 0xff00) >> 8,(color & 0xff));
    }

    @Override
    public void drawPixel(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawPoint(x,y,paint);

    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x1,y1,x2,y2,paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x,y,x+width-1,y+height-1,paint);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth -1;
        srcRect.bottom = srcY + srcHight -1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth -1;
        dstRect.bottom = y + srcHight -1;

        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap,srcRect,dstRect,null);

    }

    @Override
    public void drawPixmap(Pixmap pixmap, int destX, int destY, int destWidth, int destHight, int srcX, int srcY, int srcWidth, int srcHight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth -1;
        srcRect.bottom = srcY + srcHight -1;

        dstRect.left = destX;
        dstRect.top = destY;
        dstRect.right = destX + destWidth -1;
        dstRect.bottom = destY + destHight -1;
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap,srcRect,dstRect,null);
    }
    @Override
    public void drawPixmap(Pixmap pixmap, int destX, int destY, int destWidth, int destHight, int srcX, int srcY, int srcWidth, int srcHight,int angle) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth -1;
        srcRect.bottom = srcY + srcHight -1;

        dstRect.left = destX;
        dstRect.top = destY;
        dstRect.right = destX + destWidth -1;
        dstRect.bottom = destY + destHight -1;
        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(((AndroidPixmap) pixmap).bitmap,srcWidth,srcHight,true);

        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);
        canvas.drawBitmap(rotatedBitmap,srcRect,dstRect,null);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap,x,y,null);
    }

    public void drawGif(Gif gif,int x,int y){
        canvas.drawColor(Color.TRANSPARENT);
        long now = android.os.SystemClock.uptimeMillis();
        if (!gif.isGifStarted()) {
            gif.setGifStart(now);
        }
        if (gif != null) {
            int relTime = (int) ((now - gif.getGifStart()) % gif.duration());
            gif.setTime(relTime);
            gif.draw(canvas,x,y);

        }


    }

    @Override
    public int getWidth() {

        return bitmap.getWidth();
    }

    @Override
    public int getHight() {

        return bitmap.getHeight();
    }
}
