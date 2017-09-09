package com.example.rivios_.sportapp;

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

import java.util.ArrayList;

public class IgraciKosarka extends AppCompatActivity {
    ArrayList<PlayerStats> pst = new ArrayList<PlayerStats>();

    EditText etIme;
    EditText etNadimak;
    EditText etPoeni;
    EditText etAsistencije;
    EditText etSkokovi;
    Spinner spTimovi;
    ListView lvPlayers;

    ArrayAdapter<PlayerStats> plAdapter;
    ArrayAdapter<String> spAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igracikosarka);
        Intent i = getIntent();

        etIme = (EditText) findViewById(R.id.imekosarkasa);
        etNadimak = (EditText) findViewById(R.id.nadimakkosarkasa);
        etPoeni = (EditText) findViewById(R.id.poeni);
        etAsistencije = (EditText) findViewById(R.id.asistencije);
        etSkokovi = (EditText) findViewById(R.id.skokovi);
        spTimovi = (Spinner) findViewById(R.id.spinner);
        lvPlayers = (ListView) findViewById(R.id.listaIgraca);

        plAdapter = new ArrayAdapter<PlayerStats>(this, android.R.layout.simple_list_item_1, pst);
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
        spTimovi.setAdapter(spAdapter);
        spTimovi.setSelection(0);
    }

    public void dodajIgraca (View v)
    {
        String ime = etIme.getText().toString();
        String nadimak = etNadimak.getText().toString();

        int poeni;
        int asistencije;
        int skokovi;
        String ekipa;

        try {
            poeni = Integer.parseInt(etPoeni.getText().toString());
            asistencije = Integer.parseInt(etAsistencije.getText().toString());
            skokovi = Integer.parseInt(etSkokovi.getText().toString());
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
        for (PlayerStats s : pst)
        {
            if (s.getAthlete().getNickname().equals(nadimak))
            {
                Toast.makeText(this, "Igrač već dodan.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ekipa = spTimovi.getSelectedItem().toString();
        if (ekipa.equals("Odaberite ekipu"))
        {
            Toast.makeText(this, "Ekipa nije odabrana.", Toast.LENGTH_SHORT).show();
            return;
        }

        pst.add(new PlayerStats(new Athlete(0, ime, nadimak), new Stats(0, 0, poeni, asistencije, skokovi, ekipa), 1));
        plAdapter.notifyDataSetChanged();

        etIme.setText("");
        etNadimak.setText("");
        etPoeni.setText("");
        etAsistencije.setText("");
        etSkokovi.setText("");
        spTimovi.setSelection(0);
    }

    public void spremi (View v)
    {
        ArrayList<Athlete> igraci = new ArrayList<Athlete>();
        ArrayList<Stats> statistike = new ArrayList<Stats>();

        for (PlayerStats p : pst)
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
