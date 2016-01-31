package orangeboat.sailorscurvy.Panels;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import orangeboat.sailorscurvy.Entities.Boat;
import orangeboat.sailorscurvy.Entities.Citrus;

/**
 * Created by Kush on 1/29/2016.
 */
public class GamePanel
{
    Boat boat;
    Citrus orange;
    public GamePanel(Bitmap orange, Bitmap img, Bitmap img2, Bitmap img3, int x)
    {
        this.orange = new Citrus(orange, 500, 500, 3);
        boat = new Boat(img, img2, img3, 500,1000, x);
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
    }
    public void update()
    {
    boat.update();
        orange.update();
    }
}
