package com.example.todoapp;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;


import com.example.todoapp.Adapters.AlarmAdapter;
import com.example.todoapp.DatabaseAlarm.DatabaseAlarm;
import com.example.todoapp.Model.EventAlarm;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener{


    String time;

    //boutons et balises xml
    Button my_btn_time,my_btn_date,my_btn_ok;
    ImageView my_btn_record;
    EditText et_message;

    DatabaseAlarm my_database;

    AlarmManager my_alarm;
    private PendingIntent pendingIntent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        et_message = findViewById(R.id.et_message_personal);
        my_btn_record = findViewById(R.id.btn_record);
        my_btn_time = findViewById(R.id.btn_time);
        my_btn_date = findViewById(R.id.btn_date);
        my_btn_ok = findViewById(R.id.btn_done);


        my_btn_record.setOnClickListener(this);
        my_btn_time.setOnClickListener(this);
        my_btn_date.setOnClickListener(this);
        my_btn_ok.setOnClickListener(this);
        my_database = DatabaseAlarm.getDatabase(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == my_btn_date) {
            selectDate();
        }
        else if(view == my_btn_record) {
            recordSpeech();
        }
        else if (view == my_btn_time) {
            selectTime();
        }
        else {
            submit();
        }
    }

    private void submit() {

        String text = et_message.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer un texte", Toast.LENGTH_LONG).show();
        } else {

            if (my_btn_time.getText().toString().equals("Horaire") || my_btn_date.getText().toString().equals("Choisir date de rappel")) {
                Toast.makeText(this, "Veuillez choisir l'horaire et le jour de votre rappel", Toast.LENGTH_LONG).show();
            } else {
                EventAlarm alarmNew = new EventAlarm();
                String value = (et_message.getText().toString().trim());
                String date = (my_btn_date.getText().toString().trim());
                String time = (my_btn_time.getText().toString().trim());
                alarmNew.setDate_alarm(date);
                alarmNew.setMsg_alarm(value);
                alarmNew.setTime_alarm(time);
                my_database.DaoRoom().insertAll(alarmNew);
                setAlarm(value, date, time);
            }
        }


    }

    private void selectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                time = i + ":" + i1;
                my_btn_time.setText(FormatTime(i, i1));
            }
        }, hour, minute, false);
        timePickerDialog.show();

    }

    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                my_btn_date.setText(day + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public String FormatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }


        return time;
    }


    private void recordSpeech() {

        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {

            startActivityForResult(i, 1);
        } catch (Exception e) {
            Toast.makeText(this, "Votre appareil ne peut pas utiliser la reconnaissance vocale", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                et_message.setText(text.get(0));
            }
        }
    }


    private void setAlarm(String text, String date, String time) {
        AlarmManager a = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
        intent.putExtra("event", text);
        intent.putExtra("time", date);
        intent.putExtra("date", time);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String data = date + " " + time;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date d = formatter.parse(data);
            a.set(AlarmManager.RTC_WAKEUP, d.getTime(), pendingIntent);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        finish();

    }

    public void getMeToList(){

    }


}


