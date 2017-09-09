package com.example.rivios_.sportapp;

/**
 * Created by Milan on 9.9.2017..
 */

public class JoggingRace {
    private long raceId;
    private String name;
    private String start;
    private String finish;
    private int distance;

    public JoggingRace() {
        this.raceId = 0;
        this.name = null;
        this.start = null;
        this.finish = null;
        this.distance = 0;
    }

    public JoggingRace(long raceId, String name, String start, String finish, int distance) {
        this.raceId = raceId;
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.distance = distance;
    }

    public long getRaceId() {
        return raceId;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public int getDistance() {
        return distance;
    }

    public void setRaceId(long raceId) {
        this.raceId = raceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
