package orangeboat.sailorscurvy.Panels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import orangeboat.sailorscurvy.Entities.Boat;
import orangeboat.sailorscurvy.Entities.Citrus;

/**
 * Created by Kush on 1/29/2016.
 */
public class GamePanel
{
    Boat boat;
    Citrus orange;
    int x;
    int count = 0;
    Paint paint = new Paint();
    public GamePanel(Bitmap orange, Bitmap img, Bitmap img2, Bitmap img3, int x)
    {
        this.x = x;
        this.orange = new Citrus(orange, (int)(Math.random()*(x-200)), 0, 3);
        boat = new Boat(img, img2, img3, 500,1000, x);
        paint.setColor(Color.WHITE);
        paint.setTextSize(100f);
    }
    public void load()
    {
        boat.load();
        orange.load();
    }
    public void draw(Canvas canvas)
    {
        boat.draw(canvas);
        orange.draw(canvas);
        canvas.drawText("" + count, 0, 100, paint);
    }
    public void update()
    {
        if(boat.hitbox.intersect(orange.hitbox)){
            orange.resetX((int) (Math.random() * (x-200)));
            count++;
        }
        boat.update();
        orange.update(x);
        if(orange.hitbox.bottom >= 1800){
            orange.resetX((int)(Math.random()*(x-200)));
        }
    }
}
