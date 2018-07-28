package com.example.user.hrtc;

import android.content.DialogInterface;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Main_Menu_Bar extends AppCompatActivity  {

    TextView SearchBar, LiveStatus, ServiceId, BusStop;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__bar);

        SearchBar = (TextView) findViewById(R.id.txtSearch);
        LiveStatus = (TextView) findViewById(R.id.txtLiveStatus);
        ServiceId = (TextView) findViewById(R.id.txtServiceId);
        BusStop = (TextView) findViewById(R.id.txtBusStop);

        // To open Route search Activity
        SearchBar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent0 = new Intent(Main_Menu_Bar.this, Route.class);// ROUTE SEARCH
                startActivity(intent0);
            }
        });

        // To open BUS STOP
        BusStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Main_Menu_Bar.this, Stop_Bus.class);  // STOP BUS
                startActivity(intent1);
            }
        });

        // To open MAP Activity
        LiveStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent9 = new Intent(Main_Menu_Bar.this, MapsActivity.class);// MAP
                startActivity(intent9);
            }
        });

        // To open Track Service Activity
        ServiceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Main_Menu_Bar.this, Track_Login.class);// LOGIN
                startActivity(intent2);
            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        return true;

                    case R.id.nav_login:
                        Intent intent2 = new Intent(Main_Menu_Bar.this, Login.class);
                        startActivity(intent2);
                        return true;

                    case R.id.nav_person:
                        Intent intent3 = new Intent(Main_Menu_Bar.this, Passenger.class);
                        startActivity(intent3);
                        return true;
                }
                return false;
            }
        });
    }

    // EXIT BUTTON CODE
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

