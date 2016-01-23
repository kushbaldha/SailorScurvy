package orangeboat.sailorscurvy;

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
    public float lastX;
    private SensorManager sensorManager;
    private Sensor rotation;

    public SensorData(Context context){
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_GAME);
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
        lastX = event.values[0];
        if(lastX < .05f && lastX > -.05f){
            lastX = 0;
        }
        if(lastX > 0){
           // v.vibrate(100);

        }
        if(lastX < 0){
          //  v.vibrate(100);
        }
    }

    public void register(){
        sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregister(){
        sensorManager.unregisterListener(this);
    }
}
