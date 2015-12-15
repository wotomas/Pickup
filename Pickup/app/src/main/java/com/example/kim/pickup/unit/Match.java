package com.example.kim.pickup.unit;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nylee on 13/12/15.
 */
public class Match implements Serializable{
    private String _matchName;
    private Calendar _startTime;
    private double _distance;
    private int sportKey;
    private int totalCapacity;
    private int popularity;
    private int ownerID;
    //private int[] userIdList;

    public Match(String _matchName, Calendar _startTime, double _distance, int sportKey, int totalCapacity, int popularity, int ownerID) {
        this._matchName = _matchName;
        this._startTime = _startTime;
        this._distance = _distance;
        this.sportKey = sportKey;
        this.totalCapacity = totalCapacity;
        this.popularity = popularity;
        this.ownerID = ownerID;
        //this.userIdList = userIdList;
    }

    public String get_matchName() {
        return _matchName;
    }

    public void set_matchName(String _matchName) {
        this._matchName = _matchName;
    }

    public Calendar get_startTime() {
        return _startTime;
    }

    public void set_startTime(Calendar _startTime) {
        this._startTime = _startTime;
    }


    public double get_distance() {
        return _distance;
    }

    public void set_distance(double _distance) {
        this._distance = _distance;
    }

    public int getSportKey() {
        return sportKey;
    }

    public void setSportKey(int sportKey) {
        this.sportKey = sportKey;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
/*
    public int[] getUserIdList() {
        return userIdList;
    }

    public void insertUserIdList(int userID, int index) {
        this.userIdList[index] = userID;
    }*/
}
