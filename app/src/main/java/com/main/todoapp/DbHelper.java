package com.main.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VER = 1;
    private static final String DB_NAME = "database";

    private static final String DB_TABLE = "tasks";
    private static final String TASK_NAME = "task_name";


    public DbHelper(@Nullable Context context) {
        super( context, DB_NAME, null, DB_VER );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format( "CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL)", DB_TABLE, TASK_NAME );
        db.execSQL( query );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format( "DELETE TABLE IF EXISTS %s", DB_TABLE );
        db.execSQL( query );
        onCreate( db );
    }

    public void insertNewTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(TASK_NAME,task);
        db.insertWithOnConflict(DB_TABLE,null,val, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(String taskToSearch){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete( DB_TABLE,TASK_NAME + "=?", new String[]{taskToSearch});
        db.close();
    }

    public ArrayList<String> getAllTasks(){
        ArrayList<String> my_task_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor search_cursor = db.query( DB_TABLE , new String[]{TASK_NAME}, null, null, null, null,null);   //
        while(search_cursor.moveToNext()){
            int index = search_cursor.getColumnIndex( TASK_NAME );
            my_task_list.add(search_cursor.getString( index )); //ajout dans la liste des données de la bdd
        }
        search_cursor.close();
        db.close();
        return my_task_list;
    }
}
