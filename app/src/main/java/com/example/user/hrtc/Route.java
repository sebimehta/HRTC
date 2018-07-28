package com.example.user.hrtc;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Route extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView swap;
    Button show;


    public static  TextView data1 , data2, data3, data4;

    private ProgressDialog pDialog;

    private AutoCompleteTextView actv1, actv2;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route);
        hideSoftKeyboard();



        // TOOLBAR
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Search Bus");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Autocomplete text box
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        actv1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteOne);
        actv2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTwo);
        //Create adapter
        actv1.setAdapter(new AutoCompleteAdapter(this, actv1.getText().toString()));
        actv2.setAdapter(new AutoCompleteAdapter(this, actv2.getText().toString()));
        adapter.clear();
        adapter.getFilter().filter(actv1.getText(),actv1);
        adapter.getFilter().filter(actv2.getText(),actv2);
        adapter.notifyDataSetChanged();
        actv1.setThreshold(1);
        actv2.setThreshold(1);
        actv1.setMovementMethod(new ScrollingMovementMethod());
        actv2.setMovementMethod(new ScrollingMovementMethod());

        // To swap the string in text box
        swap=(ImageView) findViewById(R.id.swapImg);
        swap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String text1 = actv1.getText().toString();
                String text2 = actv2.getText().toString();
                actv1.setText(text2);
                actv2.setText(text1);
            }
        });

        // SHOW ROUTE BUS
        show=(Button) findViewById(R.id.btnShowRoute);
        show.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {

                //blinkTextView();                 //Dialog Box
                pDialog = new ProgressDialog(Route.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(true);

                route_fetch process = new route_fetch();   // call route fetch class here
                process.execute();

                Dialog dialog= new Dialog(Route.this);
                dialog.setContentView(R.layout.routes_custom_dialog);
                dialog.show();

               // pDialog.show();

                data1 = (TextView)dialog.findViewById(R.id.Stop_Number);
                data2=(TextView)dialog.findViewById(R.id.Route_custom_RouteId);
                data3=(TextView)dialog.findViewById(R.id.Route_Custom_DestinationId);
                data4=(TextView)dialog.findViewById(R.id.Route_Custom_ExpectedTimeId);

                // BLINK  TEXT
                TextView TxtBlink=(TextView)dialog.findViewById(R.id.TxtBlink);
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(200); //You can manage the blinking time with this parameter
                anim.setStartOffset(20);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);
                TxtBlink.startAnimation(anim);

                data1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Route.this, Route_Record.class);
                        startActivity(intent);

                        route_record_fetch process = new route_record_fetch();   // call record fetch class here
                        process.execute();
                    }
                });


            }
        });
    }

    // Hide Keyboard Initially when activity begins//
    public void hideSoftKeyboard()
    {
        if(getCurrentFocus()!=null)
        {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    // To hide keyboard when clicked outside the text box //
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
