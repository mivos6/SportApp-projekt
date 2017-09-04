package com.example.rivios_.sportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class IgraciKosarka extends AppCompatActivity {
    ArrayList<PlayerStats> pst = new ArrayList<PlayerStats>();

    EditText etIme;
    EditText etNadimak;
    EditText etPoeni;
    EditText etAsistencije;
    EditText etSkokovi;
    ListView lvPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igracikosarka);

        etIme = (EditText) findViewById(R.id.imekosarkasa);
        etNadimak = (EditText) findViewById(R.id.nadimakkosarkasa);
        etPoeni = (EditText) findViewById(R.id.poeni);
        etAsistencije = (EditText) findViewById(R.id.asistencije);
        etSkokovi = (EditText) findViewById(R.id.skokovi);
        lvPlayers = (ListView) findViewById(R.id.listaIgraca);

        ArrayAdapter<PlayerStats> plAdapter = new ArrayAdapter<PlayerStats>(this, android.R.layout.simple_list_item_1, pst);
        lvPlayers.setAdapter(plAdapter);
        lvPlayers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                pst.remove(i);
                return true;
            }
        });
    }

    public void dodajIgraca (View v)
    {
        String ime = etIme.getText().toString();
        String nadimak = etNadimak.getText().toString();

        int poeni = Integer.parseInt(etPoeni.getText().toString());
        int asistencije = Integer.parseInt(etAsistencije.getText().toString());
        int skokovi = Integer.parseInt(etSkokovi.getText().toString());

        if (ime.equals("") || nadimak.equals(""))
        {
            Toast.makeText(this, "Nije upisano ime ili nadimak.", Toast.LENGTH_SHORT).show();
            return;
        }
        for (PlayerStats s : pst)
        {
            if (s.getPlayer().getNickname().equals(nadimak))
            {
                Toast.makeText(this, "Igrč već dodan.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        pst.add(new PlayerStats(new Player(0, ime, nadimak), new Stats(0, 0, poeni, asistencije, skokovi)));

        etIme.setText("");
        etNadimak.setText("");
        etPoeni.setText("");
        etAsistencije.setText("");
        etSkokovi.setText("");
    }

    public void spremi (View v)
    {
        ArrayList<Player> igraci = new ArrayList<Player>();
        ArrayList<Stats> statistike = new ArrayList<Stats>();

        for (PlayerStats p : pst)
        {
            igraci.add(p.getPlayer());
            statistike.add(p.getStats());
        }

        Intent i = new Intent();
        i.putParcelableArrayListExtra(Constants.PLAYERS, igraci);
        i.putParcelableArrayListExtra(Constants.STATS, statistike);
        setResult(RESULT_OK, i);

        finish();
    }
}
