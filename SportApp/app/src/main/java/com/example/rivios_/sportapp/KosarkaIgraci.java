package com.example.rivios_.sportapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class KosarkaIgraci extends AppCompatActivity implements DeleteDialog.DeleteDialogListener, AdapterView.OnItemLongClickListener{
    ListView lvBasketballPlayers;

    GameDBHelper dbHelper = GameDBHelper.getInstance(this);
    ArrayList<PlayerStats> playerStats = new ArrayList<>();
    PlayerStatsAdapter adapter = new PlayerStatsAdapter(playerStats);

    private int deletePos = -1;
    private long deleteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kosrka_igraci);

        lvBasketballPlayers = (ListView) findViewById(R.id.lvBasketballPlayers);

        ArrayList<Player> players = dbHelper.getPlayers();

        for (Player pl : players)
        {
            ArrayList<Stats> stats = dbHelper.getPlayerStats(pl.getId(), true);

            PlayerStats ps = new PlayerStats();

            ps.setPlayer(pl);
            ps.setGameCount(stats.size());

            Stats sum = new Stats(pl.getId(), (long) 0, 0, 0, 0, "");

            for (Stats st : stats)
            {
                sum.setPoints(sum.getPoints() + st.getPoints());
                sum.setAssists(sum.getAssists() + st.getAssists());
                sum.setJumps(sum.getJumps() + st.getJumps());
            }
            ps.setStats(sum);

            playerStats.add(ps);
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
                playerStats.remove(deletePos);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
