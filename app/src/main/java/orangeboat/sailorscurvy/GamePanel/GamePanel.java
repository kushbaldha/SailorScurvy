package orangeboat.sailorscurvy.GamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import orangeboat.sailorscurvy.Entities.Boat;

/**
 * Created by Kush on 1/29/2016.
 */
public class GamePanel
{
    Boat boat;
    public GamePanel(Bitmap img, Bitmap img2, Bitmap img3, int x)
    {
        boat = new Boat(img, img2, img3, 500,1000, x);
    }
    public void load()
    {
        boat.load();
    }
    public void draw(Canvas canvas)
    {
        boat.draw(canvas);
    }
    public void update()
    {
    boat.update();
    }
}
