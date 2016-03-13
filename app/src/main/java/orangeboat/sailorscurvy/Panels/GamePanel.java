package orangeboat.sailorscurvy.Panels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    int x;
    int sfxstart = 0;
    public int score = 0;
    public int highScore = score;
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    public GamePanel(int x){
        this.x = x;
        paint2 = new Paint();
        paint2.setColor(Color.rgb(31, 123, 237));
        paint.setColor(Color.WHITE);
        paint.setTextSize(100f);
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
        //orange, wake, boat, boat, boat, water lemon lime magnet shield white black barrel
        //boat beep errg
        sfxloader.get(0).seekTo(sfxstart);
        sfxloader.get(2).start();
        orange = new Citrus(loader.get(0), (int)(Math.random()*(x-200)), 0, 3);
        boat = new Boat(loader.get(2), loader.get(3), loader.get(4), 500,1000, loader.get(1), x);
        water = new Water(loader.get(5));

        usedForBarrel = barrelRoll(orange.hitbox.left);

        barrel = new Barrel(loader.get(12), usedForBarrel, 0);

        boat.load();
        orange.load();
        water.load();
        barrel.load();
    }
    public void draw(Canvas canvas)
    {
        canvas.drawRect(0, 0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint2);
        water.draw(canvas);
        boat.draw(canvas);
        orange.draw(canvas);
        barrel.draw(canvas);
        canvas.drawText("" + score, 0, 100, paint);
        canvas.drawText("High Score:" + highScore, 0, 500, paint);
        if(boat.hitbox.intersect(barrel.hitbox)){
            canvas.drawText("you lost tbh fam", 0,1000, paint);

        }
    }
    public void update()
    {
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
        boat.update();
        orange.update(x);
        barrel.update(x);
        if (orange.hitbox.bottom >= 1800){
            orange.resetX((int)(Math.random()*(x-200)));

        }
        if(barrel.hitbox.bottom >= 1900){
            barrel.resetX(((int)(Math.random()*(x-200))));
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
}
