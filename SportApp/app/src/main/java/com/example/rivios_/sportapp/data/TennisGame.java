package com.example.rivios_.sportapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by rivios_ on 9/12/2017.
 */

public class TennisGame implements Parcelable {


    private long id;
    private String player1;
    private String player2;
    private int result1;
    private int result2;
    private Date datum;
    private String winner;
    private int set1;
    private int set2;
    private int set3;
    private int set4;
    private int set5;

    public TennisGame() {
        this.id = 0;
        this.player1 = null;
        this.player2 = null;
        this.result1 = 0;
        this.result2 = 0;
        this.datum = null;
        this.winner = null;
        this.set1 = 0;
        this.set2 = 0;
        this.set3 = 0;
        this.set4 = 0;
        this.set5 = 0;
    }

    public TennisGame(long id, String player1, String player2, int result1, int result2, Date datum, String winner, int set1, int set2, int set3, int set4, int set5) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.result1 = result1;
        this.result2 = result2;
        this.datum = datum;
        this.winner = winner;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.set5 = set5;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getResult1() {
        return result1;
    }

    public void setResult1(int result1) {
        this.result1 = result1;
    }

    public int getResult2() {
        return result2;
    }

    public void setResult2(int result2) {
        this.result2 = result2;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getSet1() {
        return set1;
    }

    public void setSet1(int set1) {
        this.set1 = set1;
    }

    public int getSet2() {
        return set2;
    }

    public void setSet2(int set2) {
        this.set2 = set2;
    }

    public int getSet3() {
        return set3;
    }

    public void setSet3(int set3) {
        this.set3 = set3;
    }

    public int getSet4() {
        return set4;
    }

    public void setSet4(int set4) {
        this.set4 = set4;
    }

    public int getSet5() {
        return set5;
    }

    public void setSet5(int set5) {
        this.set5 = set5;
    }

    public static Creator<TennisGame> getCREATOR() {
        return CREATOR;
    }

    protected TennisGame(Parcel in) {
    }

    public static final Creator<TennisGame> CREATOR = new Creator<TennisGame>() {
        @Override
        public TennisGame createFromParcel(Parcel in) {
            return new TennisGame(in);
        }

        @Override
        public TennisGame[] newArray(int size) {
            return new TennisGame[size];
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