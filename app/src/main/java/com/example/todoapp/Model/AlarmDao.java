package com.example.todoapp.Model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlarmDao {

    @Insert
    void insertAll(EventAlarm eventAlarm);

    @Query("SELECT * FROM BDD")
    List<EventAlarm> getAllData();

}
