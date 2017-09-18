package com.example.rivios_.sportapp.data;

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

    public FootballStats(int i, int i1, int golovi, int asistencije, String ekipa) {
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

    public long getGameId() {
        return gameId;
    }
    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getPlayerId() {
        return playerId;
    }
    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getGoals() {
        return goals;
    }
    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }


    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }


}
