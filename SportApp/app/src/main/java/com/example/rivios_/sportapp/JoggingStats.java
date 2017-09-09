package com.example.rivios_.sportapp;

/**
 * Created by Milan on 9.9.2017..
 */

public class JoggingStats {
    private long raceId;
    private long runnerId;
    private int time;
    private int place;

    public JoggingStats() {
        this.raceId = 0;
        this.runnerId = 0;
        this.time = 0;
        this.place = 0;
    }

    public JoggingStats(long raceId, long runnerId, int time, int place) {
        this.raceId = raceId;
        this.runnerId = runnerId;
        this.time = time;
        this.place = place;
    }

    public long getRaceId() {
        return raceId;
    }

    public void setRaceId(long raceId) {
        this.raceId = raceId;
    }

    public long getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(long runnerId) {
        this.runnerId = runnerId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
