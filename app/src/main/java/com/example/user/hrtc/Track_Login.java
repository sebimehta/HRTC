package com.example.user.hrtc;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Track_Login extends AppCompatActivity {

    TextInputEditText textService;
    Button btnTrackBus;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.track__login);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_service);
        mToolbar.setTitle("HRTC BUS SERVICE ID");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textService = (TextInputEditText) findViewById(R.id.idService);
        btnTrackBus = (Button) findViewById(R.id.btn_TrackBus);


        // To check whether service no is valid //
        // The map code will be added here
        btnTrackBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                hideSoftKeyboardTrack();   // Hide keyboard function called//
                if (isValidId(textService.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Service Id is valid", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Service Id is not valid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // To check validation for service number //
    private boolean isValidId(String id)
    {
        boolean check;
        if(!Pattern.matches("[a-zA-Z]+", id))
        {
            if(id.length()==6) {
                check =true;
            } else{
                check =false;
            }
        } else{
            check=true;
        }return check;
    }

    public void hideSoftKeyboardTrack()
    {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
