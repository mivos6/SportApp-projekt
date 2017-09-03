package com.example.rivios_.sportapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 3.6.2016..
 */
public class Player implements Parcelable{
    private long id;
    private String name;
    private String nickname;

    public Player() {
        this.id = 0;
        this.name = null;
        this.nickname = null;
    }

    public Player(long id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    protected Player(Parcel in) {
        id = in.readLong();
        name = in.readString();
        nickname = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String position) {
        this.nickname = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(nickname);
    }
}
