package com.example.rivios_.sportapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.Athlete;
import com.example.rivios_.sportapp.data.JoggingRace;
import com.example.rivios_.sportapp.data.JoggingStats;

import java.util.ArrayList;

/**
 * Created by rivios_ on 9/14/2017.
 */
public class JoggingRaceStats extends AppCompatActivity {
    private JoggingRace newRace = null;
    private ArrayList<Athlete> runners;
    private ArrayList<JoggingStats> stats;

    EditText etSart;
    EditText etFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogging);

        etSart = (EditText) findViewById(R.id.start);
        etFinish = (EditText) findViewById(R.id.finish);
    }

    public void runnersActivity(View v) {
        if (newRace == null)
        {
            Toast.makeText(this, "Prvo odredite rutu.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent();
        i.setClass(this, JoggingRunners.class);
        startActivityForResult(i, Constants.PLAYER_RESULT);
    }

    public void addRoute(View v) {
        Intent i = new Intent();
        i.setClass(this, MapsActivity.class);
        startActivityForResult(i, Constants.ROUTE_RESULT);
    }

    public void saveToDB(View v) {
        if (newRace == null)
        {
            Toast.makeText(this, "Nije postavljena utrka", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.ROUTE_RESULT)
        {
            if (resultCode == RESULT_OK)
            {
                Log.d("PERO", "RESULT_OK");
                newRace = new JoggingRace();

                newRace.setStart(data.getStringExtra(Constants.START_TAG));
                newRace.setFinish(data.getStringExtra(Constants.FINISH_TAG));
                newRace.setDistance(data.getIntExtra(Constants.DIST_TAG, 0));
                newRace.setEncodedRoute(data.getStringExtra(Constants.ROUTE_TAG));

                etSart.setText(newRace.getStart());
                etFinish.setText(newRace.getFinish());
            }
        }
        else if (requestCode == Constants.PLAYER_RESULT)
        {

        }
    }
}
