package com.example.rivios_.sportapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.Athlete;
import com.example.rivios_.sportapp.data.FootballPlayersStats;
import com.example.rivios_.sportapp.data.FootballStats;

import java.util.ArrayList;

public class FootballAddPlayers extends AppCompatActivity {

    ArrayList<FootballPlayersStats> pst = new ArrayList<FootballPlayersStats>();

    EditText etIme;
    EditText etNadimak;
    EditText etGolovi;
    EditText etAsistencije;
    Spinner spEkipe;
    ListView lvPlayers;

    ArrayAdapter<FootballPlayersStats> plAdapter;
    ArrayAdapter<String> spAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_players);

        Intent i = getIntent();

        etIme = (EditText) findViewById(R.id.playername);
        etNadimak = (EditText) findViewById(R.id.playernickname);
        etGolovi = (EditText) findViewById(R.id.goals);
        etAsistencije = (EditText) findViewById(R.id.assists);
        spEkipe = (Spinner) findViewById(R.id.spinner);
        lvPlayers = (ListView) findViewById(R.id.listaIgraca);

        plAdapter = new ArrayAdapter<FootballPlayersStats>(this, android.R.layout.simple_list_item_1, pst);
        lvPlayers.setAdapter(plAdapter);
        lvPlayers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                pst.remove(i);
                plAdapter.notifyDataSetChanged();
                return true;
            }
        });

        ArrayList<String> teams = new ArrayList<String>();
        teams.add("Odaberite ekipu");
        teams.add(i.getStringExtra(Constants.TEAM1_TAG));
        teams.add(i.getStringExtra(Constants.TEAM2_TAG));
        spAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teams);
        spEkipe.setAdapter(spAdapter);
        spEkipe.setSelection(0);
    }

    public void addFootballPlayer (View v)
    {
        String ime = etIme.getText().toString();
        String nadimak = etNadimak.getText().toString();

        int golovi;
        int asistencije;
        String ekipa;

        try {
            golovi = Integer.parseInt(etGolovi.getText().toString());
            asistencije = Integer.parseInt(etAsistencije.getText().toString());
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Nepravilni podaci.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ime.equals("") || nadimak.equals(""))
        {
            Toast.makeText(this, "Nije upisano ime ili nadimak.", Toast.LENGTH_SHORT).show();
            return;
        }
        for (FootballPlayersStats s : pst)
        {
            if (s.getAthlete().getNickname().equals(nadimak))
            {
                Toast.makeText(this, "Igrač već dodan.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ekipa = spEkipe.getSelectedItem().toString();
        if (ekipa.equals("Odaberite ekipu"))
        {
            Toast.makeText(this, "Ekipa nije odabrana.", Toast.LENGTH_SHORT).show();
            return;
        }

        pst.add(new FootballPlayersStats(new Athlete(0, ime, nadimak, Constants.DISCIPLINE_FOOTBALL), new FootballStats(0, 0, golovi, asistencije, ekipa), 1));
        plAdapter.notifyDataSetChanged();

        etIme.setText("");
        etNadimak.setText("");
        etGolovi.setText("");
        etAsistencije.setText("");
        spEkipe.setSelection(0);
    }

    public void saveFootballPlayer (View v)
    {
        ArrayList<Athlete> igraci = new ArrayList<Athlete>();
        ArrayList<FootballStats> statistike = new ArrayList<FootballStats>();

        for (FootballPlayersStats p : pst)
        {
            igraci.add(p.getAthlete());
            statistike.add(p.getStats());
            Log.d("PERO", "Dodan igrac " + p.getAthlete().getNickname());
        }

        Intent i = new Intent();
        i.putParcelableArrayListExtra(Constants.PLAYERS, igraci);
        i.putParcelableArrayListExtra(Constants.STATS, statistike);
        setResult(RESULT_OK, i);

        finish();

    }
}
