package com.example.rivios_.sportapp;

/**
 * Created by admin on 3.6.2016..
 */
public class Player {
    private long id;
    private String name;
    private String position;

    public Player() {
        this.id = 0;
        this.name = null;
        this.position = null;
    }

    public Player(long id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
