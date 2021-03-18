package com.example.todoapp.DatabaseAlarm;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todoapp.Model.AlarmDao;
import com.example.todoapp.Model.EventAlarm;

import androidx.room.Database;

import static com.example.todoapp.Utils.Constants.DATABASE_ALARM_NAME;
import static com.example.todoapp.Utils.Constants.VERSION_DATABASE;

//mettre exportSchema à false si problème lors de la compilation (warnings chez moi), mais normalement il faut le laisser à true

@Database(entities = {EventAlarm.class}, version = VERSION_DATABASE, exportSchema = true)
public abstract class DatabaseAlarm extends RoomDatabase {
    private static DatabaseAlarm INSTANCE;

    public abstract AlarmDao DaoRoom();

    public static DatabaseAlarm getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseAlarm.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseAlarm.class,
                                    DATABASE_ALARM_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
