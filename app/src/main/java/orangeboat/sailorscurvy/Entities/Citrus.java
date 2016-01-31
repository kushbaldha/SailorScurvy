package orangeboat.sailorscurvy.Entities;

import android.graphics.Bitmap;

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
}
