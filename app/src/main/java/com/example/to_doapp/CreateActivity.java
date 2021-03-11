package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CreateActivity extends AppCompatActivity {

    private TextView titleText;
    private EditText listName, listDescription;
    private Calendar calendar;
    private Button dateChoice, alarmTime, create;
    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        titleText = (TextView) findViewById(R.id.activity_main_title_text);
        listName = (EditText) findViewById(R.id.activity_create_list_name_input);
        listDescription = (EditText) findViewById(R.id.activity_create_list_description_input);
        dateChoice = (Button)findViewById(R.id.activity_create_date_button);
        alarmTime = (Button)findViewById(R.id.activity_create_alarm_time_input);
        create = (Button)findViewById(R.id.activity_create_create_button);

        // Get the current date
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        showTime(0, 0);

        // Set the time variables (hour, minute) with the time selected for the alarm
        alarmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        hour = h;
                        minute = m;
                        showTime(hour, minute);
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        // Create the list
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listName.getText().length() == 0) {
                    Toast.makeText(CreateActivity.this, "Veuillez entrer un titre pour votre liste.", Toast.LENGTH_LONG).show();
                } else if (listName.getText().length() > 50) {
                    Toast.makeText(CreateActivity.this, "Le titre est trop long (max 50 caractères).", Toast.LENGTH_LONG).show();
                } else if (listDescription.getText().length() > 200) {
                    Toast.makeText(CreateActivity.this, "La description est trop longue (max 200 caractères).", Toast.LENGTH_LONG).show();
                } else {
                    // Add the instructions to stock the list into the database
                }
            }
        });
    }

    // Show the calendar selection
    public void setDate(View view) {
        showDialog(999);
    }

    // Set the calendar selection with the current day
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    // Set the date variables (day, month, year) with the date selected
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker self, int y, int m, int d) {
                    year = y;
                    month = m;
                    day = d;
                    showDate(year, month+1, day);
                }
            };

    // Update the button date choice with the date selected
    private void showDate(int year, int month, int day) {
        dateChoice.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    // Update the button alarm time choice with the time selected
    private void showTime(int hour, int minute) {
        alarmTime.setText(new StringBuilder().append("Rappel : ").append(hour).append(":").append(minute <= 9 ? "0" : "").append(minute));
    }
}