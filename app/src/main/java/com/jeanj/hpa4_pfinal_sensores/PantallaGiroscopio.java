package com.jeanj.hpa4_pfinal_sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaGiroscopio extends AppCompatActivity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    Button buttongyro;
    TextView sensor1;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_giroscopio);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        buttongyro=findViewById(R.id.btnregresar);
        img=findViewById(R.id.imageView3);
        sensor1=findViewById(R.id.tvsensor);

        buttongyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                CharSequence text = "Volviendo a pantalla principal, espere... ";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
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
                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
            }

            if(sensorEvent.values[0] > 0.5f) { // anticlockwise
                getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);
            } else if(sensorEvent.values[0] < -0.5f) { // clockwise
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            }

            String txt = "\n\nSensor: ";
            // Cada sensor puede lanzar un thread que pase por aqui
            // Para asegurarnos ante los accesos simult‡neos sincronizamos esto

            txt += "Giroscopio\n";
            txt += "\nX: " + x+ " Y: "+y+" Z: "+ z+" \n\n";

            sensor1.setText(txt);

            contador += 1;

            if (contador%40 == 0)
            {
                sensor1.setText("");
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