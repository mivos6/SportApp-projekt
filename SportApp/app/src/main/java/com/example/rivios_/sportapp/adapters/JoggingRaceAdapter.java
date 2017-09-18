package com.example.rivios_.sportapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rivios_.sportapp.R;
import com.example.rivios_.sportapp.data.JoggingRace;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Milan on 17.9.2017..
 */

public class JoggingRaceAdapter extends BaseAdapter {
    ArrayList<JoggingRace> races;

    public JoggingRaceAdapter(ArrayList<JoggingRace> races) {
        this.races = races;
    }

    @Override
    public int getCount() {
        return races.size();
    }

    @Override
    public Object getItem(int position) {
        return races.get(position);
    }

    @Override
    public long getItemId(int position) {
        return races.get(position).getRaceId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RaceViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.list_element, null);

            holder = new RaceViewHolder();
            holder.tvStart = (TextView) convertView.findViewById(R.id.tvTeam1);
            holder.tvFinish = (TextView) convertView.findViewById(R.id.tvTeam2);
            holder.tvDstance = (TextView) convertView.findViewById(R.id.tvRezultat);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDatum);

            convertView.setTag(holder);
        }
        else {
            holder = (RaceViewHolder) convertView.getTag();
        }

        JoggingRace current = races.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

        holder.tvStart.setText(current.getStart());
        holder.tvFinish.setText(current.getFinish());
        holder.tvDstance.setText(current.getDistance());
        holder.tvDate.setText(sdf.format(current.getDate()));

        return convertView;
    }

    static class RaceViewHolder {
        private TextView tvStart;
        private TextView tvFinish;
        private TextView tvDstance;
        private TextView tvDate;
    }
}
