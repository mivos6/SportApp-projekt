package com.example.rivios_.sportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ArhivaKosarka extends AppCompatActivity implements DeleteDialog.DeleteDialogListener{
    ArrayList<Game> games;
    GameStatsAdapter adapter;
    ListView lvGameStats;
    GameDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arhivakosarka);

        //fillDB();

        lvGameStats = (ListView) findViewById(R.id.lvGameStats);
        dbHelper = GameDBHelper.getInstance(this);

        games = dbHelper.getGames();

        adapter = new GameStatsAdapter(games);

        lvGameStats.setAdapter(adapter);

        lvGameStats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), KosarkaIgraciUtakmice.class);
                i.putExtra("GAME_ID", id);
                startActivity(i);
            }
        });

        lvGameStats.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                if (dbHelper.deleteGame(id))
                {
                    adapter.notifyDataSetChanged();
                    return true;
                }
                else return false;
            }
        });
    }

    @Override
    public void onDialogClick(boolean yesNo) {

    }
}
