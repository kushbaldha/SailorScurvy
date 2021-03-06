package orangeboat.sailorscurvy.Input;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import orangeboat.sailorscurvy.Display;
import orangeboat.sailorscurvy.Panels.EndPanel;
import orangeboat.sailorscurvy.Panels.GamePanel;
import orangeboat.sailorscurvy.Panels.PausePanel;
import orangeboat.sailorscurvy.Panels.TitlePanel;
import orangeboat.sailorscurvy.R;

/**
 * Created by Jay on 1/30/2016.
 */
public class IMGLoader
{
    Bitmap temp;
    Resources resources;
    DisplayMetrics displayMetrics;
    GamePanel g;
    TitlePanel t;
    EndPanel e;
    PausePanel p;
    public IMGLoader(Resources resources, DisplayMetrics m, GamePanel gamePanel,TitlePanel titlePanel, EndPanel endPanel, PausePanel pausePanel)
    {
        g = gamePanel;
        t = titlePanel;
        e = endPanel;
        p = pausePanel;
        this.resources = resources;
        displayMetrics = m;
        start();
    }
    public void start(){
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.orange), BitmapFactory.decodeResource(resources, R.drawable.orange).getWidth() , BitmapFactory.decodeResource(resources, R.drawable.orange).getHeight() , true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.wake), BitmapFactory.decodeResource(resources, R.drawable.wake).getWidth() * 2, BitmapFactory.decodeResource(resources, R.drawable.wake).getHeight() * 2, true);
        g.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.boatforward);
        g.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.boatleft);
        g.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.boatright);
        g.imgLoad(temp);
        temp =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.water2), BitmapFactory.decodeResource(resources, R.drawable.water3).getWidth() * 2, BitmapFactory.decodeResource(resources, R.drawable.water3).getHeight() * 2, true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.lemon), BitmapFactory.decodeResource(resources, R.drawable.lemon).getWidth(), BitmapFactory.decodeResource(resources, R.drawable.lemon).getHeight(), true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.lime), BitmapFactory.decodeResource(resources, R.drawable.lime).getWidth(), BitmapFactory.decodeResource(resources, R.drawable.lime).getHeight(), true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.magnet), BitmapFactory.decodeResource(resources, R.drawable.magnet).getWidth(), BitmapFactory.decodeResource(resources, R.drawable.magnet).getHeight(), true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.shield), BitmapFactory.decodeResource(resources, R.drawable.shield).getWidth(), BitmapFactory.decodeResource(resources, R.drawable.shield).getHeight(), true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.white), BitmapFactory.decodeResource(resources, R.drawable.white).getWidth(), BitmapFactory.decodeResource(resources, R.drawable.white).getHeight(), true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.black), BitmapFactory.decodeResource(resources, R.drawable.black).getWidth() , BitmapFactory.decodeResource(resources, R.drawable.black).getHeight(), true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.barrel), BitmapFactory.decodeResource(resources, R.drawable.barrel).getWidth(), BitmapFactory.decodeResource(resources, R.drawable.barrel).getHeight(), true);
        g.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.explosionboat);
        g.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.pause);
        g.imgLoad(temp);

        temp = BitmapFactory.decodeResource(resources, R.drawable.retrybutton);
        e.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.resumebutton);
        p.imgLoad(temp);

        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.sailor), BitmapFactory.decodeResource(resources, R.drawable.sailor).getWidth() / 2, BitmapFactory.decodeResource(resources, R.drawable.sailor).getHeight() / 2, true);
        t.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.playbutton1);
        t.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources,R.drawable.quitbutton);
        t.imgLoad(temp);
        e.imgLoad(temp);
        p.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources,R.drawable.touchtoggle);
        t.imgLoad(temp);
        e.imgLoad(temp);
        p.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources,R.drawable.tilttoggle);
        t.imgLoad(temp);
        e.imgLoad(temp);
        p.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources,R.drawable.titletext);
        t.imgLoad(temp);
    }
    /*
    public GamePanel getGamePanel()
    {
        Bitmap temp1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.orange), BitmapFactory.decodeResource(resources, R.drawable.orange).getWidth() / 2, BitmapFactory.decodeResource(resources, R.drawable.orange).getHeight() / 2, true);
        Bitmap temp2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.wake), BitmapFact
        ory.decodeResource(resources, R.drawable.wake).getWidth()*2, BitmapFactory.decodeResource(resources, R.drawable.wake).getHeight()* 2, true);
        Bitmap temp3 =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.water2), BitmapFactory.decodeResource(resources, R.drawable.water3).getWidth()*2,BitmapFactory.decodeResource(resources, R.drawable.water3).getHeight()* 2, true);
        return new GamePanel(temp1, BitmapFactory.decodeResource(resources, R.drawable.boatforward),
                BitmapFactory.decodeResource(resources,
                        R.drawable.boatleft),BitmapFactory.decodeResource(resources, R.drawable.boatright),temp2,temp3, displayMetrics.widthPixels);
    }
    */
}
