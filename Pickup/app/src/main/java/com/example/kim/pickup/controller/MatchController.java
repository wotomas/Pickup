package com.example.kim.pickup.controller;

import android.content.Context;

import com.example.kim.pickup.storage.MatchStorage;
import com.example.kim.pickup.storage.disk.JsonStorable;
import com.example.kim.pickup.unit.Match;

import java.util.ArrayList;

public class MatchController {
    private static MatchController _instance = null;
    private static MatchStorage _MatchStorage = null;

    public MatchController() {

    }

    public static MatchController getInstance() {
        if(_instance == null) {
            _instance = new MatchController();
        }
        return _instance;
    }

    //Initialize Match Storage
    public boolean initMatchStorage(MatchStorage MatchStorage, Context context) {
        //ex) MatchController.getInstance().initMatchStorage(new MatchStorage, getContext());
        //if Storage is not initialized
        if(_MatchStorage == null) {
            //save a newly created storage
            _MatchStorage = MatchStorage;
            if(_MatchStorage instanceof JsonStorable) {
                //a chekcer to double check if storage is jsonStorable
                //load from text file and store it to storage
                _MatchStorage = (MatchStorage) ((JsonStorable)_MatchStorage).loadFromJson(context);
                if(_MatchStorage == null) {
                    //if the text file has nothing inside
                    //just store the newly created storage
                    _MatchStorage = MatchStorage;
                }
            }
            return true;
        }
        return false;
    }
    /**
     public LinkedList<Match> get_MatchSortedThroughTime(int number) {
     return _MatchStorage.get_MatchSortedThroughTime(number);
     }
     public LinkedList<Match> get_MatchSortedThroughTrend(int number) {
     return _MatchStorage.get_MatchSortedThroughTrend(number);
     }
     **/

    public void addMatch(Match Match, Context context) {
        _MatchStorage.addMatch(Match, context);
    }

    public ArrayList<Match> getList(String CURRENT_USER_SPORTS, Context context) {
        return _MatchStorage.get_MatchList(CURRENT_USER_SPORTS, context);
    }

    public int getTotalSize() {
        return _MatchStorage.get_totalSIze();
    }

    public void saveToParse(Context context) {
        _MatchStorage.saveToParse(context);
    }


}