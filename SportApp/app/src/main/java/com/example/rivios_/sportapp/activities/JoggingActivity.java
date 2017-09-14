package com.example.rivios_.sportapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rivios_.sportapp.R;

/**
 * Created by rivios_ on 9/14/2017.
 */
public class JoggingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogging);
    }

    public void runnersactivity(View v) {
        Intent i = new Intent();
        i.setClass(this, RunnersActivity.class);
        startActivity(i);
    }

    public void addRoute(View v) {
        Intent i = new Intent();
        i.setClass(this, MapsActivity.class);
        startActivity(i);
    }

}
