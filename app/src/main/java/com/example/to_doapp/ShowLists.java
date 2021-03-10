package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowLists extends AppCompatActivity {

    private ArrayList <String> postIts;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lists);

        listView = findViewById(R.id.viewLists);
        btn = findViewById(R.id.btnAddList);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addItem(v);
            }
        });

        postIts = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postIts);
        listView.setAdapter(itemsAdapter);
        setUpListViewListener();
    }

    private void setUpListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context contextForToast = getApplicationContext();
                Toast.makeText(contextForToast, "Post-it supprimé", Toast.LENGTH_LONG).show();

                postIts.remove(position);
                itemsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addItem(View view){
        EditText input =  findViewById(R.id.editTextTextPersonName2);
        String itemText = input.getText().toString();

        if(!(itemText.equals(""))){
            itemsAdapter.add(itemText);
            input.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Il faut un nom de liste", Toast.LENGTH_LONG).show();
        }

    }
}