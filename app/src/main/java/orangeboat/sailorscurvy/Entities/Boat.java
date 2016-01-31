package orangeboat.sailorscurvy.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import orangeboat.sailorscurvy.Threads.Animation;
import orangeboat.sailorscurvy.Display;
import orangeboat.sailorscurvy.Input.SensorData;

/**
 * Created by Kush on 1/29/2016.
 */
public class Boat extends Entity
{
    int dx, sensitivity;
    Bitmap boatForwardImg;
    Bitmap boatRightImg;
    Bitmap boatLeftImg;
    Bitmap [] boatForwardFrames = new Bitmap[4];
    Bitmap [] boatLeftFrames = new Bitmap[4];
    Bitmap [] boatRightFrames = new Bitmap[4];
    Animation boatForward;
    Animation boatLeft;
    Animation boatRight;
    Paint paint;
    Paint p2 = new Paint();
    int midline;
    int limit;
    public Boat(Bitmap boatForward,Bitmap boatleft, Bitmap boatright, int x, int y, int limit)
    {
        super(boatForward, x, y);
        this.limit = limit;
        this.boatForwardImg = boatForward;
        this.boatLeftImg = boatleft;
        this.boatRightImg = boatright;
        midline = limit/2 - boatRightImg.getWidth()/8;
        this.boatForward = new Animation();
        this.boatLeft = new Animation();
        this.boatRight = new Animation();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        p2.setColor(Color.WHITE);
        sensitivity = 20;
    }
    public void update()
    {

        dx = (int)(SensorData.lastX* sensitivity);
        if(dx<2 && dx>-2) {
            dx = 0;
            /*midline code
            if(x < midline-2){   x += 10; }
            else if(x > midline+2){  x -= 10; }
            */
        }
        if((x > 0 && dx > 0) || (dx < 0 && x < limit-boatRightImg.getWidth()/4) ) {
            //adds edges of the screen
            x -= dx;
        }
        super.update();
        hitbox.set(hitbox.left + boatForwardImg.getWidth()/16, hitbox.top,
                hitbox.right - boatForwardImg.getWidth()/16, hitbox.bottom - boatForwardImg.getHeight()/4);
        boatForward.update();
        boatLeft.update();
        boatRight.update();
    }
    public void load()
    {
        int width = boatForwardImg.getWidth()/4;
        int height = boatForwardImg.getHeight();
        for(int i = 0; i < boatForwardFrames.length;i++)
        {
            boatForwardFrames[i] = Bitmap.createBitmap(boatForwardImg,i*width,0,width,height);
            boatRightFrames[i] = Bitmap.createBitmap(boatRightImg, i*width, 0, width, height);
            boatLeftFrames[i] = Bitmap.createBitmap(boatLeftImg, i*width, 0, width, height);
        }
        boatForward.setFrames(boatForwardFrames);
        boatForward.setDelay(60);
        boatRight.setFrames(boatRightFrames);
        boatRight.setDelay(60);
        boatLeft.setFrames(boatLeftFrames);
        boatLeft.setDelay(60);
    }
    public void draw(Canvas canvas)
    {
        canvas.drawRect(0, 0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint);
        if(SensorData.lastX < -0.5f){
            canvas.drawBitmap(boatRight.getImage(), x, y, null);
        }
        else if(SensorData.lastX > 0.5f){
            canvas.drawBitmap(boatLeft.getImage(), x, y, null);
        }
        else {
            canvas.drawBitmap(boatForward.getImage(), x, y, null);
        }
      //  canvas.drawRect(hitbox, p2);
    }
}
