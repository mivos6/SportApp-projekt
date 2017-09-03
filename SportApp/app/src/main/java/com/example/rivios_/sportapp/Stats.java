package com.example.rivios_.sportapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 3.6.2016..
 */
public class Stats implements Parcelable {
    private long playerId;
    private long gameId;
    private int points;
    private int assists;
    private int jumps;

    public Stats() {
        this.playerId = 0;
        this.gameId = 0;
        this.points = 0;
        this.assists = 0;
        this.jumps = 0;
    }

    public Stats(long playerId, long gameId, int points, int assists, int jumps) {
        this.playerId = playerId;
        this.gameId = gameId;
        this.points = points;
        this.assists = assists;
        this.jumps = jumps;
    }

    protected Stats(Parcel in) {
        playerId = in.readLong();
        gameId = in.readLong();
        points = in.readInt();
        assists = in.readInt();
        jumps = in.readInt();
    }

    public static final Creator<Stats> CREATOR = new Creator<Stats>() {
        @Override
        public Stats createFromParcel(Parcel in) {
            return new Stats(in);
        }

        @Override
        public Stats[] newArray(int size) {
            return new Stats[size];
        }
    };

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getJumps() {
        return jumps;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(playerId);
        parcel.writeLong(gameId);
        parcel.writeInt(points);
        parcel.writeInt(assists);
        parcel.writeInt(jumps);
    }
}
