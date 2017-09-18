package com.example.rivios_.sportapp.activities.jogging;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.Athlete;
import com.example.rivios_.sportapp.data.JoggingRunnerStats;
import com.example.rivios_.sportapp.data.JoggingStats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by rivios_ on 9/14/2017.
 */
public class JoggingRunners extends AppCompatActivity {
    EditText etRunnerName;
    EditText etRunnerNickname;
    EditText etRunnerTime;
    ListView lvAddRunners;
    ArrayAdapter<JoggingRunnerStats> adapter;

    ArrayList<JoggingRunnerStats> rs = new ArrayList<JoggingRunnerStats>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runners);

        etRunnerName = (EditText) findViewById(R.id.runnerName);
        etRunnerNickname = (EditText) findViewById(R.id.runnerNickname);
        etRunnerTime = (EditText) findViewById(R.id.runnerTime);
        lvAddRunners = (ListView) findViewById(R.id.addRunnersList);

        adapter = new ArrayAdapter<JoggingRunnerStats>(this, android.R.layout.simple_list_item_1, rs);
        lvAddRunners.setAdapter(adapter);
        lvAddRunners.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                rs.remove(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void addRunner(View v)
    {
        String name = etRunnerName.getText().toString();
        String nickname = etRunnerNickname.getText().toString();

        Date time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        try
        {
            time = dateFormat.parse(etRunnerTime.getText().toString());
        }
        catch (ParseException e)
        {
            Toast.makeText(this, "Nepravilno upisano vreme.", Toast.LENGTH_SHORT).show();
            return;
        }

        long secs = time.getTime();

        rs.add(new JoggingRunnerStats(new Athlete(0, name, nickname, Constants.DISCIPLINE_JOGGING), new JoggingStats(0, 0, secs, 0)));
        Collections.sort(rs);
        for (int i = 0; i < rs.size(); i++)
        {
            rs.get(i).getStats().setPlace(i + 1);
        }
        adapter.notifyDataSetChanged();

        etRunnerName.setText("");
        etRunnerNickname.setText("");
        etRunnerTime.setText("");
    }

    public void saveRunners(View v)
    {
        Intent resultIntent = new Intent();
        ArrayList<Athlete> addedRunners = new ArrayList<Athlete>();
        ArrayList<JoggingStats> addedStats = new ArrayList<JoggingStats>();

        for (JoggingRunnerStats added : rs)
        {
            addedRunners.add(added.getRunner());
            addedStats.add(added.getStats());
        }

        resultIntent.putExtra(Constants.ATHLETE_TAG, addedRunners);
        resultIntent.putExtra(Constants.STATS_TAG, addedStats);

        setResult(RESULT_OK);
        finish();
    }
}
