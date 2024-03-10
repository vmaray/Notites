package com.victormp.notites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    // Definición de elementos de la interfaz de usuario
    protected TextView txt_splash;
    // Definición del intent de cambio de actividad
    private Intent splashToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Inicialización de los elementos de la interfaz de usuario
        txt_splash = (TextView) findViewById(R.id.txtApp_splash);

        // Avanza a la pantalla principal automáticamente después de 2 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Crea un intent para ir a la actividad principal y finaliza la actividad actual
                splashToMain = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splashToMain);
                finish();
            }
        }, 2000); // 2000 milisegundos (2 segundos)
    }
}