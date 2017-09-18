package com.example.rivios_.sportapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.BasketballPlayerStatsAdapter;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.BasketballPlayerStats;
import com.example.rivios_.sportapp.data.BasketballStats;

import java.util.ArrayList;

public class BasketballGamePlayers extends AppCompatActivity {
    long gameId;
    ArrayList<BasketballPlayerStats> playerStats = new ArrayList<>();
    BasketballPlayerStatsAdapter adapter;
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

        ArrayList<BasketballStats> stats = dbHelper.getBasketballPlayerStats(gameId, false);

        for (BasketballStats st : stats)
        {
            playerStats.add(new BasketballPlayerStats(dbHelper.getAthlete(st.getPlayerId()), st, 0));
        }

        //basketballPlayerStats = dbHelper.getBasketballPlayerStats(gameId, false);

        adapter = new BasketballPlayerStatsAdapter(playerStats);

        lvplayerStats.setAdapter(adapter);
    }
}
