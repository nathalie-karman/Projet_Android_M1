package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;



import android.content.DialogInterface;

import android.view.MenuItem;
import android.view.View;

import com.example.todoapp.Fragments.AddTaskFragment;
import com.example.todoapp.Model.DialogCloseListener;
import com.example.todoapp.View.RecyclerItemTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.todoapp.Adapters.ToDoAdapter;
import com.example.todoapp.Model.ToDoModel;
import com.example.todoapp.Utils.DatabaseHandler;
import com.google.android.material.navigation.NavigationView;


import java.util.Collections;
import java.util.List;

/**
 * Accès à la page principale
 */
public class MainActivity extends AppCompatActivity  implements DialogCloseListener, NavigationView.OnNavigationItemSelectedListener {

    private DatabaseHandler db;
    DrawerLayout drawerLayout;
    private RecyclerView my_rv;
    private ToDoAdapter my_adapter;
    private FloatingActionButton my_fab;
    private FloatingActionButton my_fab2;
    NavigationView navigationView;
    private List<ToDoModel> my_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHandler(this);
        db.openDatabase();
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigationView);
        my_rv = findViewById(R.id.rv_task_name);
        my_rv.setLayoutManager(new LinearLayoutManager(this));
        my_adapter = new ToDoAdapter(db,MainActivity.this);
        my_rv.setAdapter(my_adapter);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new
                ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(my_adapter));

        itemTouchHelper.attachToRecyclerView(my_rv);

        my_fab = findViewById(R.id.btn_fab);
        my_fab2 = findViewById(R.id.btn_create_alarm);
        my_list = db.getAllTasks();
        Collections.reverse(my_list);

        my_adapter.setTasks(my_list);

        my_fab.setOnClickListener( new View.OnClickListener() {     //si ne marche pas, voir ici  https://youtu.be/K7e1t5Oci0E?list=PLzEWSvaHx_Z2MeyGNQeUCEktmnJBp8136&t=1207
            @Override
            public void onClick(View v) {
                AddTaskFragment.newInstance().show(getSupportFragmentManager(), AddTaskFragment.TAG);
            }
        } );
        ItemTouchHelper item = new ItemTouchHelper(callback);
        item.attachToRecyclerView(my_rv);

        my_fab2.setOnClickListener( new View.OnClickListener() {     //si ne marche pas, voir ici  https://youtu.be/K7e1t5Oci0E?list=PLzEWSvaHx_Z2MeyGNQeUCEktmnJBp8136&t=1207
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(i);
            }
        } );


    }
    /**
     * pour le drag& drop, on a besoin que de la méthode, la méthode onMove,
     * la méthode onSwiped est implémentée dans un ItemTouchHelper
     * @see:Utils.RecyclerItemTouchHelper
     *
     */
    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN |
            ItemTouchHelper.START | ItemTouchHelper.END, 0) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(my_list, fromPosition, toPosition);    //échange des positions entre les listes
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_slideshow:
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
                break;

            case R.id.help:
                Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent2);
                break;

            case R.id.about:
                Intent intent3 = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent3);
        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        my_list = db.getAllTasks();
        Collections.reverse(my_list);
        my_adapter.setTasks(my_list);
        my_adapter.notifyDataSetChanged();
    }
}