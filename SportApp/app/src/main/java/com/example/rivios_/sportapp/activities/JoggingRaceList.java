package com.example.rivios_.sportapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.JoggingRace;

import java.util.ArrayList;

public class JoggingRaceList extends AppCompatActivity {
    private ArrayList<JoggingRace> races = new ArrayList<JoggingRace>();

    ListView lvRaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogging_race_list);

        lvRaces = (ListView) findViewById(R.id.lvRaces);
    }
}
