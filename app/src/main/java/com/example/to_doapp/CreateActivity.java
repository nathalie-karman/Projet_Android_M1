package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class CreateActivity extends AppCompatActivity {

    private TextView titleText;
    private EditText listName, listDescription;
    private Calendar calendar;
    private Button dateChoice;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        titleText = (TextView) findViewById(R.id.activity_main_title_text);
        listName = (EditText) findViewById(R.id.activity_create_list_name_input);
        listDescription = (EditText) findViewById(R.id.activity_create_list_description_input);
        dateChoice = (Button)findViewById(R.id.activity_create_date_button);

        // Get the current date
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
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
}