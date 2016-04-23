package orangeboat.sailorscurvy.Input;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

import orangeboat.sailorscurvy.Panels.GamePanel;
import orangeboat.sailorscurvy.Panels.TitlePanel;

/**
 * Created by Jay on 4/21/2016.
 */
public class TouchEvents {
    public boolean switcher = false;
    MotionEvent event;
    int x, y;
    int pointerCount;
    public int touchtriggerOn = 0;
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

    public void checkTitle(TitlePanel title) {
        int action = MotionEventCompat.getActionMasked(event);
        if (MotionEvent.ACTION_DOWN == action) {
            System.out.println(x + " " + y);
            if (title.rectPlay.contains(x, y)) {
                System.out.println("Play button pressed");
                switcher = true;
            }
            else if(title.rectToggle.contains(x,y)){
                System.out.println("TOGGLED");
                if(title.touchOn){
                    title.touchOn = false;
                    touchtriggerOn = 1;
                }
                else{
                    title.touchOn = true;
                    touchtriggerOn = 2;
                }
            }
        }
    }
    public void titleToGameToggleInfo(GamePanel gamePanel){
        if(touchtriggerOn == 2) {
            gamePanel.touchOn = 2;
        }
        if(touchtriggerOn == 1){
            gamePanel.touchOn = 1;
        }
    }
    public void checkGame(GamePanel gamePanel) {
        int action = MotionEventCompat.getActionMasked(event);
        // FIRST TOUCH
        if (MotionEvent.ACTION_DOWN == action) {
            gamePanel.downTouch(x, y, event.getPointerId(event.getActionIndex()));
          //  System.out.println(x+ " is the x coordinate " + y + " is the y coordinate");
        }
        if(MotionEvent.ACTION_UP == action)
        {
            gamePanel.upTouch(x, y, event.getPointerId(event.getActionIndex()));
        }

        // SECOND TOUCH
        if(MotionEvent.ACTION_POINTER_DOWN == action)
        {
            gamePanel.downTouch(x, y, event.getPointerId(event.getActionIndex()));
        }
        if(MotionEvent.ACTION_POINTER_UP == action) {
            gamePanel.upTouch(x, y, event.getPointerId(event.getActionIndex()));
        }
        if(MotionEvent.ACTION_MOVE == action)
        {
            int pointerCount = event.getPointerCount();
            for(int i = 0; i < pointerCount; ++i) {

                x = (int)event.getX(i);
                y = (int)event.getY(i);
                gamePanel.downTouch(x,y,event.getPointerId(i));
            }
        }
    }
}