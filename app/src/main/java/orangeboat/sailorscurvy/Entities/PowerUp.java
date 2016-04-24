package orangeboat.sailorscurvy.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Jay on 1/30/2016.
 */
public class PowerUp extends Item {
    // int type 1 = magnet, 2 = shield, 3 = slow, 4 = speed
    int type;
    public PowerUp(Bitmap img, int x, int y, int type){
        super(img, x, y);
        this.type = type;
    }
    public void load(){
        super.load();
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(itemAnimation.getImage(), x, y, null);
    }
    public void update(int x){
        y += (x+hitbox.top)*.035;
        super.update();
    }
    public void resetX(int x){
        super.x = x;
        super.y = 0;
    }
}
