package orangeboat.sailorscurvy.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Kush on 1/29/2016.
 */
public class Entity
{
    Rect hitbox;

    int TH;
    int TW;
    int x;
    int y;
    public Entity(Bitmap img,int x, int y)
    {
        this.x = x;
        this.y = y;
        TW = img.getWidth();
        TH = img.getHeight();
        hitbox = new Rect(x,y,x+TW,y+TH);
    }
    public void draw(Canvas canvas)
    {
    }
    public void update()
    {

    }
    public void load(Bitmap img)
    {
    }
}
