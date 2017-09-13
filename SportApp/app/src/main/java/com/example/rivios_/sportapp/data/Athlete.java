package com.example.rivios_.sportapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 3.6.2016..
 */
public class Athlete implements Parcelable{
    private long id;
    private String name;
    private String nickname;
    private String discipline;

    public Athlete() {
        this.id = 0;
        this.name = null;
        this.nickname = null;
    }

    public Athlete(long id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    protected Athlete(Parcel in) {
        id = in.readLong();
        name = in.readString();
        nickname = in.readString();
    }

    public static final Creator<Athlete> CREATOR = new Creator<Athlete>() {
        @Override
        public Athlete createFromParcel(Parcel in) {
            return new Athlete(in);
        }

        @Override
        public Athlete[] newArray(int size) {
            return new Athlete[size];
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

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDiscipline() {
        return discipline;
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