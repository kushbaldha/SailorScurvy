package orangeboat.sailorscurvy;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Kush on 1/29/2016.
 */
public class GamePanel
{
    Boat boat;
    public GamePanel(Bitmap img)
    {
        boat = new Boat(img,500,500);
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