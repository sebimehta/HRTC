package com.example.user.hrtc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class Actv extends AppCompatActivity {

    private AutoCompleteTextView actv;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);

        actv = (AutoCompleteTextView) findViewById(R.id.autoComplete);

        //Create adapter
        actv.setAdapter(new AutoCompleteAdapter(this, actv.getText().toString()));

        adapter.clear();

        adapter.getFilter().filter(actv.getText(),actv);
        adapter.notifyDataSetChanged();
        actv.setThreshold(1);
    }
}