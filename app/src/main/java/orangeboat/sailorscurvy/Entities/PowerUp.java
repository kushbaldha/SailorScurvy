package orangeboat.sailorscurvy.Entities;

import android.graphics.Bitmap;

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
}
