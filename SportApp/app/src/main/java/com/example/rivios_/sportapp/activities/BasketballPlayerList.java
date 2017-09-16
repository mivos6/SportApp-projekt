package com.example.rivios_.sportapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.DeleteDialog;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.PlayerStatsAdapter;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.Athlete;
import com.example.rivios_.sportapp.data.BasketballPlayerStats;
import com.example.rivios_.sportapp.data.BasketballStats;

import java.util.ArrayList;

public class BasketballPlayerList extends AppCompatActivity implements DeleteDialog.DeleteDialogListener, AdapterView.OnItemLongClickListener{
    ListView lvBasketballPlayers;

    GameDBHelper dbHelper = GameDBHelper.getInstance(this);
    ArrayList<BasketballPlayerStats> basketballPlayerStats = new ArrayList<>();
    PlayerStatsAdapter adapter = new PlayerStatsAdapter(basketballPlayerStats);

    private int deletePos = -1;
    private long deleteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kosrka_igraci);

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

        lvBasketballPlayers.setOnItemLongClickListener(this);
        lvBasketballPlayers.setAdapter(adapter);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
        DeleteDialog delDialog =  new DeleteDialog();
        delDialog.setListener(this);
        deletePos = pos;
        deleteId = id;
        delDialog.show(getFragmentManager(), Constants.DELETE_DIALOG_TAG);
        return true;
    }

    @Override
    public void onDialogClick(boolean yes) {
        if (yes) {
            if (dbHelper.deletePlayer(deleteId)) {
                basketballPlayerStats.remove(deletePos);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
