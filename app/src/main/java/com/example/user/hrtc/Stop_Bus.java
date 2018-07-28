package com.example.user.hrtc;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Stop_Bus extends AppCompatActivity {

    Toolbar mToolbar;
    EditText txtBusStop;
    Button showSTOP;
    TextView TxtBlink;

    public static  TextView   data2, data3, data4;
    public static Button data1;

    private ProgressDialog pDialog;
    private AutoCompleteTextView actv;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop__bus);

        hideSoftKeyboard();              // Hide keyboard
        //blinkTextView();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(" BUS STOP");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Autocomplete text box
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        actv = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        //Create adapter
        actv.setAdapter(new AutoCompleteAdapter(this, actv.getText().toString()));
        adapter.clear();
        adapter.getFilter().filter(actv.getText(),actv);
        adapter.notifyDataSetChanged();
        actv.setThreshold(1);

             // text box
        showSTOP=(Button) findViewById(R.id.Stop_Bus_Btn);       // Bus stop button
        showSTOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pDialog = new ProgressDialog(Stop_Bus.this);
               // pDialog.setMessage("Please wait...");
                //pDialog.setCancelable(true);

                stop_fetch process = new stop_fetch();      // ca;; stop bus fetch class
                process.execute();

                // Custom dialog box
                Dialog dialog= new Dialog(Stop_Bus.this);
                dialog.setContentView(R.layout.stop_custom_dialog);
                dialog.show();

               // pDialog.show();

                data1 = (Button)dialog.findViewById(R.id.Stop_Number);  // button

                data2=(TextView)dialog.findViewById(R.id.Stop_Route);
                data3=(TextView)dialog.findViewById(R.id.Stop_Destination);
                data4=(TextView)dialog.findViewById(R.id.Stop_ExptTime);

                TextView TxtBlink=(TextView)dialog.findViewById(R.id.TxtBlink);
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(200); //You can manage the blinking time with this parameter
                anim.setStartOffset(20);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);
                TxtBlink.startAnimation(anim);

                // to call the information about the particular bus
                data1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(Stop_Bus.this, Stop_record.class);
                        startActivity(intent);

                        stop_record_fetch process = new stop_record_fetch();   // call record fetch class here
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
