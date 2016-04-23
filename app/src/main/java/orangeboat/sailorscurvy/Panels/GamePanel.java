package orangeboat.sailorscurvy.Panels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;

import java.util.ArrayList;

import orangeboat.sailorscurvy.Display;
import orangeboat.sailorscurvy.Entities.Barrel;
import orangeboat.sailorscurvy.Entities.Boat;
import orangeboat.sailorscurvy.Entities.Citrus;
import orangeboat.sailorscurvy.Entities.Water;

/**
 * Created by Kush on 1/29/2016.
 */
public class GamePanel
{
    int usedForBarrel;
    ArrayList <Bitmap> loader = new ArrayList<>();
    ArrayList<MediaPlayer> sfxloader = new ArrayList<>();
    Barrel barrel;
    Boat boat;
    Citrus orange;
    Water water;
    public Rect left, right, pause;
    public int touchOn = 1;
    public boolean gameEnded;
    int x;
    int sfxstart = 0;
    public int score = 0;
    public int highScore = score;
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();
    public GamePanel(int x){
        this.x = x;
        paint2 = new Paint();
        paint2.setColor(Color.rgb(31, 123, 237));
        paint.setColor(Color.WHITE);
        paint.setTextSize(100f);
        paint3.setColor(Color.BLACK);
        left = new Rect(0, 0, Display.displayMetrics.widthPixels/2,Display.displayMetrics.heightPixels );
        right = new Rect(Display.displayMetrics.widthPixels/2, 0, Display.displayMetrics.widthPixels,Display.displayMetrics.heightPixels);
    }
    /*
    public GamePanel(Bitmap orange, Bitmap img, Bitmap img2, Bitmap img3,Bitmap wake,Bitmap water, int x)
    {
        this.x = x;
        this.orange = new Citrus(orange, (int)(Math.random()*(x-200)), 0, 3);
        boat = new Boat(img, img2, img3, 500,1000, wake, x);
        this.water = new Water(water);
        paint2 = new Paint();
        paint2.setColor(Color.rgb(31,123,237));
        paint.setColor(Color.WHITE);
        paint.setTextSize(100f);
    }
    */
    public void load()
    {
        gameEnded = false;
        //orange, wake, boat, boat, boat, water lemon lime magnet shield white black barrel explosion, pause
        //boat beep errg
        sfxloader.get(0).seekTo(sfxstart);
        sfxloader.get(2).start();

        orange = new Citrus(loader.get(0), (int)(Math.random()*(x-200)), 0, 3);
        boat = new Boat(loader.get(2), loader.get(3), loader.get(4), 500,1000, loader.get(1), x, loader.get(13));
        water = new Water(loader.get(5));
        //usedForBarrel = barrelRoll(orange.hitbox.left);
        barrel = new Barrel(loader.get(12), (int)(Math.random()*(x-200)), 0);
        pause = new Rect(Display.displayMetrics.widthPixels-loader.get(14).getWidth(),0, Display.displayMetrics.widthPixels, loader.get(14).getHeight());

        boat.load();
        orange.load();
        water.load();
        barrel.load();
    }
    public void draw(Canvas canvas)
    {
        canvas.drawRect(0, 0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint2);
      //  canvas.drawRect(left, paint);
      //  canvas.drawRect(right, paint3);
        if(boat.hitbox.intersect(barrel.hitbox)){
            if(boat.boatexplosion.playedOnce()){
                canvas.drawRect(0, 0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint3);
                gameEnded = true;
                return;
            }
            canvas.drawText("you lost tbh fam", 0,1000, paint);
            boat.drawDeath(canvas);
            return;
        }
        water.draw(canvas);
        orange.draw(canvas);
        barrel.draw(canvas);
        if(touchOn == 2){
            boat.touchDraw(canvas);
        }
        else if (touchOn == 1){
            boat.draw(canvas);
        }
        canvas.drawBitmap(loader.get(14), Display.displayMetrics.widthPixels-loader.get(14).getWidth(), 0, null);
        canvas.drawText("" + score, 0, 100, paint);
        canvas.drawText("High Score:" + highScore, 0, 500, paint);

    }
    public void update()
    {
        if(boat.hitbox.intersect(barrel.hitbox)){
            boat.updateDeath();
            return;
        }
        water.update();
        if(boat.hitbox.intersect(orange.hitbox)){
            orange.resetX((int) (Math.random() * (x-200)));
            score++;
            /*
            if(score > 10){
                if(score > 20){
                    sfxstart= sfxloader.get(1).getCurrentPosition();
                    sfxloader.get(1).pause();
                    sfxloader.get(2).seekTo(sfxstart);
                    sfxloader.get(2).start();
                }
                else{
                    sfxstart= sfxloader.get(0).getCurrentPosition();
                    sfxloader.get(0).pause();
                    sfxloader.get(1).seekTo(sfxstart);
                    sfxloader.get(1).start();
                }
            }
            */
            if (score > highScore)
                setHighScore(score);
        }
        if(touchOn == 2){
            boat.touchUpdate();
        }
        else if (touchOn == 1){
            boat.update();
        }
        orange.update(x);
        barrel.update(x);
        if (orange.hitbox.bottom >= 1800){
            orange.resetX((int)(Math.random()*(x-200)));

        }
        if(barrel.hitbox.bottom >= 1900){
            barrel.resetX(((int) (Math.random()*(x-200))));
        }
    }
    public void setHighScore(int num)
    {
        highScore = num;
    }
    public void imgLoad(Bitmap image) {loader.add(image);}
    public void sfxLoad(MediaPlayer sfx){ sfxloader.add(sfx);}
    public int barrelRoll(int anythingbut){
        int temp = (int)(Math.random()*(x-200));
        boolean flag = true;
        for(;flag;) {
            if (Math.abs(anythingbut - temp) > 100) {
                flag = false;
            }
        }
            return temp;
    }
    public void downTouch(int x, int y, int pointerNumber) {
        if(left.contains(x,y)){
            boat.moveLeft();
        }
        if(right.contains(x,y)){
            boat.moveRight();
        }
    }
    public void upTouch(int x, int y,int pointerNumber) {
        boat.straighten();
    }
}
