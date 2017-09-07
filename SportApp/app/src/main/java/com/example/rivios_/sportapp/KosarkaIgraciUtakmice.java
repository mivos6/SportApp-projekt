package com.example.rivios_.sportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class KosarkaIgraciUtakmice extends AppCompatActivity {
    long gameId;
    ArrayList<Stats> playerStats;
    PlayerStatsAdapter adapter;
    ListView lvplayerStats;
    GameDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arhiva_igraca);

        dbHelper = GameDBHelper.getInstance(this);

        //fillDBPlayers();

        Intent i = getIntent();
        gameId = i.getLongExtra("GAME_ID", 0);

        Log.d("PERO", "Arhiva igraca, utakmica: " + gameId);

        lvplayerStats = (ListView) findViewById(R.id.lvPlayerStats);

        playerStats = dbHelper.getPlayerStats(gameId);

        adapter = new PlayerStatsAdapter(playerStats);

        lvplayerStats.setAdapter(adapter);
    }
}
