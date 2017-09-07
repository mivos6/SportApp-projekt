package com.example.rivios_.sportapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class KosrkaIgraci extends AppCompatActivity {
    ListView lvBasketballPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kosrka_igraci);

        lvBasketballPlayers = (ListView) findViewById(R.id.lvBasketballPlayers);
    }
}
