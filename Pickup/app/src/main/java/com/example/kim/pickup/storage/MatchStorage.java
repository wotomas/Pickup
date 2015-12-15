package com.example.kim.pickup.storage;

import android.content.Context;
import android.util.Log;

import com.example.kim.pickup.activity.MainActivity;
import com.example.kim.pickup.fragment.TimeFragment;
import com.example.kim.pickup.storage.disk.FileManager;
import com.example.kim.pickup.storage.disk.JsonStorable;
import com.example.kim.pickup.unit.Match;
import com.google.gson.Gson;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MatchStorage implements JsonStorable {

    private ArrayList<Match> _MatchList;
    private ArrayList<Match> _MatchListFromServer;

    private int _MatchNumber = 0;

    public MatchStorage() {
        _MatchList = new ArrayList<Match>();
        _MatchListFromServer = new ArrayList<Match>();
    }

    public void localMatchListReset(Context context) {
        _MatchListFromServer.clear();
        saveToJson(context);
    }

    public ArrayList<Match> get_MatchList(String sport, final Context context) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Match");
        query.whereEqualTo("sport", MainActivity.CURRENT_USER_SPORTS);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e == null) {
                    Log.d("ParseTest", "Retrieved Objects count : " + parseObjects.size() + " Current ArrayList Size: " + _MatchListFromServer.size());
                    //object is received. object is match
                    _MatchListFromServer.clear();

                    for(ParseObject parseObject: parseObjects) {
                        Match newMatch = new Match();
                        newMatch.set_location(parseObject.getDouble("location"));
                        newMatch.setTotalCapacity(parseObject.getInt("capacity"));
                        newMatch.setPopularity(parseObject.getInt("popularity"));
                        newMatch.set_matchName(parseObject.getString("matchName"));
                        newMatch.setLocationName(parseObject.getString("locationName"));
                        //String Date = parseObject.getString("startTime");
                        //Fri Dec 18 20:37:44 GMT+08:00 2015
                        String input = parseObject.getString("startTime");
                        String dateFormat  = "EEE MMM d HH:mm:ss z yyyy";
                        Date date = new Date();
                        try {
                             date = new SimpleDateFormat(dateFormat).parse(input);
                        } catch (java.text.ParseException e1) {
                            e1.printStackTrace();
                        }
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        newMatch.set_startTime(cal);
                        newMatch.setOwnerID(parseObject.getString("ownerID"));
                        newMatch.setSportKey(parseObject.getString("sport"));
                        _MatchListFromServer.add(newMatch);
                        TimeFragment.mAdapter.notifyDataSetChanged();
                    }
                    saveToJson(context);

                } else {
                    // something went wrong
                    Log.d("ParseTest", "Error : " + e.getMessage());
                }
            }
        });

        return _MatchListFromServer;
    }

    public int get_totalSIze() {
        return _MatchNumber;
    }

    public void addMatch(Match match, Context context) {
        _MatchList.add(match);
        _MatchNumber++;
        saveToJson(context);
    }

    public void saveToParse(final Context context) {
        ParseObject newMatch;
        for(final Match match: _MatchList) {
            newMatch = new ParseObject("Match");

            newMatch.put("ownerID", match.getOwnerID());
            newMatch.put("matchName", match.get_matchName());
            newMatch.put("location", match.get_location());
            newMatch.put("startTime", match.get_startTime().getTime().toString());
            newMatch.put("popularity", match.getPopularity());
            newMatch.put("sport", match.getSportKey());
            newMatch.put("capacity", match.getTotalCapacity());
            newMatch.put("locationName", match.getLocationName());

            newMatch.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null) {
                        //object was saved successfully, remove from local storage
                        _MatchList.remove(match);
                        saveToJson(context);
                    } else {
                        //object was not saved successfully.
                    }
                }
            });
        }


    }

    @Override
    public String getFileName() {
        return "MATCHJSON.txt";
    }

    @Override
    public Object loadFromJson(Context context) {
        //create a new Gson class to load file from the saved text file.
        //if the textfile is correctly loaded, deserialize the Json read into MatchStorage class
        Gson gson = new Gson();
        String json = FileManager.getInstance().loadFromFile(getFileName(), context);
        if(json.equals("")) {
            return null;
        }

        return gson.fromJson(json, MatchStorage.class);

    }

    @Override
    public void saveToJson(Context context) {
        Gson gson = new Gson();
        FileManager.getInstance().writeToFile(gson.toJson(this), getFileName(), context);
    }

}