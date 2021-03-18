package com.example.todoapp.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Modèle BDD de l'alarme
 */
@Entity(tableName = "BDD")
public class EventAlarm {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "msg_alarm")
    String msg_alarm;
    @ColumnInfo(name = "time_alarm")
    String time_alarm;
    @ColumnInfo(name = "date_alarm")
    String date_alarm;


    public void setId(int id) {
        this.id = id;
    }

    public void setMsg_alarm(String msg_alarm) {
        this.msg_alarm = msg_alarm;
    }

    public void setTime_alarm(String time_alarm) {
        this.time_alarm = time_alarm;
    }

    public void setDate_alarm(String date_alarm) {
        this.date_alarm = date_alarm;
    }

    public int getId() {
        return id;
    }

    public String getMsg_alarm() {
        return msg_alarm;
    }

    public String getTime_alarm() {
        return time_alarm;
    }

    public String getDate_alarm() {
        return date_alarm;
    }
}

