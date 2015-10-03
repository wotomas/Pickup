package com.example.kim.pickup.storage;

import android.content.Context;
import android.util.Log;
import com.example.kim.pickup.storage.disk.JsonStorable;
import com.example.kim.pickup.unit.User;
import java.util.LinkedList;
import com.google.gson.Gson;
import com.example.kim.pickup.storage.disk.FileManager;

/**
 * Created by kim on 2015-10-03.
 */
public class UserStorage implements JsonStorable {
    private LinkedList<User>_list;
    private int _userTotalCount = 0;

    public UserStorage(){
        _list = new LinkedList<User>();
    }

    public LinkedList<User> getUserList() {
        return _list;
    }
    public User getUser(String id) {
        for(User user: _list) {
            if(user.get_username().equals(id)) {
                return user;
            }
        }

        return null;
    }

    public boolean saveUser(User user, Context context) {
        if(!userExists(user)){
            _list.add(user);
            _userTotalCount++;
            saveToJson(context);
            return true;
        }
        return false;
    }

    private boolean userExists(User user) {
        for(User u : _list){
            if(u.get_username().equals(user.get_username()))
                return true;
        }
        return false;
    }

    @Override
    public String getFileName() {
        return "USER.txt";
    }

    @Override
    public Object loadFromJson(Context context) {
        Log.d("MonsterStorage", "/loadFromJson Loading from Json at " + context.getFilesDir().toString());
        Gson gson = new Gson();
        String json = FileManager.getInstance().loadFromFile(getFileName(), context);
        if (json.equals("")) return null;
        return gson.fromJson(json, UserStorage.class);
    }

    @Override
    public void saveToJson(Context context) {
        Gson gson = new Gson();
        FileManager.getInstance().writeToFile(gson.toJson(this), getFileName(), context);
    }

}
