package orangeboat.sailorscurvy.Input;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import orangeboat.sailorscurvy.Panels.GamePanel;
import orangeboat.sailorscurvy.Panels.TitlePanel;
import orangeboat.sailorscurvy.R;

/**
 * Created by Jay on 1/30/2016.
 */
public class IMGLoader
{
    Resources resources;
    DisplayMetrics displayMetrics;
    public IMGLoader(Resources resources, DisplayMetrics m)
    {
        this.resources = resources;
        displayMetrics = m;
    }

    public GamePanel getGamePanel()
    {
        Bitmap temp1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.orange), BitmapFactory.decodeResource(resources, R.drawable.orange).getWidth() / 2, BitmapFactory.decodeResource(resources, R.drawable.orange).getHeight() / 2, true);
        Bitmap temp2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.wake), BitmapFactory.decodeResource(resources, R.drawable.wake).getWidth()*2, BitmapFactory.decodeResource(resources, R.drawable.wake).getHeight()* 2, true);
        Bitmap temp3 =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.water2), BitmapFactory.decodeResource(resources, R.drawable.water3).getWidth()*2,BitmapFactory.decodeResource(resources, R.drawable.water3).getHeight()* 2, true);
        return new GamePanel(temp1, BitmapFactory.decodeResource(resources, R.drawable.boatforward),
                BitmapFactory.decodeResource(resources,
                        R.drawable.boatleft),BitmapFactory.decodeResource(resources, R.drawable.boatright),temp2,temp3, displayMetrics.widthPixels);
    }
}
