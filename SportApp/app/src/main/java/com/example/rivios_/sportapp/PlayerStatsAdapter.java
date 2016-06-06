package com.example.rivios_.sportapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 6.6.2016..
 */
public class PlayerStatsAdapter extends BaseAdapter {
    ArrayList<Stats> playerStats;

    public PlayerStatsAdapter(ArrayList<Stats> playerStats) {
        this.playerStats = playerStats;
    }

    @Override
    public int getCount() {
        return playerStats.size();
    }

    @Override
    public Object getItem(int position) {
        return playerStats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return playerStats.get(position).getPlayerId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StatsViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.list_player_element, null);

            holder = new StatsViewHolder();
            holder.tvNamePos = (TextView) convertView.findViewById(R.id.tvNamePos);
            holder.tvStats = (TextView) convertView.findViewById(R.id.tvStats);

            convertView.setTag(holder);
        } else {
            holder = (StatsViewHolder) convertView.getTag();
        }

        Stats current = playerStats.get(position);

        GameDBHelper helper = GameDBHelper.getInstance(convertView.getContext());
        Player p = helper.getPlayer(current.getPlayerId());

        holder.tvNamePos.setText(p.getName() + ", " + p.getPosition());
        holder.tvStats.setText("PTS: " + current.getPoints() +
                "AST: " + current.getAssists() +
                "RBD: " + current.getJumps());

        return convertView;
    }

    static class StatsViewHolder {
        private TextView tvNamePos;
        private TextView tvStats;
    }
}
