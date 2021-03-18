package com.example.todoapp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.todoapp.Model.ToDoModel;
import com.example.todoapp.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui s'occupe de la base d données des tâches/sous tâches etc. Pour l'instant elle ne récupère que le "titre' des tâches, le statut (finie/en cours)
 */
public class DatabaseHandler extends SQLiteOpenHelper {



    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, Constants.NAME, null, Constants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TODO_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(ToDoModel task){
        ContentValues cv = new ContentValues();
       // Log.d(task.getTask(),"valeur de la tâche ici");

        if(task.getTask() != ""){   //si utilisateur a appuyé sur confirmer sans entrer de texte = ne marche pas, faut voir comment gerer mais pas le temps
            cv.put(Constants.TASK, task.getTask());
            cv.put(Constants.STATUS, 0);
            db.insertWithOnConflict(Constants.TODO_TABLE,null,cv, SQLiteDatabase.CONFLICT_REPLACE);   // si doublon
        }

    }

    public List<ToDoModel> getAllTasks(){
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(Constants.TODO_TABLE, null, null, null, null, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        ToDoModel task = new ToDoModel();
                        task.setId(cur.getInt(cur.getColumnIndex(Constants.ID)));
                        task.setTask(cur.getString(cur.getColumnIndex(Constants.TASK)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(Constants.STATUS)));
                        taskList.add(task);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return taskList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(Constants.STATUS, status);
        db.update(Constants.TODO_TABLE, cv, Constants.ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.TASK, task);
        db.update(Constants.TODO_TABLE, cv, Constants.ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteTask(int id){
        db.delete(Constants.TODO_TABLE, Constants.ID + "= ?", new String[] {String.valueOf(id)});
    }
}
