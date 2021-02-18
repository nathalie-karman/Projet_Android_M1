package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView texte_accueil_1;
    private TextView texte_accueil_2;
    private Button bouton_commencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texte_accueil_1 = (TextView)findViewById(R.id.texte_accueil_1);
        texte_accueil_2 = (TextView) findViewById(R.id.texte_accueil_2);
        bouton_commencer = (Button)findViewById(R.id.bouton_commencer);

        bouton_commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}