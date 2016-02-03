package orangeboat.sailorscurvy.Input;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

/**
 * Created by Jay on 1/22/2016.
 */
public class SensorData implements SensorEventListener {
    Vibrator v;
    public static float lastX;
    private SensorManager sensorManager;
    //private SensorManager sensorManagerOrientation;
   // private Sensor orientation;
    private Sensor rotation;
    private float gravity;
    private float linear_acceleration;
    final float alpha = (float)0.8;


    public SensorData(Context context){
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       // sensorManagerOrientation = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
      //  orientation = sensorManagerOrientation.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_GAME);
       // sensorManagerOrientation.registerListener(this, orientation, SensorManager.SENSOR_DELAY_GAME);
        v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1){
        // TODO

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        /*deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);
        if (deltaX < 3)
            deltaX = 0;
        if (deltaY < 3)
            deltaY = 0;
        */
        /*
        gravity = alpha * gravity + (1 - alpha) * event.values[0];
        linear_acceleration = event.values[0] - gravity;
        System.out.println(linear_acceleration);
        lastX = -linear_acceleration;
        */
        lastX = event.values[0];
        if(lastX < .05f && lastX > -.05f){
            lastX = 0;
        }
        if( lastX > 3f){
            lastX = 3f;
        }
        if( lastX < -3f){
            lastX = -3f;
        }
      /*  if(lastX > 0){
            v.vibrate(100);

        }
        if(lastX < 0){
            v.vibrate(100);
        }*/
    }

    public void register(){
        sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_GAME);
        //sensorManagerOrientation.registerListener(this, orientation, SensorManager.SENSOR_DELAY_GAME);
    }

    public void unregister(){
        sensorManager.unregisterListener(this);
       // sensorManagerOrientation.unregisterListener(this);
    }
}
