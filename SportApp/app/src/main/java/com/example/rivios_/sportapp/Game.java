package com.example.rivios_.sportapp;

import java.util.Date;

/**
 * Created by admin on 26.5.2016..
 */
public class Game {
    private long id;
    private String team1;
    private String team2;
    private int result1;
    private int result2;
    private Date datum;
    private String winner;

    public Game() {
        this.id = 0;
        this.team1 = null;
        this.team2 = null;
        this.result1 = 0;
        this.result2 = 0;
        this.datum = null;
        this.winner = null;
    }

    public Game(long id, String team1, String team2, int result1, int result2, Date datum, String winner) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.result1 = result1;
        this.result2 = result2;
        this.datum = datum;
        this.winner = winner;
    }

    public long getId() {
        return id;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getResult1() {
        return result1;
    }

    public int getResult2() {
        return result2;
    }

    public Date getDatum() {
        return datum;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setResult1(int result1) {
        this.result1 = result1;
    }

    public void setResult2(int result2) {
        this.result2 = result2;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
