package com.example.rivios_.sportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ArhivaKosarka extends AppCompatActivity {
    ArrayList<Game> games;
    GameStatsAdapter adapter;
    ListView lvGameStats;
    GameDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arhivakosarka);

        //fillDB();

        lvGameStats = (ListView) findViewById(R.id.lvGameStats);
        dbHelper = GameDBHelper.getInstance(this);

        games = dbHelper.getGames();

        adapter = new GameStatsAdapter(games);

        lvGameStats.setAdapter(adapter);

        lvGameStats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), ArhivaIgracaActivity.class);
                i.putExtra("GAME_ID", id);
                startActivity(i);
            }
        });
    }

}
