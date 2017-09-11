package com.example.rivios_.sportapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FootballGameStatsActivity extends AppCompatActivity {

    private FootballGame currentFootbalGame = new FootballGame();
    private ArrayList<Athlete> trenutniIgraci = new ArrayList<Athlete>();
    private ArrayList<Stats> trenutneStatistike = new ArrayList<Stats>();

    EditText etTeam1;
    EditText etTeam2;
    EditText etResult;
    EditText etDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_game_stats);

        etTeam1 = (EditText) findViewById(R.id.footballteam1);
        etTeam2 = (EditText) findViewById(R.id.footballteam2);
        etResult = (EditText) findViewById(R.id.footballresult);
        etDatum = (EditText) findViewById(R.id.datum);
    }

    public void footballplayers(View v) {
        Intent i = new Intent();
        i.setClass(this, FootballPlayers.class);

        String footballteam1 = etTeam1.getText().toString();
        String footballteam2 = etTeam2.getText().toString();

        if (!(footballteam1.equals("") || footballteam2.equals(""))) {
            i.putExtra(Constants.TEAM1_TAG, footballteam1);
            i.putExtra(Constants.TEAM2_TAG, footballteam2);
        }
        else {
            Toast.makeText(this, "Nisu upisane ekipe.", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivityForResult(i, Constants.PLAYER_RESULT);
    }

    public void spremiuBazu (View v) {
        String team1 = etTeam1.getText().toString();
        String team2 = etTeam2.getText().toString();
        String footballresult = etResult.getText().toString();
        String datum = etDatum.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (team1.equals("")) {
            Toast.makeText(this, "Nije upisan tim.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (team2.equals("")) {
            Toast.makeText(this, "Nije upisan tim.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (footballresult.equals("")) {
            Toast.makeText(this, "Nije upisan rezultat.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (datum.equals("")) {
            Toast.makeText(this, "Nije upisan datum.", Toast.LENGTH_SHORT).show();
            return;
        }

        currentFootbalGame.setTeam1(team1);
        currentFootbalGame.setTeam2(team2);

        int indikator = footballresult.indexOf(":");
        if ((indikator <= 0) || (indikator == footballresult.length() - 1)) {
            Toast.makeText(this, "Neispravno upisan rezultat.", Toast.LENGTH_SHORT).show();
            return;
        }
        int brDvotocke = 0;
        for (int i = 0; i < footballresult.length(); i++) {
            if (footballresult.charAt(i) == ':')
                brDvotocke++;
            if (brDvotocke > 1) {
                Toast.makeText(this, "Neispravno upisan rezultat.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        int result1;
        int result2;

        try {
            result1 = Integer.parseInt(footballresult.substring(0, indikator));
            result2 = Integer.parseInt(footballresult.substring(indikator + 1));
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Neispravno upisan rezultat.", Toast.LENGTH_SHORT).show();
            return;
        }

        GameDBHelper dbHelper = GameDBHelper.getInstance(this);

        FootballGame currentFootballGame;
        dbHelper.addFootballGame(currentFootballGame);
        long gid = dbHelper.getGameID(currentFootballGame.getTeam1(), currentFootballGame.getTeam2(), currentFootballGame.getDatum());
        currentFootballGame.setId(gid);

        Log.d("PERO", "Spremljena utakmica: " + gid);

        if (trenutniIgraci.size() > 0)
        {
            for (Athlete igrac : trenutniIgraci)
            {
                if (dbHelper.getPlayerID(igrac.getNickname()) == -1) {
                    dbHelper.addAthlete(igrac);
                }
                long pid = dbHelper.getPlayerID(igrac.getNickname());
                igrac.setId(pid);
                Log.d("PERO", "Spremljen igrač: " + pid);
            }
        }

        if (trenutneStatistike.size() > 0) {
            for (int i = 0; i < trenutneStatistike.size(); i++)
            {
                trenutneStatistike.get(i).setGameId(trenutnaUtakmica.getId());
                trenutneStatistike.get(i).setPlayerId(trenutniIgraci.get(i).getId());

                dbHelper.addStats(trenutneStatistike.get(i));

                Log.d("PERO", "Spremljena statistika: utakmica "
                        + trenutneStatistike.get(i).getGameId()
                        + ", igrač " + trenutneStatistike.get(i).getPlayerId());
            }
        }
    }



    public void birajDatum (View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), Constants.DATE_PICKER_TAG);
    }
}
