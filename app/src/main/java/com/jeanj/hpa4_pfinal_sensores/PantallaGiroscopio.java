package com.jeanj.hpa4_pfinal_sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaGiroscopio extends AppCompatActivity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    Button buttongyro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_giroscopio);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        buttongyro=findViewById(R.id.buttongyro);

        buttongyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, MainActivity.class);

            }
        });
    }
    private int contador = 0;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            if(sensorEvent.values[2] > 0.5f) { // anticlockwise
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            } else if(sensorEvent.values[2] < -0.5f) { // clockwise
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            }

            if(sensorEvent.values[0] > 0.5f) { // anticlockwise
                getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            } else if(sensorEvent.values[0] < -0.5f) { // clockwise
                getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
            }
            ((TextView)findViewById(R.id.sensor)).append("X: "+x+ " Y: "+ y+ " Z: "+ z + "\n");

            contador += 1;

            if (contador%40 == 0)
            {
                ((TextView)findViewById(R.id.sensor)).setText("");
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}