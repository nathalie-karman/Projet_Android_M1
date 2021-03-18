package com.example.todoapp.Utils;

/**
 * Classe qui recense les principales constantes de toute l'application
 */
public abstract class Constants {
    public static final String DATABASE_ALARM_NAME = "BDD_ALARM";
    public static final int VERSION_DATABASE = 1;
    public static final String HOUR = "time_hour";
    public static final String MINUTE = "time_minute";
    public static final String TIME_PICKER = "time_picker";


    public static final String CHANNEL_ID = "666";
    public static final String CHANNEL_ID_ONE = "notify_001";
    public static final String CHANNEL_NAME = "notification channel";

    //constantes pour la base de données, (à diviser en catégorie plus tard)
    public static final int VERSION = 1;
    public static final String NAME = "toDoListDatabase";
    public static final String TODO_TABLE = "todo";
    public static final String ID = "id";
    public static final String TASK = "task";
    public static final String STATUS = "status";
    public static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK + " TEXT UNIQUE, "
            + STATUS + " INTEGER)";
}
