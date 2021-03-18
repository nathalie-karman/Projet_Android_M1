package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;

import com.example.todoapp.Adapters.AlarmAdapter;
import com.example.todoapp.DatabaseAlarm.DatabaseAlarm;
import com.example.todoapp.Model.EventAlarm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.todoapp.Adapters.ToDoAdapter;
import com.example.todoapp.Model.ToDoModel;
import com.example.todoapp.Utils.DatabaseHandler;


import java.util.List;
import java.util.Objects;

/**
 * Activité : rappels
 */
public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button btn_create_alarm;
    AlarmAdapter my_adapter_alarm;
    RecyclerView my_rv_alarm;
    DatabaseAlarm my_database_alarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_create_alarm = findViewById(R.id.btn_create_alarm);
        my_rv_alarm = findViewById(R.id.rv_main_alarm);
        btn_create_alarm.setOnClickListener(this);
        my_database_alarm = DatabaseAlarm.getDatabase(getApplicationContext());

    }



    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();

    }

    private void setAdapter() {
        List<EventAlarm> classList = my_database_alarm.DaoRoom().getAllData();
        my_adapter_alarm = new AlarmAdapter(getApplicationContext(), classList);
        my_rv_alarm.setAdapter(my_adapter_alarm);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_create_alarm) {
            createAlarmActivity();
        }
    }

    private void createAlarmActivity() {
        Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
        startActivity(intent);
    }




}