package com.example.kim.pickup.unit;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by nylee on 13/12/15.
 */
public class Match implements Serializable{
    private String _matchName;
    private Calendar _startTime;
    private double location;
    private String locationName;
    private String sportKey;
    private int totalCapacity;
    private int popularity;
    private String ownerID;

    //private int[] userIdList;
    /*
    1. Need player list.
    popularity = total count of this player list.

    2. photo?! how to deal with it?
    MatchRoomActivity needs: total count of photos for each match.
    Two types of photo needs to be saved:
        a. background photos for sports types
        b. match photos for individual matches.
     */

    public Match() {
        this._matchName = "";
        this._startTime = Calendar.getInstance();
        this.location = 0.0;
        this.sportKey = "";
        this.totalCapacity = 0;
        this.popularity = 0;
        this.ownerID = "";
        this.locationName="";
    }

    public Match(String _matchName, Calendar _startTime, double _distance, String sportName, int totalCapacity, int popularity, String ownerID, String locationName) {
        this._matchName = _matchName;
        this._startTime = _startTime;
        this.location = _distance;
        this.sportKey = sportName;
        this.totalCapacity = totalCapacity;
        this.popularity = popularity;

        this.ownerID = ownerID;
        this.locationName = locationName;
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


    public double get_location() {
        return location;
    }

    public void set_location(double _distance) {
        this.location = _distance;
    }

    public String getSportKey() {
        return sportKey;
    }

    public void setSportKey(String sportKey) {
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

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
