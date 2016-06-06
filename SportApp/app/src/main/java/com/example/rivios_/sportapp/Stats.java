package com.example.rivios_.sportapp;

/**
 * Created by admin on 3.6.2016..
 */
public class Stats {
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
}
