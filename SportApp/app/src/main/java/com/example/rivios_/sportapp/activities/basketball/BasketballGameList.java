package com.example.rivios_.sportapp.activities.basketball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.DeleteDialog;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.adapters.BasketballGameStatsAdapter;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.BasketballGame;

import java.util.ArrayList;

public class BasketballGameList extends AppCompatActivity implements DeleteDialog.DeleteDialogListener, AdapterView.OnItemLongClickListener{
    ArrayList<BasketballGame> basketballGames;
    BasketballGameStatsAdapter adapter;
    ListView lvGameStats;
    GameDBHelper dbHelper;
    EditText editText;
    private int deletePos = -1;
    private long deleteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //fillDB();
        editText = (EditText) findViewById(R.id.txtsearch);
        editText.setBackground(getResources().getDrawable(R.color.basketball));

        lvGameStats = (ListView) findViewById(R.id.lvStats);
        lvGameStats.setBackground(getResources().getDrawable(R.color.basketball));

        dbHelper = GameDBHelper.getInstance(this);

        basketballGames = dbHelper.getBasketballGames();
        adapter = new BasketballGameStatsAdapter(basketballGames);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){

                    // reset listview

                    lvGameStats.setAdapter(adapter);

                }

                else{

                    // perform search

                    searchItem(s.toString());

                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
                basketballGames.remove(deletePos);
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void searchItem(String textToSearch){

        for(String item:){

            if(!item.contains(textToSearch)){

                basketballGames.remove(item);

            }

        }

        adapter.notifyDataSetChanged();

    }
}