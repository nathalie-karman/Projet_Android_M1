package com.example.to_doapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener{


    private EditText input;
    private ImageView adding_items;
    private ListView item_list;
    private ArrayList<String> table_values = new ArrayList<String>(); // récupère l'input
    private ArrayAdapter<String> adapter; // insérer une nouvelle valeur dans la liste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        input = findViewById(R.id.inputData);
        adding_items = findViewById(R.id.addButtonView); //ajout du bouton "+"
        item_list = findViewById(R.id.listsV2);

        adding_items.setOnClickListener(this);
        item_list.setOnItemLongClickListener(this); // Giving the reference of the onclick function
    }

    @Override
    public void onClick(View view) {
        String get_input = input.getText().toString();

        if(table_values.contains(get_input)){
            Toast message = Toast.makeText(getBaseContext(),"Item Already Exist", Toast.LENGTH_SHORT);
            message.setGravity( Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0,0 ); //mettre en haut de la page le message
            message.show();
        } // Enter the element if it does not exist
        else{
            table_values.add(get_input);
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,table_values);
            item_list.setAdapter(adapter);
            input.setText("");
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        final int removing_item = position;
        AlertDialog.Builder builder = new AlertDialog.Builder( this); // Ask the user to get the confirmation before deleting an item from the listView
        builder.setMessage("Do you want to delete").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                table_values.remove(removing_item);
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Item Deleted", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancel", null).show();



        return true;
    }
}