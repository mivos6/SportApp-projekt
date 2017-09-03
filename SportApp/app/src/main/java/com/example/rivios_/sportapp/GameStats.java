package com.example.rivios_.sportapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GameStats extends AppCompatActivity {

    private Game trenutnaUtakmica;
    private ArrayList<Player> trenutniIgraci;
    private ArrayList<Stats> trenutneStatistike;

    EditText etTeam1;
    EditText etTeam2;
    EditText etResult;
    EditText etDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestats);

        etTeam1 = (EditText) findViewById(R.id.team1);
        etTeam2 = (EditText) findViewById(R.id.team2);
        etResult = (EditText) findViewById(R.id.rezultat);
        etDatum = (EditText) findViewById(R.id.datum);
    }

    public void igracikosarka(View v) {
        Intent i = new Intent();
        i.setClass(this, IgraciKosarka.class);
        startActivityForResult(i, Constants.PLAYER_RESULT);
    }


    public void arhivakosarka (View v) {
        Intent i = new Intent();
        i.setClass(this, ArhivaKosarka.class);
        startActivity(i);
    }

    public void spremiuBazu (View v) {
        String team1 = etTeam1.getText().toString();
        String team2 = etTeam2.getText().toString();
        String rezultat = etResult.getText().toString();
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
        if (rezultat.equals("")) {
            Toast.makeText(this, "Nije upisan rezultat.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (datum.equals("")) {
            Toast.makeText(this, "Nije upisan datum.", Toast.LENGTH_SHORT).show();
            return;
        }

        trenutnaUtakmica.setTeam1(team1);
        trenutnaUtakmica.setTeam2(team2);

        int indikator = rezultat.indexOf(":");
        if ((indikator <= 0) || (indikator == rezultat.length() - 1)) {
            Toast.makeText(this, "Neispravno upisan rezultat.", Toast.LENGTH_SHORT).show();
            return;
        }
        int brDvotocke = 0;
        for (int i = 0; i < rezultat.length(); i++) {
            if (rezultat.charAt(i) == ':')
                brDvotocke++;
            if (brDvotocke > 1) {
                Toast.makeText(this, "Neispravno upisan rezultat.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        trenutnaUtakmica.setResult1(Integer.parseInt(rezultat.substring(0, indikator)));
        trenutnaUtakmica.setResult2(Integer.parseInt(rezultat.substring(indikator + 1)));

        if (trenutnaUtakmica.getResult1() < trenutnaUtakmica.getResult2()) {
            trenutnaUtakmica.setWinner(team2);
        } else if (trenutnaUtakmica.getResult1() > trenutnaUtakmica.getResult2()) {
            trenutnaUtakmica.setWinner(team1);
        } else {
            trenutnaUtakmica.setWinner("");
        }
        try {
            trenutnaUtakmica.setDatum(dateFormat.parse(datum));
        } catch (ParseException e) {
            Toast.makeText(this, "Neispravno upisan datum.", Toast.LENGTH_SHORT).show();
            return;
        }

        GameDBHelper dbHelper = GameDBHelper.getInstance(this);

        dbHelper.addGame(trenutnaUtakmica);
        trenutnaUtakmica.setId(dbHelper.getGameID(trenutnaUtakmica.getTeam1(), trenutnaUtakmica.getTeam2(), trenutnaUtakmica.getDatum()));

        if (trenutniIgraci.size() > 0)
        {
            for (Player igrac : trenutniIgraci)
            {
                if (dbHelper.getPlayerID(igrac.getNickname()) == -1) {
                    dbHelper.addPlayer(igrac);
                }
                igrac.setId(dbHelper.getPlayerID(igrac.getNickname()));
            }
        }

        if (trenutneStatistike.size() > 0) {
            for (int i = 0; i < trenutneStatistike.size(); i++)
            {
                trenutneStatistike.get(i).setGameId(trenutnaUtakmica.getId());
                trenutneStatistike.get(i).setPlayerId(trenutniIgraci.get(i).getId());

                dbHelper.addStats(trenutneStatistike.get(i));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.PLAYER_RESULT)
        {
            if (resultCode == RESULT_OK)
            {
                ArrayList<Player> listaDodanih = data.getParcelableArrayListExtra(Constants.PLAYERS);
                ArrayList<Stats> listaStatistika = data.getParcelableArrayListExtra(Constants.STATS);

                for ( Player igrac : listaDodanih) {
                    trenutniIgraci.add(igrac);
                }
                for ( Stats st : listaStatistika) {
                    trenutneStatistike.add(st);
                }
            }
        }
    }

    public void birajDatum (View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), Constants.DATE_PICKER_TAG);
    }
}
