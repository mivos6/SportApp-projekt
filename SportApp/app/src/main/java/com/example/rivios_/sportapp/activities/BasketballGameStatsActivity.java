package com.example.rivios_.sportapp.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.DatePickerFragment;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.Athlete;
import com.example.rivios_.sportapp.data.BasketballGame;
import com.example.rivios_.sportapp.data.BasketballStats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BasketballGameStatsActivity extends AppCompatActivity {

    private BasketballGame trenutnaUtakmica = new BasketballGame();
    private ArrayList<Athlete> trenutniIgraci = new ArrayList<Athlete>();
    private ArrayList<BasketballStats> trenutneStatistike = new ArrayList<BasketballStats>();

    EditText etTeam1;
    EditText etTeam2;
    EditText etResult;
    EditText etDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball_game_stats);

        etTeam1 = (EditText) findViewById(R.id.team1);
        etTeam2 = (EditText) findViewById(R.id.team2);
        etResult = (EditText) findViewById(R.id.rezultat);
        etDatum = (EditText) findViewById(R.id.datum);
    }

    public void igracikosarka(View v) {
        Intent i = new Intent();
        i.setClass(this, BasketballAddPlayers.class);

        String team1 = etTeam1.getText().toString();
        String team2 = etTeam2.getText().toString();

        if (!(team1.equals("") || team2.equals(""))) {
            i.putExtra(Constants.TEAM1_TAG, team1);
            i.putExtra(Constants.TEAM2_TAG, team2);
        }
        else {
            Toast.makeText(this, "Nisu upisane ekipe.", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivityForResult(i, Constants.PLAYER_RESULT);
    }

    public void arhivakosarka (View v) {
        Intent i = new Intent();
        i.setClass(this, BasketballGameList.class);
        startActivity(i);
    }

    public void arhivaIgracaKosarka (View v)
    {
        Intent i = new Intent();
        i.setClass(this, BasketballPlayerList.class);
        startActivity(i);
    }

    public void spremiuBazu (View v) {
        String team1 = etTeam1.getText().toString();
        String team2 = etTeam2.getText().toString();
        String result = etResult.getText().toString();
        String datum = etDatum.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (team1.equals("")) {
            Toast.makeText(this, "Nije unešena ekipa.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (team2.equals("")) {
            Toast.makeText(this, "Nije unešena ekipa.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (result.equals("")) {
            Toast.makeText(this, "Nije upisan rezultat.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (datum.equals("")) {
            Toast.makeText(this, "Nije upisan datum.", Toast.LENGTH_SHORT).show();
            return;
        }

        trenutnaUtakmica.setTeam1(team1);
        trenutnaUtakmica.setTeam2(team2);

        int indikator = result.indexOf(":");
        if ((indikator <= 0) || (indikator == result.length() - 1)) {
            Toast.makeText(this, "Neispravno upisan rezultat.", Toast.LENGTH_SHORT).show();
            return;
        }
        int brDvotocke = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == ':')
                brDvotocke++;
            if (brDvotocke > 1) {
                Toast.makeText(this, "Neispravno upisan rezultat.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        int result1;
        int result2;

        try {
            result1 = Integer.parseInt(result.substring(0, indikator));
            result2 = Integer.parseInt(result.substring(indikator + 1));
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Neispravno upisan rezultat.", Toast.LENGTH_SHORT).show();
            return;
        }

        trenutnaUtakmica.setResult1(result1);
        trenutnaUtakmica.setResult2(result2);

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

        dbHelper.addBasketballGame(trenutnaUtakmica);
        long gid = dbHelper.getGameID(trenutnaUtakmica.getTeam1(), trenutnaUtakmica.getTeam2(), trenutnaUtakmica.getDatum());
        trenutnaUtakmica.setId(gid);

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
            for (int i = 0; i < trenutneStatistike.size(); i++) {
                trenutneStatistike.get(i).setGameId(trenutnaUtakmica.getId());
                trenutneStatistike.get(i).setPlayerId(trenutniIgraci.get(i).getId());

                dbHelper.addStats(trenutneStatistike.get(i));

                Log.d("PERO", "Spremljena statistika: utakmica "
                        + trenutneStatistike.get(i).getGameId()
                        + ", igrač " + trenutneStatistike.get(i).getPlayerId());
            }
        }

        trenutnaUtakmica = new BasketballGame();
        trenutniIgraci.clear();
        trenutneStatistike.clear();
        etTeam1.setText("");
        etTeam2.setText("");
        etResult.setText("");
        etDatum.setText("");
        Toast.makeText(this, "Utakmica uspješno spremljena.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.PLAYER_RESULT)
        {
            if (resultCode == RESULT_OK)
            {
                ArrayList<Athlete> listaDodanih = data.getParcelableArrayListExtra(Constants.PLAYERS);
                ArrayList<BasketballStats> listaStatistika = data.getParcelableArrayListExtra(Constants.STATS);

                for ( Athlete igrac : listaDodanih) {
                    trenutniIgraci.add(igrac);
                }
                for ( BasketballStats st : listaStatistika) {
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
