package com.example.kim.pickup.unit;

import android.util.Log;

import com.parse.ParseGeoPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by nylee on 13/12/15.
 */
public class Match implements Serializable, Comparable<Match> {
    private String _matchName;
    private Calendar _startTime;
    private ParseGeoPoint location;
    private String locationName;
    private String sportKey;
    private int totalCapacity;
    private int popularity;
    private String ownerID;
    private List<String> users;

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
        this.location = null;
        this.sportKey = "";
        this.totalCapacity = 0;
        this.popularity = 0;
        this.ownerID = "";
        this.locationName="";
        this.users = new ArrayList<String>();
    }

    public Match(String _matchName, Calendar _startTime, ParseGeoPoint location, String sportName, int totalCapacity, int popularity, String ownerID, String locationName) {
        this._matchName = _matchName;
        this._startTime = _startTime;
        this.location = location;
        this.sportKey = sportName;
        this.totalCapacity = totalCapacity;
        this.popularity = popularity;

        this.ownerID = ownerID;
        this.locationName = locationName;
        this.users = new ArrayList<String>();
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


    public ParseGeoPoint get_location() {
        return location;
    }

    public void set_location(ParseGeoPoint location) {
        this.location = location;
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

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<Object> users) {
        this.users.clear();
        for(Object obj: users) {
            this.users.add((String) obj);
            Log.d("UserListTest", "User is added is: " + (String) obj);
        }
        Log.d("UserListTest", "User List is: " + this.users.toString());
    }

    public void addUsers(String user) {
        this.users.add(user);
    }

    public int getUsersCount() {
        return this.users.size();
    }

    public boolean checkIfUserIsAlreadyJoined(String username) {
        for(String user: this.users) {
            if(user.equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Match another) {
        return another.get_startTime().compareTo(this.get_startTime());
    }
}
