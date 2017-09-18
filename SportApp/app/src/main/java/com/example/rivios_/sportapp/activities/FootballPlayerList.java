package com.example.rivios_.sportapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.FootballPlayerStatsAdapter;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.BasketballPlayerStatsAdapter;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.Athlete;
import com.example.rivios_.sportapp.data.FootballPlayersStats;
import com.example.rivios_.sportapp.data.FootballStats;

import java.util.ArrayList;

public class FootballPlayerList extends AppCompatActivity {

    ListView lvFootballPlayers;

    GameDBHelper dbHelper = GameDBHelper.getInstance(this);
    ArrayList<FootballPlayersStats> footballPlayersStats = new ArrayList<>();
    FootballPlayerStatsAdapter adapter = new FootballPlayerStatsAdapter(footballPlayersStats);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_player_list);


        lvFootballPlayers = (ListView) findViewById(R.id.lvFootballPlayers);

        ArrayList<Athlete> athletes = dbHelper.getAthletes(Constants.DISCIPLINE_BASKETBALL);

        for (Athlete pl : athletes)
        {
            ArrayList<FootballStats> stats = dbHelper.getFoottballPlayerStats(pl.getId(), true);

            FootballPlayersStats ps = new FootballPlayersStats();

            ps.setAthlete(pl);
            ps.setGameCount(stats.size());

            FootballStats sum = new FootballStats(pl.getId(), (long) 0, 0, 0, 0,  "");

            for (FootballStats st : stats)
            {
                sum.setGoals(sum.getGoals() + st.getGoals());
                sum.setAssists(sum.getAssists() + st.getAssists());
            }
            ps.setStats(sum);

            footballPlayersStats.add(ps);
        }

        lvFootballPlayers.setAdapter(adapter);
    }

}