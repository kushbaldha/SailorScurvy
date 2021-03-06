package orangeboat.sailorscurvy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import orangeboat.sailorscurvy.Input.IMGLoader;
import orangeboat.sailorscurvy.Input.SFXLoader;
import orangeboat.sailorscurvy.Input.SensorData;
import orangeboat.sailorscurvy.Input.TouchEvents;
import orangeboat.sailorscurvy.Panels.EndPanel;
import orangeboat.sailorscurvy.Panels.GamePanel;
import orangeboat.sailorscurvy.Panels.PausePanel;
import orangeboat.sailorscurvy.Panels.TitlePanel;
import orangeboat.sailorscurvy.Threads.MainThread;

/**
 * Created by Kush on 1/20/2016.
 */
public class Display extends SurfaceView implements SurfaceHolder.Callback
{
    TouchEvents touch;
    MainThread mainThread;
    SurfaceHolder contextHolder;
    Paint paint;
    public static DisplayMetrics displayMetrics;
    GamePanel gamePanel;
    TitlePanel titlePanel;
    EndPanel endPanel;
    PausePanel pausePanel;
    SensorData sensor;
    IMGLoader imageLoader;
    SFXLoader sfx;
    int panelSwitch = 0;
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
       // paint.setColor(Color.rgb(31,123,237));
        paint.setColor(Color.WHITE);
        displayMetrics = m;
        sensor = d;
        x = displayMetrics.widthPixels;
        //y = displayMetrics.heightPixels;
        gamePanel = new GamePanel(x);
        titlePanel = new TitlePanel();
        endPanel = new EndPanel();
        pausePanel = new PausePanel();
        imageLoader = new IMGLoader(getResources(), m, gamePanel,titlePanel,endPanel, pausePanel);
        sfx = new SFXLoader(this.getContext(), gamePanel);
        /*Bitmap orange = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                        R.drawable.orange), BitmapFactory.decodeResource(getResources(), R.drawable.orange).getWidth() / 2,
                BitmapFactory.decodeResource(getResources(), R.drawable.orange).getHeight() / 2, true);
        Bitmap wake = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                        R.drawable.wake), BitmapFactory.decodeResource(getResources(), R.drawable.wake).getWidth()*2,
                BitmapFactory.decodeResource(getResources(), R.drawable.wake).getHeight()* 2, true);
        Bitmap water = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                        R.drawable.water2), BitmapFactory.decodeResource(getResources(), R.drawable.water3).getWidth()*2,
                BitmapFactory.decodeResource(getResources(), R.drawable.water3).getHeight()* 2, true);*/
        //gamePanel = imageLoader.getGamePanel();
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        titlePanel.load();
        gamePanel.load();
        endPanel.load();
        pausePanel.load();
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
        if(panelSwitch == 0)
            titlePanel.update();
        else if(panelSwitch == 1) {
            gamePanel.update();
        }
        if(gamePanel.gameEnded){
            panelSwitch = 2;
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        touch = new TouchEvents(event);
        if (panelSwitch == 0) {
            touch.checkTitle(titlePanel);
            touch.toGameToggleInfo(gamePanel,pausePanel,endPanel);
            if (touch.switcher) {
                panelSwitch = 1;
                touch.switcher = false;
            }
        }
        if (panelSwitch == 1) {
            touch.checkGame(gamePanel);
            touch.toGameToggleInfo(gamePanel, pausePanel, endPanel);
            if (touch.switcher) {
                panelSwitch = 3;
                touch.switcher = false;
            }
        }
        if (panelSwitch == 2) {
            gamePanel.load();
            touch.checkEnd(endPanel);
            touch.toGameToggleInfo(gamePanel, pausePanel, endPanel);
            if (touch.switcher) {
                panelSwitch = 1;
                touch.switcher = false;
            }
        }
        if(panelSwitch == 3) {
            touch.checkPause(pausePanel);
            touch.toGameToggleInfo(gamePanel,pausePanel,endPanel);
            if (touch.switcher) {
                panelSwitch = 1;
                touch.switcher = false;
            }
        }
        return true;
    }
    @Override
    public void draw(Canvas canvas)
    {
        /*paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(rect,paint);
        paint.setTextSize(200f);*/
        if(panelSwitch == 0)
        {
            titlePanel.draw(canvas);
        }
        else if(panelSwitch == 1) {
            paint.setColor(Color.RED);
            paint.setTextSize(70f);
            gamePanel.draw(canvas);
          //  canvas.drawText("" + SensorData.lastX, 400, 200, paint);
            // canvas.drawText("" + (SensorData.lastX*15), 100, 500, paint);
        }
        else if (panelSwitch == 2) {
            endPanel.draw(canvas);
        }
        else if(panelSwitch == 3){
            pausePanel.draw(canvas);
        }
        canvas.drawText("" + gamePanel.score, Display.displayMetrics.widthPixels/2, Display.displayMetrics.heightPixels-paint.getTextSize(), paint);
        canvas.drawText("High Score:" + gamePanel.highScore, 0, Display.displayMetrics.heightPixels - paint.getTextSize(), paint);
    }
    public void newThread() {
        mainThread = new MainThread(contextHolder, this);
    }
}
