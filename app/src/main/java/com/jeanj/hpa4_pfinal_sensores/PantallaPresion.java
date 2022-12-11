package com.jeanj.hpa4_pfinal_sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaPresion extends AppCompatActivity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senPressure;

    Button btnreg;
    TextView presion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_presion);

        btnreg=findViewById(R.id.btnreg);
        presion=findViewById(R.id.presion);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senPressure = senSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        senSensorManager.registerListener(this, senPressure , SensorManager.SENSOR_DELAY_NORMAL);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }


        public void onSensorChanged(SensorEvent event) {
            Sensor mysensor = event.sensor;
            String txt = "\n\nSensor: ";

            // Cada sensor puede lanzar un thread que pase por aqui
            // Para asegurarnos ante los accesos simult‡neos sincronizamos esto

                        txt += "Presion\n";
                        txt += "\n" + event.values[0] + " mBar \n\n";

                        presion.setText(txt);

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
        senSensorManager.registerListener(this, senPressure, SensorManager.SENSOR_DELAY_NORMAL);
    }

        }



