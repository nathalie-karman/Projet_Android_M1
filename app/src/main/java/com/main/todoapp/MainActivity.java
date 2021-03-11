package com.main.todoapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.res.Resources.*;

public class MainActivity extends AppCompatActivity {


    DbHelper my_database;
    ArrayAdapter<String> adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        my_database = new DbHelper( this );
        list = (ListView) findViewById( R.id.listViewTask );

        loadTaskList();
        
    }

    private void loadTaskList() {
        ArrayList<String> task_list = my_database.getAllTasks();

        if(adapter == null){
            adapter = new ArrayAdapter<>( this,R.layout.row,R.id.taskContent,task_list);
            list.setAdapter( adapter );

        }else{
            adapter.clear();
            adapter.addAll( task_list );
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu,menu );

        Drawable icon = menu.getItem( 0 ).getIcon();
        icon.mutate();
        icon.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        //icon.setColorFilter(toColor, PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.actionAddTask:
                final EditText textLocal = new EditText( this );
                AlertDialog dialog = new AlertDialog.Builder( this )
                        .setTitle( "Ajouter tâche" )
                        .setMessage( "Et après ?" )
                        .setView( textLocal )
                        .setPositiveButton( "Ajouter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String new_task = String.valueOf( textLocal.getText());
                                my_database.insertNewTask( new_task );
                                loadTaskList();

                            }

                        } )
                        .setNegativeButton( "Annuler", null )
                        .create();
                dialog.show();
                return true;

        }
        return super.onOptionsItemSelected( item );
    }

    public void deleteTask(View v){
        View parent = (View) v.getParent();
        TextView task_to_del = (TextView) findViewById( R.id.taskContent );
        String task = String.valueOf( task_to_del.getText() );
        my_database.deleteTask( task );
        loadTaskList();
    }
}