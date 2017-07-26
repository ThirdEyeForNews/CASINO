package com.androidgames.framework;

/**
 * Created by Manu on 7/13/2017.
 */

public interface Graphics {

    public static enum PixmapFormat{
        ARGB8888,ARGB4444,RGB565
    }

    public Pixmap newPixmap(String fileName, PixmapFormat format);
    public Gif newGifFile(String fileName);

    public void clear(int color);

    public void drawPixel(int x, int y, int color);
    public void drawLine(int x1, int y1, int x2, int y2, int color);
    public void drawRect(int x, int y, int width, int height, int color);
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHight);
    public void drawPixmap(Pixmap pixmap, int destX, int destY,int destWidth,int destHight, int srcX, int srcY, int srcWidth, int srcHight);
    public void drawPixmap(Pixmap pixmap, int destX, int destY, int destWidth, int destHight, int srcX, int srcY, int srcWidth, int srcHight,int angle);

        public void drawPixmap(Pixmap pixmap, int x, int y);
    public void drawGif(Gif gif,int x,int y);



    public int getWidth();
    public int getHight();

}
