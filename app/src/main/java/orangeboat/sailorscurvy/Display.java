package orangeboat.sailorscurvy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Kush on 1/20/2016.
 */
public class Display extends SurfaceView implements SurfaceHolder.Callback
{
    MainThread mainThread;
    Paint paint;
    DisplayMetrics displayMetrics;
    public Display(Context context) {
        super(context);
        getHolder().addCallback(this);
        mainThread = new MainThread(getHolder(),this);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        displayMetrics = new DisplayMetrics();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        // it might take several tries to stop secondthread so this is needed
        // a try catch loop is created

        while (retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }
    public void update()
    {

    }

    @Override
    public void draw(Canvas canvas)
    {
        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,1080, 1766, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(250,250,500,500,paint);
    }
}
