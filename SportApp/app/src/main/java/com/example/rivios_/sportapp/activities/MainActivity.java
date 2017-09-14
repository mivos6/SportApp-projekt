package com.example.rivios_.sportapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rivios_.sportapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void basketballgamestats(View v) {
        Intent i = new Intent();
        i.setClass(this, BasketballGameStatsActivity.class);
        startActivity(i);
    }
    public void footballgamestats(View v) {
        Intent i = new Intent();
        i.setClass(this, FootballGameStatsActivity.class);
        startActivity(i);
    }


    public void joggingracestats(View v) {
        Intent i = new Intent();
        i.setClass(this, MapsActivity.class);
        startActivity(i);
    }

    public void joggingrace(View v) {
        Intent i = new Intent();
        i.setClass(this, JoggingActivity.class);
        startActivity(i);
    }
}
