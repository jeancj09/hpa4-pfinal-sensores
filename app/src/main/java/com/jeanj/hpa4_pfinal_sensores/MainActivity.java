package com.jeanj.hpa4_pfinal_sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bttGiro;
    Button bttAcel;
    Button bttTemp;
    Button bttPresion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttGiro = findViewById(R.id.bttgiro);
        bttAcel = findViewById(R.id.bttacel);
        bttTemp = findViewById(R.id.btttemp);
        bttPresion = findViewById(R.id.bttpresion);

        bttGiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                CharSequence text = "Cargando el sensor giroscopio, espere... ";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(view.getContext(), PantallaGiroscopio.class);
                startActivity(intent);
            }
        });

        bttAcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                CharSequence text = "Cargando el sensor acelerómetro, espere... ";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(view.getContext(), PantallaAcelerometro.class);
                startActivity(intent);

            }
        });

        bttTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                CharSequence text = "Cargando el sensor de temperatura, espere... ";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(view.getContext(), PantallaTemperatura.class);
                startActivity(intent);

            }
        });

        bttPresion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                CharSequence text = "Cargando el sensor de presión, espere... ";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(view.getContext(), PantallaPresion.class);
                startActivity(intent);

            }
        });
    }
}