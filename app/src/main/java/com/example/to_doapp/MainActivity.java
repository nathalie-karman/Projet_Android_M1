package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView titleText, welcomeText;
    private Button startButton;
    private RadioGroup modeChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = (TextView)findViewById(R.id.activity_main_title_text);
        welcomeText = (TextView) findViewById(R.id.activity_main_welcome_text);
        startButton = (Button)findViewById(R.id.activity_main_start_button);

        modeChoice = (RadioGroup) findViewById(R.id.activity_main_radio);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Temporary
                Intent createActivity = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(createActivity);
            }
        });
    }
}