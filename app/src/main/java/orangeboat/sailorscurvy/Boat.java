package orangeboat.sailorscurvy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import orangeboat.sailorscurvy.Animation;

/**
 * Created by Kush on 1/29/2016.
 */
public class Boat extends Entity
{
    int dx,dx2;
    Bitmap boatForwardImg;
    Bitmap [] boatForwardFrames = new Bitmap[4];
    Animation boatForward;
    Paint paint;
    public Boat(Bitmap boatForward,int x, int y)
    {
        super(boatForward, x, y);
        this.boatForwardImg = boatForward;
        this.boatForward = new Animation();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        dx2 = 15;
    }
    public void update()
    {
        dx = (int)(SensorData.lastX*dx2);
        if(dx<2&dx>-2)
            dx=0;
        x-=dx;
        boatForward.update();
    }
    public void load()
    {
        int width = 250;
        int height = 300;
        for(int i = 0; i < boatForwardFrames.length;i++)
        {
            boatForwardFrames[i] = Bitmap.createBitmap(boatForwardImg,i*width,0,width,height);
        }
        boatForward.setFrames(boatForwardFrames);
        boatForward.setDelay(60);
    }
    public void draw(Canvas canvas)
    {
        canvas.drawRect(0, 0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint);
        canvas.drawBitmap(boatForward.getImage(), x, y, null);

    }
}
