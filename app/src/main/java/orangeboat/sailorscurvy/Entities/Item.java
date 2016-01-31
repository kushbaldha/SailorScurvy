package orangeboat.sailorscurvy.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import orangeboat.sailorscurvy.Threads.Animation;

/**
 * Created by Jay on 1/30/2016.
 */
public class Item extends Entity {
 //   Paint paint = new Paint();
    Animation itemAnimation = new Animation();
    Bitmap[] frames = new Bitmap[4];
    Bitmap img;
    public Item(Bitmap img, int x, int y){
        super(img, x, y);
        this.img = img;
       // hitbox.set(hitbox.left+img.getWidth()/12,hitbox.top+img.getHeight()/15,
       //         hitbox.right-img.getWidth()/12,hitbox.bottom-img.getHeight()/2);
    }
    public void load(){
   //     paint.setColor(Color.WHITE);
        int width = img.getWidth()/4;
        int height = img.getHeight();
        for(int i = 0; i < frames.length;i++)
        {
            frames[i] = Bitmap.createBitmap(img,i*width,0,width,height);
        }
        itemAnimation.setFrames(frames);
        itemAnimation.setDelay(100);
    }
    public void draw(Canvas canvas){
    }
    public void update(){
        super.update();
        hitbox.set(hitbox.left+img.getWidth()/12,hitbox.top+img.getHeight()/15,
               hitbox.right-img.getWidth()/12,hitbox.bottom-img.getHeight()/2);
        itemAnimation.update();
    }
}
