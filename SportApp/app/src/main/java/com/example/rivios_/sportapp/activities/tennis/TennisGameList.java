package com.example.rivios_.sportapp.activities.tennis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.DeleteDialog;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.adapters.FootballGameStatsAdapter;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.adapters.TennisGameStatsAdapter;
import com.example.rivios_.sportapp.data.TennisGame;

import java.util.ArrayList;

public class TennisGameList extends AppCompatActivity implements DeleteDialog.DeleteDialogListener, AdapterView.OnItemLongClickListener{
    ArrayList<TennisGame> tennisGames;
    TennisGameStatsAdapter adapter;
    ListView lvGameStats;
    GameDBHelper dbHelper;

    private int deletePos = -1;
    private long deleteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvGameStats = (ListView) findViewById(R.id.lvStats);
        lvGameStats.setBackground(getResources().getDrawable(R.color.tennis));

        dbHelper = GameDBHelper.getInstance(this);

        tennisGames = dbHelper.getTennisGames();

        adapter = new TennisGameStatsAdapter(tennisGames);

        lvGameStats.setAdapter(adapter);

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
            if (dbHelper.deleteTennisGame(deleteId)) {
                tennisGames.remove(deletePos);
                adapter.notifyDataSetChanged();
            }
        }
    }

}
