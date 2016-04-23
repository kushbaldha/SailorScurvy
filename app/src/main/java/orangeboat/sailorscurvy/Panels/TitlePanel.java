package orangeboat.sailorscurvy.Panels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.sailorscurvy.Display;
import orangeboat.sailorscurvy.Threads.Animation;

/**
 * Created by Jay on 1/30/2016.
 */
public class TitlePanel {
    ArrayList<Bitmap> loader = new ArrayList<>();
    Bitmap sailorimg, playButton, quitButton, titleWords, touch, tilt;
    Animation sailor;
    Bitmap [] sailorFrames = new Bitmap[8];
    Paint paint;
    public Rect rectPlay, rectQuit, rectToggle;
    private int x,y, qx, qy, buttonX, buttonY;
    private int togglex, toggley, togglewidth, toggleheight;
    public boolean touchOn = false;
    public TitlePanel()
    {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        sailor = new Animation();
    }
    public void update(){
        sailor.update();
    }
    public void draw(Canvas canvas){
        canvas.drawRect(0,0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint);
        canvas.drawBitmap(sailor.getImage(), 50, 50, null);
        canvas.drawBitmap(titleWords, Display.displayMetrics.widthPixels/2-titleWords.getWidth()/2 ,(Display.displayMetrics.heightPixels/9), null);
        canvas.drawBitmap(playButton,x,y,null);
        if(touchOn) {
            canvas.drawBitmap(touch,togglex, toggley, null);
        }
        else {
            canvas.drawBitmap(tilt, togglex, toggley, null);
        }
    }
    public void load()
    {
        this.sailorimg = loader.get(0);
        this.playButton= loader.get(1);
        this.quitButton = loader.get(2);
        this.touch = loader.get(3);
        this.tilt = loader.get(4);
        this.titleWords = loader.get(5);
        buttonX = playButton.getWidth();
        buttonY = playButton.getHeight();
        togglewidth = tilt.getWidth();
        toggleheight = tilt.getHeight();
        int width = 200/2;
        int height = 390/2;
        for(int i = 0; i< sailorFrames.length; i++)
        {
            sailorFrames[i] = Bitmap.createBitmap(sailorimg,i*width,0,width,height);
      //      backgroundFrames[i] = Bitmap.createScaledBitmap(backgroundFrames[i],Display.displayMetrics.widthPixels-100,Display.displayMetrics.heightPixels-100,true);
        }
        sailor.setFrames(sailorFrames);
        sailor.setDelay(80);
        x = (int) (Display.displayMetrics.widthPixels/2);
        y = (int) ( Display.displayMetrics.heightPixels/1.3);
        qx = (int) (Display.displayMetrics.widthPixels/3.3);
        qy = (int) ( Display.displayMetrics.heightPixels/1.3);
        togglex  = (int) ( Display.displayMetrics.widthPixels/9.5);
        toggley = (int) ( Display.displayMetrics.heightPixels/1.35);
        rectPlay = new Rect(x,y, (x + buttonX), (y + buttonY));
        rectQuit = new Rect(qx,qy, (qx + buttonX), (qy + buttonY));
        rectToggle = new Rect(togglex, toggley, (togglex+togglewidth), (toggley+toggleheight));
    }
    public void remove(){

    }
    public void imgLoad(Bitmap image) {loader.add(image);}

}
