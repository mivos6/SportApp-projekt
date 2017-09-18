package com.example.rivios_.sportapp.activities.football;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.adapters.FootballPlayerStatsAdapter;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.Athlete;
import com.example.rivios_.sportapp.data.FootballPlayersStats;
import com.example.rivios_.sportapp.data.FootballStats;

import java.util.ArrayList;
import java.util.Collections;

public class FootballPlayerList extends AppCompatActivity {
    ListView lvFootballPlayers;

    GameDBHelper dbHelper = GameDBHelper.getInstance(this);
    ArrayList<FootballPlayersStats> footballPlayersStats = new ArrayList<>();
    FootballPlayerStatsAdapter adapter = new FootballPlayerStatsAdapter(footballPlayersStats);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        lvFootballPlayers = (ListView) findViewById(R.id.lvStats);
        lvFootballPlayers.setBackground(getResources().getDrawable(R.color.football));

        ArrayList<Athlete> athletes = dbHelper.getAthletes(Constants.DISCIPLINE_FOOTBALL);

        for (Athlete pl : athletes)
        {
            ArrayList<FootballStats> stats = dbHelper.getFoottballPlayerStats(pl.getId(), true);

            FootballPlayersStats ps = new FootballPlayersStats();

            ps.setAthlete(pl);
            ps.setGameCount(stats.size());

            FootballStats sum = new FootballStats(pl.getId(), 0, 0, 0, "");

            for (FootballStats st : stats)
            {
                sum.setGoals(sum.getGoals() + st.getGoals());
                sum.setAssists(sum.getAssists() + st.getAssists());
            }
            ps.setStats(sum);

            footballPlayersStats.add(ps);
        }
        Collections.sort(footballPlayersStats);
        lvFootballPlayers.setAdapter(adapter);
    }

}