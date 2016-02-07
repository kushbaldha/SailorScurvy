package orangeboat.sailorscurvy.Panels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import orangeboat.sailorscurvy.Display;
import orangeboat.sailorscurvy.Entities.Boat;
import orangeboat.sailorscurvy.Entities.Citrus;
import orangeboat.sailorscurvy.Entities.Water;

/**
 * Created by Kush on 1/29/2016.
 */
public class GamePanel
{
    Boat boat;
    Citrus orange;
    Water water;
    int x;
    public int score = 0;
    public int highScore = score;
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    public GamePanel(Bitmap orange, Bitmap img, Bitmap img2, Bitmap img3,Bitmap wake,Bitmap water, int x)
    {
        this.x = x;
        this.orange = new Citrus(orange, (int)(Math.random()*(x-200)), 0, 3);
        boat = new Boat(img, img2, img3, 500,1000, wake, x);
        this.water = new Water(water);
        paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint.setColor(Color.WHITE);
        paint.setTextSize(100f);
    }
    public void load()
    {
        boat.load();
        orange.load();
        water.load();
    }
    public void draw(Canvas canvas)
    {
        canvas.drawRect(0, 0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint2);
       // water.draw(canvas);
        boat.draw(canvas);
        orange.draw(canvas);
        canvas.drawText("" + score, 0, 100, paint);
        canvas.drawText("High Score:" + highScore, 0, 500, paint);
    }
    public void update()
    {
        water.update();
        if(boat.hitbox.intersect(orange.hitbox)){
            orange.resetX((int) (Math.random() * (x-200)));
            score++;
            if(score>highScore)
                setHighScore(score);
        }
        boat.update();
        orange.update(x);
        if(orange.hitbox.bottom >= 1800){
            orange.resetX((int)(Math.random()*(x-200)));
        }
    }
    public void setHighScore(int num)
    {
        highScore = num;
    }
}
