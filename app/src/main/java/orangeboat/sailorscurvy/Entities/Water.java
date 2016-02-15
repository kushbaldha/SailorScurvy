package orangeboat.sailorscurvy.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import orangeboat.sailorscurvy.Display;
import orangeboat.sailorscurvy.Threads.Animation;

/**
 * Created by Jay on 2/7/2016.
 */
public class Water {
    Bitmap waterimg;
    Animation water = new Animation();
    Bitmap[] waterFrames = new Bitmap[10];
    public Water(Bitmap img){
        waterimg = img;
    }
    public void load(){
        int width = waterimg.getWidth()/10;
        int height = waterimg.getHeight();
        for(int i = 0; i < waterFrames.length;i++)
        {
            waterFrames[i] = Bitmap.createBitmap(waterimg,i*width,0,width,height);
        }
        water.setFrames(waterFrames);
        water.setDelay(60);
    }
    public void update(){
        water.update();
    }
    public void draw(Canvas canvas){
        for(int x = 0; x < Display.displayMetrics.widthPixels; x+=waterimg.getWidth()/10) {
            if(x/(waterimg.getWidth()/10)%2 == 1) water.setRandomFrame();
            for (int y = 0; y < Display.displayMetrics.heightPixels; y += waterimg.getHeight()) {
                canvas.drawBitmap(water.getImage(), x, y, null);
            }
        }
    }

}
