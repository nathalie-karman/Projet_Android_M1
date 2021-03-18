package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Dans "Rappels", cette classe permet d'ttribuer au champ "message", l'input de l'utilisateur
 */
public class MessageActivity extends AppCompatActivity {
    TextView my_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        my_text = findViewById(R.id.tv_message);
        Bundle bundle = getIntent().getExtras();
        my_text.setText(bundle.getString("message"));
    }
}