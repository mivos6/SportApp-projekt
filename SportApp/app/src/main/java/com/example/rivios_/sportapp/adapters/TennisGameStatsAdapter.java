package com.example.rivios_.sportapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.TennisGame;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by rivios_ on 9/18/2017.
 */

public class TennisGameStatsAdapter extends BaseAdapter{

    ArrayList<TennisGame> games;

    public TennisGameStatsAdapter(ArrayList<TennisGame> games) {
        this.games = games;
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Object getItem(int position) {
        return games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return games.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GameViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.tennis_list_element, null);

            holder = new GameViewHolder();
            holder.tvTeam1 = (TextView) convertView.findViewById(R.id.tvPlayer1);
            holder.tvTeam2 = (TextView) convertView.findViewById(R.id.tvPlayer2);
            holder.tvRezultat = (TextView) convertView.findViewById(R.id.tvTextView2);
            holder.tvDatum = (TextView) convertView.findViewById(R.id.tvDatum);

            convertView.setTag(holder);
        }
        else {
            holder = (GameViewHolder) convertView.getTag();
        }

        TennisGame current = games.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

        holder.tvTeam1.setText(current.getPlayer1());
        holder.tvTeam2.setText(current.getPlayer2());
        holder.tvRezultat.setText(current.getResult1() + " : " + current.getResult2()+ " : " + current.getSet1());
        holder.tvDatum.setText(sdf.format(current.getDatum()));

        return convertView;
    }

    static class GameViewHolder {
        private TextView tvTeam1;
        private TextView tvTeam2;
        private TextView tvRezultat;
        private TextView tvDatum;
    }
}