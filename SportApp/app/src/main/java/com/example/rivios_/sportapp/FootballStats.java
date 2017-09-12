package com.example.rivios_.sportapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rivios_ on 9/10/2017.
 */

public class FootballStats implements Parcelable{

    private long playerId;
    private long gameId;
    private int goals;
    private int assists;
    private String team;

    public FootballStats() {
        this.playerId = 0;
        this.gameId = 0;
        this.goals = 0;
        this.assists = 0;
        this.team = null;
    }

    public FootballStats(long playerId, long gameId, int goals, int assists, int jumps, String team) {
        this.playerId = playerId;
        this.gameId = gameId;
        this.goals = goals;
        this.assists = assists;
        this.team = team;
    }

    protected FootballStats(Parcel in) {
        playerId = in.readLong();
        gameId = in.readLong();
        goals = in.readInt();
        assists = in.readInt();
        team = in.readString();
    }


    public static final Creator<FootballStats> CREATOR = new Creator<FootballStats>() {
        @Override
        public FootballStats createFromParcel(Parcel in) {
            return new FootballStats(in);
        }

        @Override
        public FootballStats[] newArray(int size) {
            return new FootballStats[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
