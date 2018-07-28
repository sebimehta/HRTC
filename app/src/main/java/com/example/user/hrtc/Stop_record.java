package com.example.user.hrtc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Stop_record extends AppCompatActivity {

    Button RouteMap;
    Button RouteRefresh;
    TextView BusDetails, ExptTime;

    public static TextView  info2, info3, info4, info5, info6, info7;
    public  static  Button info1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_record);

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


        info1 = (Button) findViewById(R.id.stop_BusNoID);
        info2 = (TextView) findViewById(R.id.stop_RouteID);
        info3 = (TextView) findViewById(R.id.stop_FromID);
        info4=(TextView) findViewById(R.id.stop_ToID);
        info5=(TextView)findViewById(R.id.stop_BusTypeID);
        info6=(TextView) findViewById(R.id.stop_VehicleNoID);
        info7=(TextView) findViewById(R.id.stop_StatusID);


        RouteMap=(Button) findViewById(R.id.Route_Map);
        //on click map opens
        RouteMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Stop_record.this, MapsActivity.class);
                startActivity(i);
            }
        });
        RouteRefresh=(Button)findViewById(R.id.Route_Refresh);


        // FOR RUNNING BOTTOM STATUS LIST
        BusDetails=(TextView) findViewById(R.id.Route_Details);
        ExptTime=(TextView)findViewById(R.id.Route_ExptTimeDetails);

    }
}
