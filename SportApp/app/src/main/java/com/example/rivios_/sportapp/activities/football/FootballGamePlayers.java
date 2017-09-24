package com.example.rivios_.sportapp.activities.football;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rivios_.sportapp.adapters.FootballPlayerStatsAdapter;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.FootballPlayersStats;
import com.example.rivios_.sportapp.data.FootballStats;

import java.util.ArrayList;

public class FootballGamePlayers extends AppCompatActivity {
    long gameId;
    ArrayList<FootballPlayersStats> playerStats = new ArrayList<>();
    FootballPlayerStatsAdapter adapter;
    ListView lvplayerStats;
    EditText editText;
    GameDBHelper dbHelper = GameDBHelper.getInstance(this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //fillDBPlayers();

        Intent i = getIntent();
        gameId = i.getLongExtra("GAME_ID", 0);

        Log.d("PERO", "Arhiva igraca, utakmica: " + gameId);

        editText = (EditText) findViewById(R.id.txtsearch);
        editText.setBackground(getResources().getDrawable(R.color.football));

        lvplayerStats = (ListView) findViewById(R.id.lvStats);
        lvplayerStats.setBackground(getResources().getDrawable(R.color.football));

        ArrayList<FootballStats> stats = dbHelper.getFoottballPlayerStats(gameId, false);

        for (FootballStats st : stats)
        {
            playerStats.add(new FootballPlayersStats(dbHelper.getAthlete(st.getPlayerId()), st, 0));
        }

        //basketballPlayerStats = dbHelper.getBasketballPlayerStats(gameId, false);

        adapter = new FootballPlayerStatsAdapter(playerStats);

        lvplayerStats.setAdapter(adapter);
    }
}
