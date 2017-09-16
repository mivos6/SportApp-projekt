package com.example.rivios_.sportapp.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.DatePickerFragment;
import com.example.rivios_.sportapp.R;

/**
 * Created by rivios_ on 9/14/2017.
 */
public class TennisGameStatsActivity extends AppCompatActivity {


    EditText etplayer1Name;
    EditText etpl1Nickname;
    EditText etpl2Name;
    EditText etpl2Nickname;
    EditText etResult;
    EditText etfirstSet;
    EditText etsecondSet;
    EditText etthirdSet;
    EditText etfourthSet;
    EditText etfifthSet;
    EditText etDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tennis_game);


        etplayer1Name = (EditText) findViewById(R.id.player1Name);
        etpl1Nickname = (EditText) findViewById(R.id.player2Nickname);
        etpl2Name = (EditText) findViewById(R.id.player2Name);
        etpl2Nickname = (EditText) findViewById(R.id.player2Nickname);
        etResult = (EditText) findViewById(R.id.tennisresult);
        etfirstSet = (EditText) findViewById(R.id.firstSet);
        etsecondSet = (EditText) findViewById(R.id.secondSet);
        etthirdSet = (EditText) findViewById(R.id.thirdSet);
        etfourthSet = (EditText) findViewById(R.id.fourthSet);
        etfifthSet = (EditText) findViewById(R.id.fifthSet);
        etDatum = (EditText) findViewById(R.id.datum);
    }


    public void birajDatum (View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), Constants.DATE_PICKER_TAG);
    }

}
