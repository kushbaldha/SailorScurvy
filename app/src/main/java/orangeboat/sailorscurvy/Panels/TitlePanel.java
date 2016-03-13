package orangeboat.sailorscurvy.Panels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import orangeboat.sailorscurvy.Display;
import orangeboat.sailorscurvy.Threads.Animation;

/**
 * Created by Jay on 1/30/2016.
 */
public class TitlePanel {
    ArrayList<Bitmap> loader = new ArrayList<>();
    Bitmap backgroundimg, playButton, titleWords;
    Animation background;
    Bitmap [] backgroundFrames = new Bitmap[8];
    Paint paint;
    public TitlePanel()
    {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        background = new Animation();
    }
    public void update(){
        background.update();
    }
    public void draw(Canvas canvas){
        canvas.drawRect(0,0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint);
        canvas.drawBitmap(background.getImage(), 50, 50, null);
    }
    public void load()
    {
        this.backgroundimg = loader.get(0);
        int width = 200;
        int height = 390;
        for(int i = 0; i< backgroundFrames.length; i++)
        {
            backgroundFrames[i] = Bitmap.createBitmap(backgroundimg,i*width,0,width,height);
            backgroundFrames[i] = Bitmap.createScaledBitmap(backgroundFrames[i],Display.displayMetrics.widthPixels-100,Display.displayMetrics.heightPixels-100,true);
        }
        background.setFrames(backgroundFrames);
        background.setDelay(60);
    }
    public void remove(){

    }
    public void imgLoad(Bitmap image) {loader.add(image);}

}
