package com.example.rivios_.sportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class KosarkaIgraciUtakmice extends AppCompatActivity {
    long gameId;
    ArrayList<PlayerStats> playerStats = new ArrayList<>();
    PlayerStatsAdapter adapter;
    ListView lvplayerStats;
    GameDBHelper dbHelper = GameDBHelper.getInstance(this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arhiva_igraca);

        //fillDBPlayers();

        Intent i = getIntent();
        gameId = i.getLongExtra("GAME_ID", 0);

        Log.d("PERO", "Arhiva igraca, utakmica: " + gameId);

        lvplayerStats = (ListView) findViewById(R.id.lvPlayerStats);

        ArrayList<Stats> stats = dbHelper.getPlayerStats(gameId, false);

        for (Stats st : stats)
        {
            playerStats.add(new PlayerStats(dbHelper.getPlayer(st.getPlayerId()), st, 0));
        }

        //playerStats = dbHelper.getPlayerStats(gameId, false);

        adapter = new PlayerStatsAdapter(playerStats);

        lvplayerStats.setAdapter(adapter);
    }
}
