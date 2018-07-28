package com.example.user.hrtc;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    TextInputEditText txtEmail, txtPassword;
    Button btnLogin;
    Toolbar mToolbar;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtEmail = (TextInputEditText) findViewById(R.id.idEmail);
        txtPassword = (TextInputEditText) findViewById(R.id.idPassword);

        txtEmail.getBackground().mutate().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
        txtPassword.getBackground().mutate().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login();
                hideSoftKeyboard();
            }
        });

// ADMIN TOOLBAR
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("HRTC LOGIN");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void login()           // not coded for login i.e for dept use only
    {
        if (TextUtils.isEmpty(txtEmail.getText().toString().trim()) ||
                TextUtils.isEmpty(txtPassword.toString().trim())) {
            txtEmail.setError("Email Field cannot be empty ");
            txtPassword.setError("Password Field cannot be empty ");
        } else if (!emailValidator(txtEmail.getText().toString())) {
            txtEmail.setError("Please Enter Valid Email Address ");
        } else {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
        }
    }

    // Email Validator
    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
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
