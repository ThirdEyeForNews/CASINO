package com.androidgames.framework.implementation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Manu on 7/13/2017.
 */

public class CompassHandler implements SensorEventListener {

    float yaw;
    float pitch;
    float roll;

    public CompassHandler(Context context){
        SensorManager sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getSensorList(Sensor.TYPE_ORIENTATION).size() !=0){
            Sensor compass = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION).get(0);
            sensorManager.registerListener(this,compass,SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        yaw = sensorEvent.values[0];
        pitch = sensorEvent.values[1];
        roll = sensorEvent.values[2];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float getYaw(){
        return yaw;
    }
    public float getPitch(){
        return pitch;
    }
    public float getRoll(){
        return roll;
    }
}
