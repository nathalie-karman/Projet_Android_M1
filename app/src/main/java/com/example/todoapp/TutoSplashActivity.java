package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;


public class TutoSplashActivity extends AppCompatActivity {

    public Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide();

    setContentView(R.layout.activity_tuto);
    //skip = findViewById(R.id.btn_skip);



    final Intent i = new Intent(TutoSplashActivity.this, Dashboard.class);
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
    @Override
    public void run() {
        startActivity(i);
        finish();
    }
    }, 5000);

    skip.setOnClickListener(new View.OnClickListener() {     //si ne marche pas, voir ici  https://youtu.be/K7e1t5Oci0E?list=PLzEWSvaHx_Z2MeyGNQeUCEktmnJBp8136&t=1207
    @Override
    public void onClick(View v) {
        Intent i = new Intent(TutoSplashActivity.this, MainActivity.class);
        startActivity(i);
    }
    });
    }
    private void checkFirstOpen(){
    Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isFirstRun", true);

    if (!isFirstRun) {
        Intent intent = new Intent(TutoSplashActivity.this, Dashboard.class);
        startActivity(intent);
        finish();

    }

    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun",
            false).apply();

    }
}