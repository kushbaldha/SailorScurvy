package orangeboat.sailorscurvy;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import orangeboat.sailorscurvy.Input.SensorData;
import orangeboat.sailorscurvy.Panels.GamePanel;
import orangeboat.sailorscurvy.Threads.MainThread;

/**
 * Created by Kush on 1/20/2016.
 */
public class Display extends SurfaceView implements SurfaceHolder.Callback
{
    MainThread mainThread;
    SurfaceHolder contextHolder;
    Paint paint;
    public static DisplayMetrics displayMetrics;
    GamePanel gamePanel;
    SensorData sensor;
    int x;
    int y;
    int dx = 50;
    int dx2 = -50;
    Rect rect = new Rect(x-dx,y-50,x-dx2,y+50);
    public Display(Context context, DisplayMetrics m, SensorData d) {
        super(context);
        getHolder().addCallback(this);
        contextHolder= getHolder();
        mainThread = new MainThread(getHolder(),this);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        displayMetrics = m;
        sensor = d;
        x = displayMetrics.widthPixels;
        //y = displayMetrics.heightPixels;
        Bitmap orange = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                        R.drawable.orange), BitmapFactory.decodeResource(getResources(), R.drawable.orange).getWidth() / 2,
                BitmapFactory.decodeResource(getResources(), R.drawable.orange).getHeight() / 2, true);
        gamePanel = new GamePanel(orange, BitmapFactory.decodeResource(getResources(), R.drawable.boatforward),
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.boatleft),BitmapFactory.decodeResource(getResources(), R.drawable.boatright), x);

    }
    public boolean onTouchEvent(MotionEvent event){
        //
        return true;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gamePanel.load();
        Thread.State state = mainThread.getState();
        if(state == Thread.State.TERMINATED) {
            newThread();
        }
        //once surface is created, we can safely start gameloop
        mainThread.setRunning(true);
         mainThread.start();

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
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
        gamePanel.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        /*paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(rect,paint);
        paint.setTextSize(200f);*/
        paint.setColor(Color.RED);
        paint.setTextSize(200f);
        gamePanel.draw(canvas);
        canvas.drawText("" + SensorData.lastX, 150, 300, paint);
       // canvas.drawText("" + (SensorData.lastX*15), 100, 500, paint);
    }
    public void newThread() {
        mainThread = new MainThread(contextHolder, this);
    }
}
