package orangeboat.sailorscurvy.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Jay on 2/19/2016.
 */
public class Barrel extends Item {
    public Barrel(Bitmap img, int x, int y){
        super(img, x, y);
    }
    public void load(){
        super.load();
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(itemAnimation.getImage(), x, y, null);
        //  canvas.drawRect(hitbox, paint);
    }
    public void update(int x){
        y += (x+hitbox.top)*.035;
       // hitbox.set(hitbox.left+img.getWidth()/18,hitbox.top+img.getHeight()/7,
       //         hitbox.right-img.getWidth()/20,hitbox.bottom-img.getHeight()/2);
       // itemAnimation.update();
        super.update();
    }
    public void resetX(int x){
        super.x = x;
        super.y = 0;
    }
}
