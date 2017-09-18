package com.example.rivios_.sportapp.activities.basketball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.adapters.BasketballPlayerStatsAdapter;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.Athlete;
import com.example.rivios_.sportapp.data.BasketballPlayerStats;
import com.example.rivios_.sportapp.data.BasketballStats;

import java.util.ArrayList;
import java.util.Collections;

public class BasketballPlayerList extends AppCompatActivity{
    ListView lvBasketballPlayers;

    GameDBHelper dbHelper = GameDBHelper.getInstance(this);
    ArrayList<BasketballPlayerStats> basketballPlayerStats = new ArrayList<>();

    BasketballPlayerStatsAdapter adapter = new BasketballPlayerStatsAdapter(basketballPlayerStats);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball_player_list);

        lvBasketballPlayers = (ListView) findViewById(R.id.lvBasketballPlayers);

        ArrayList<Athlete> athletes = dbHelper.getAthletes(Constants.DISCIPLINE_BASKETBALL);

        for (Athlete pl : athletes)
        {
            ArrayList<BasketballStats> stats = dbHelper.getBasketballPlayerStats(pl.getId(), true);

            BasketballPlayerStats ps = new BasketballPlayerStats();

            ps.setAthlete(pl);
            ps.setGameCount(stats.size());

            BasketballStats sum = new BasketballStats(pl.getId(), (long) 0, 0, 0, 0, "");

            for (BasketballStats st : stats)
            {
                sum.setPoints(sum.getPoints() + st.getPoints());
                sum.setAssists(sum.getAssists() + st.getAssists());
                sum.setJumps(sum.getJumps() + st.getJumps());
            }
            ps.setStats(sum);



            basketballPlayerStats.add(ps);
        }

        Collections.sort(basketballPlayerStats);
        lvBasketballPlayers.setAdapter(adapter);
    }

}
