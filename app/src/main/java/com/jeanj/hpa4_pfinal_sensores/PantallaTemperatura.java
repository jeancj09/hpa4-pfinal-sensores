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
import android.widget.Toast;

import java.util.EventListener;

public class PantallaTemperatura extends AppCompatActivity implements SensorEventListener {

    private SensorManager mgr;
    private Sensor temp;
    private TextView tx1;
    private StringBuilder msg = new StringBuilder(2048);
    Button btnregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_temperatura);

        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        temp = mgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        tx1= findViewById(R.id.tx1);


        btnregresar.setOnClickListener(new View.OnClickListener() {
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
    @Override
    protected void onResume() {
        mgr.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mgr.unregisterListener(this, temp);
        super.onPause();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        float fahrenheit = event.values[0] * 9 / 5 + 32;
        msg.insert(0, "Evento de temperatura: " + event.values[0] + " Celsius (" +
                fahrenheit  + " F)\n");
        tx1.setText(msg);
        tx1.invalidate();
    }
}