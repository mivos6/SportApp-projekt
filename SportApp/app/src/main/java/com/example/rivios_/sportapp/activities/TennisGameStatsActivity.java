package com.example.rivios_.sportapp.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rivios_.sportapp.Constants;
import com.example.rivios_.sportapp.DatePickerFragment;
import com.example.rivios_.sportapp.GameDBHelper;
import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.TennisGame;
import com.example.rivios_.sportapp.data.Athlete;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by rivios_ on 9/14/2017.
 */
public class TennisGameStatsActivity extends AppCompatActivity {

    private TennisGame currentTennisgame = new TennisGame();
    private ArrayList<Athlete> currentTennisPlayers = new ArrayList<Athlete>();


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

    public void saveTennisGame (View v) {
        String player1 = etplayer1Name.getText().toString();
        String nickname1 = etpl1Nickname.getText().toString();
        String player2 = etpl2Name.getText().toString();
        String nickname2 = etpl2Nickname.getText().toString();
        String result = etResult.getText().toString();
        String datum = etDatum.getText().toString();
        String set1 = etfirstSet.getText().toString();
        String set2 = etsecondSet.getText().toString();
        String set3 = etthirdSet.getText().toString();
        String set4 = etfourthSet.getText().toString();
        String set5 = etfifthSet.getText().toString();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd.MM.yyyy");

        if (player1.equals("")) {
            Toast.makeText(this, "Nije unešeno ime prvog igrača.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nickname1.equals("")) {
            Toast.makeText(this, "Nije unešen nadimak prvog igrača.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (player2.equals("")) {
            Toast.makeText(this, "Nije unešeno ime drugog igrača.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nickname2.equals("")) {
            Toast.makeText(this, "Nije unešen nadimak drugog igrača.", Toast.LENGTH_SHORT).show();
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


        currentTennisgame.setPlayer1(player1);
        currentTennisgame.setPlayer2(player2);

        if (((result.indexOf(":") <= 0) || (result.indexOf(":") == result.length() - 1)) ||
                (!set1.equals("")&&((set1.indexOf(":") <= 0) || (set1.indexOf(":") == set1.length() - 1))) ||
                (!set2.equals("")&&((set2.indexOf(":") <= 0) || (set2.indexOf(":") == set2.length() - 1))) ||
                (!set3.equals("")&&((set3.indexOf(":") <= 0) || (set3.indexOf(":") == set3.length() - 1))) ||
                (!set4.equals("")&&((set4.indexOf(":") <= 0) || (set4.indexOf(":") == set4.length() - 1))) ||
                (!set5.equals("")&&((set5.indexOf(":") <= 0) || (set5.indexOf(":") == set5.length() - 1)))) {
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

        int indikator = result.indexOf(":");;
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

        currentTennisgame.setResult1(result1);
        currentTennisgame.setResult2(result2);

        if (currentTennisgame.getResult1() < currentTennisgame.getResult2()) {
            currentTennisgame.setWinner(player2);
        } else if (currentTennisgame.getResult1() > currentTennisgame.getResult2()) {
            currentTennisgame.setWinner(player1);
        } else {
            currentTennisgame.setWinner("");
        }
        try {
            currentTennisgame.setDatum(dateFormat.parse(datum));
        } catch (ParseException e) {
            Toast.makeText(this, "Neispravno upisan datum.", Toast.LENGTH_SHORT).show();
            return;
        }

        GameDBHelper dbHelper = GameDBHelper.getInstance(this);

        dbHelper.addTennisGame(currentTennisgame);
        long gid = dbHelper.getBasketballGameID(currentTennisgame.getPlayer1(), currentTennisgame.getPlayer2(), currentTennisgame.getDatum());
        currentTennisgame.setId(gid);

        Log.d("PERO", "Spremljena utakmica: " + gid);

        if (currentTennisPlayers.size() > 0)
        {
            for (Athlete player : currentTennisPlayers)
            {
                if (dbHelper.getAthleteID(player.getNickname()) == -1) {
                    dbHelper.addAthlete(player);
                }
                long pid = dbHelper.getAthleteID(player.getNickname());
                player.setId(pid);
                Log.d("PERO", "Spremljen igrač: " + pid);
            }
        }



    }
    public void birajDatum (View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), Constants.DATE_PICKER_TAG);
    }

}
