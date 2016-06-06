package com.example.rivios_.sportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestats);
    }

    public void igracikosarka(View v) {
        Intent i = new Intent();
        i.setClass(this, IgraciKosarka.class);
        startActivity(i);
    }


    public void arhivakosarka (View v) {
        Intent i = new Intent();
        i.setClass(this, ArhivaKosarka.class);
        startActivity(i);
    }
}
