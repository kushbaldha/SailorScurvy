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
    Bitmap wakeImg;
    Bitmap explosion;
    Bitmap [] boatForwardFrames = new Bitmap[4];
    Bitmap [] boatLeftFrames = new Bitmap[4];
    Bitmap [] boatRightFrames = new Bitmap[4];
    Bitmap [] wakeFrames = new Bitmap[4];
    Bitmap [] explosionFrames = new Bitmap[8];
    Animation boatForward;
    Animation boatLeft;
    Animation boatRight;
    Animation wake;
    public Animation boatexplosion;
    Paint p2 = new Paint();
    int midline;
    int limit;
    int signal = 0; // 0-middle, 1 left, 2 is right
    public Boat(Bitmap boatForward,Bitmap boatleft, Bitmap boatright, int x, int y,Bitmap wake, int limit, Bitmap explosion)
    {
        super(boatForward, x, y);
        this.limit = limit;
        this.boatForwardImg = boatForward;
        this.boatLeftImg = boatleft;
        this.boatRightImg = boatright;
        this.wakeImg = wake;
        this.explosion = explosion;
        midline = limit/2 - boatRightImg.getWidth()/8;
        this.boatForward = new Animation();
        this.boatLeft = new Animation();
        this.boatRight = new Animation();
        this.wake = new Animation();
        this.boatexplosion = new Animation();
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
        hitbox.set(hitbox.left + boatForwardImg.getWidth() / 16, hitbox.top,
                hitbox.right - boatForwardImg.getWidth() / 16, hitbox.bottom - boatForwardImg.getHeight() / 4);
        boatForward.update();
        boatLeft.update();
        boatRight.update();
        wake.update();
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
        width = wakeImg.getWidth()/4;
        height = wakeImg.getHeight();
        for(int i = 0; i < wakeFrames.length;i++)
        {
            wakeFrames[i] = Bitmap.createBitmap(wakeImg,i*width,0,width,height);
        }
        width = explosion.getWidth()/8;
        height = explosion.getHeight();
        for(int i = 0; i < explosionFrames.length;i++)
        {
            explosionFrames[i] = Bitmap.createBitmap(explosion,i*width,0,width,height);
        }
        boatexplosion.setFrames(explosionFrames);
        boatexplosion.setDelay(175);
        wake.setFrames(wakeFrames);
        wake.setDelay(60);
        boatForward.setFrames(boatForwardFrames);
        boatForward.setDelay(60);
        boatRight.setFrames(boatRightFrames);
        boatRight.setDelay(60);
        boatLeft.setFrames(boatLeftFrames);
        boatLeft.setDelay(60);
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(wake.getImage(),x-10,y,null);
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
    public void updateDeath(){
        if(!boatexplosion.playedOnce()) {
            boatexplosion.update();
        }
    }
    public void drawDeath(Canvas canvas)
    {
        canvas.drawBitmap(boatexplosion.getImage(), x, y, null);

    }
    public void touchUpdate(){
        if(signal == 2) {dx = (int)  -2.6f   *20;}
        if(signal == 1) {dx = (int)  2.6f   *20;}
        if(signal == 0) { dx = 0; }
        if((x > 0 && (signal == 1) || ((signal == 2) && x < limit-boatRightImg.getWidth()/4))){
            x -= dx;
        }
        super.update();
        hitbox.set(hitbox.left + boatForwardImg.getWidth() / 16, hitbox.top,
                hitbox.right - boatForwardImg.getWidth() / 16, hitbox.bottom - boatForwardImg.getHeight() / 4);
        boatForward.update();
        boatLeft.update();
        boatRight.update();
        wake.update();
    }
    public void touchDraw(Canvas canvas){
        canvas.drawBitmap(wake.getImage(),x-10,y,null);
        if(signal == 2){
            canvas.drawBitmap(boatRight.getImage(), x, y, null);
        }
        else if(signal == 1){
            canvas.drawBitmap(boatLeft.getImage(), x, y, null);
        }
        else {
            canvas.drawBitmap(boatForward.getImage(), x, y, null);
        }
    }
    public void moveRight(){ signal = 2;}
    public void moveLeft(){ signal = 1;}
    public void straighten(){signal = 0;}
}
