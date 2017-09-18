package com.example.rivios_.sportapp.activities.football;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.DeleteDialog;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.activities.basketball.BasketballGamePlayers;
import com.example.rivios_.sportapp.adapters.FootballGameStatsAdapter;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.FootballGame;

import java.util.ArrayList;

public class FootballGameList extends AppCompatActivity implements DeleteDialog.DeleteDialogListener, AdapterView.OnItemLongClickListener{
    ArrayList<FootballGame> footballGames;
    FootballGameStatsAdapter adapter;
    ListView lvGameStats;
    GameDBHelper dbHelper;

    private int deletePos = -1;
    private long deleteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arhivanogomet);

        //fillDB();

        lvGameStats = (ListView) findViewById(R.id.lvFootballGameStats);
        dbHelper = GameDBHelper.getInstance(this);

        footballGames = dbHelper.getFootballGames();

        adapter = new FootballGameStatsAdapter(footballGames);

        lvGameStats.setAdapter(adapter);

        lvGameStats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), BasketballGamePlayers.class);
                i.putExtra("GAME_ID", id);
                startActivity(i);
            }
        });

        lvGameStats.setOnItemLongClickListener(this);
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
            if (dbHelper.deleteBasketballGame(deleteId)) {
                footballGames.remove(deletePos);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
