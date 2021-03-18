package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.todoapp.Fragments.AddTaskFragment;
import com.google.android.material.navigation.NavigationView;


public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public ImageButton imageView0;
    public ImageButton imageView1;
    public ImageButton imageView2;
    public ImageButton imageView3;
    public ImageButton imageView4;
    public ImageButton imageView5;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;

    public Menu menu;
    public TextView textView;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        imageView0 = findViewById(R.id.bic);
        imageView1 = findViewById(R.id.work);
        imageView2 = findViewById(R.id.trip);
        imageView3 = findViewById(R.id.etude);
        imageView4 = findViewById(R.id.clean);
        imageView5 = findViewById(R.id.shop);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        textView = findViewById(R.id.textView);
        navigationView.bringToFront();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        imageView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, MainActivity.class);
                startActivity(i);
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, MainActivity.class);
                startActivity(i);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, MainActivity.class);
                startActivity(i);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, MainActivity.class);
                startActivity(i);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, MainActivity.class);
                startActivity(i);
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent i = new Intent(Dashboard.this, Dashboard.class);
                startActivity(i);
            case R.id.nav_slideshow:
                Intent intent = new Intent(Dashboard.this, AlarmActivity.class);
                startActivity(intent);
                break;

            case R.id.help:
                 Intent intent2 = new Intent(Dashboard.this, MainActivity.class);
                 startActivity(intent2);
                 break;

            case R.id.about:
                Intent intent3 = new Intent(Dashboard.this, MainActivity3.class);
                startActivity(intent3);
               /* break;
            case R.id.nav_share: Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); break;*/
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;}


}