package com.example.user.hrtc;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class Passenger extends AppCompatActivity {

    TextInputEditText txtSource, txtDestination;
    Button Fare;


    Toolbar mToolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger);

        txtSource = (TextInputEditText) findViewById(R.id.FareSource);
        txtDestination = (TextInputEditText) findViewById(R.id.FareDestination);

        txtSource.getBackground().mutate().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
        txtDestination.getBackground().mutate().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);

        Fare = (Button) findViewById(R.id.FareBtn);
        Fare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hideSoftKeyboard();
            }
        });

        // ADMIN TOOLBAR
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("BUS FARE");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // Hide Keyboard Initially when activity begins//

    public void hideSoftKeyboard()
    {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    // To hide keyboard when clicked outside the text box //

    public void hideKeyboard(View view)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
