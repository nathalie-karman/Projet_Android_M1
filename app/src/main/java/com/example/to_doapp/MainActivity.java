package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


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
                //  Intent createActivity = new Intent(MainActivity.this, CreateActivity.class);
                //  startActivity(createActivity);
                Intent createActivity = new Intent(MainActivity.this, ShowLists.class);
                startActivity(createActivity);
            }
        });
    }

    /** Creation d'un "Toast" pour afficher temporairement les informations
     * a l'ecran
     */
    public void popUp(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     * La fonction onStop() est executee :
     * - lorsque l'activite n'est plus en premier plan
     * - ou bien lorsque l'activite va etre detruite
     *
     * Cette fonction est suivie :
     * - de la fonction onRestart() si l'activite passe à nouveau en premier plan;
     * - de la fonction onDestroy() lorsque l'activite se termine ou bien lorsque le systeme decide de l'arreter
     */
    @Override
    protected void onStop() {
        super.onStop();

        popUp("onStop()");

    }

    /**
     * Cette fonction est executee lorsque l'activite se termine ou bien lorsque
     * le systeme decide de l'arreter.
     *
     * La fonction onCreate() devra a nouveau etre executee pour obtenir à nouveau l'activite.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        popUp("onDestroy()");
    }
}