package com.example.user.hrtc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Route_Record extends AppCompatActivity {

    Toolbar mToolbar;

    public static TextView info2, info3, info4, info5, info6, info7;
    public  static  Button info1;

    Button RouteBus;

    Button RouteMap;
    Button RouteRefresh;
    TextView BusDetails, ExptTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route__record);

        // Toolbar for Bus details
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("HRTC Bus Details");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        info1 = (Button) findViewById(R.id.Route_BusNoID);
        info2 = (TextView) findViewById(R.id.Route_RouteID);
        info3 = (TextView) findViewById(R.id.Route_FromID);
        info4=(TextView) findViewById(R.id.Route_ToID);
        info5=(TextView)findViewById(R.id.Route_BusTypeID);
        info6=(TextView) findViewById(R.id.Route_VehicleNoID);
        info7=(TextView) findViewById(R.id.Route_StatusID);

        RouteRefresh=(Button) findViewById(R.id.Route_Refresh);
        RouteMap=(Button) findViewById(R.id.Route_Map);
        RouteMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Route_Record.this, MapsActivity.class);  // On click map opens
                startActivity(i);
            }
        });

        // FOR RUNNING STATUS LIST
        BusDetails=(TextView) findViewById(R.id.Route_Details);
        ExptTime=(TextView)findViewById(R.id.Route_ExptTimeDetails);
    }
}
