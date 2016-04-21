package orangeboat.sailorscurvy.Input;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

import orangeboat.sailorscurvy.Panels.TitlePanel;

/**
 * Created by Jay on 4/21/2016.
 */
public class TouchEvents {
    public boolean switcher = false;
    MotionEvent event;
    int x, y;
    int pointerCount;

    public TouchEvents(MotionEvent event) {
        this.event = event;
        pointerCount = event.getPointerCount();
        if (pointerCount >= 2) {
            x = (int) event.getX(event.getPointerId(event.getActionIndex()));
            y = (int) event.getY(event.getPointerId(event.getActionIndex()));
        } else {
            x = (int) event.getX();
            y = (int) event.getY();
        }
    }

    public void checkForPlayPress(TitlePanel menu) {
        int action = MotionEventCompat.getActionMasked(event);
        if (MotionEvent.ACTION_DOWN == action) {
            System.out.println(x + " " + y);
            if (menu.rectPlay.contains(x, y)) {
                System.out.println("Play button pressed");
                switcher = true;
            }
        }
    }
}