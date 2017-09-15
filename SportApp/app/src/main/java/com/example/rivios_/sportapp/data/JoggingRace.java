package com.example.rivios_.sportapp.data;

/**
 * Created by Milan on 9.9.2017..
 */

public class JoggingRace {
    private long raceId;
    private String start;
    private String finish;
    private int distance;
    private String encodedRoute;

    public JoggingRace() {
        this.raceId = 0;
        this.start = null;
        this.finish = null;
        this.distance = 0;
        this.encodedRoute = null;
    }

    public JoggingRace(long raceId, String start, String finish, int distance) {
        this.raceId = raceId;
        this.start = start;
        this.finish = finish;
        this.distance = distance;
        this.encodedRoute = null;
    }

    public long getRaceId() {
        return raceId;
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

    public void setStart(String start) {
        this.start = start;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getEncodedRoute() {
        return encodedRoute;
    }

    public void setEncodedRoute(String encodedRoute) {
        this.encodedRoute = encodedRoute;
    }

}
