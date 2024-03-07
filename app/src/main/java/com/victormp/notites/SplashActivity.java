package com.victormp.notites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    protected TextView txt_splash;

    private Intent splashToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashToMain = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splashToMain);
                finish();
            }
        }, 2000);

        txt_splash = (TextView) findViewById(R.id.txtApp_splash);

    }
}