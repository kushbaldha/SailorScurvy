package orangeboat.sailorscurvy.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Jay on 1/30/2016.
 */
public class Citrus extends Item {
    // type, 1 = lemon, 2 = lime, 3 = orange
    int type;
    public Citrus(Bitmap img, int x, int y, int type){
        super(img, x, y);
        this.type = type;
    }
    public void load(){
        super.load();
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(itemAnimation.getImage(), x, y, null);
     //   canvas.drawRect(hitbox, paint);
    }
    public void update(){
        super.update();
    }
}
