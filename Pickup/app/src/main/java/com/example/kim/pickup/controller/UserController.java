package com.example.kim.pickup.controller;

import android.content.Context;

import com.example.kim.pickup.storage.UserStorage;
import com.example.kim.pickup.storage.disk.JsonStorable;
import com.example.kim.pickup.unit.User;

/**
 * Created by kim on 2015-10-03.
 */
public class UserController {
    private static UserController instance = null;
    private static UserStorage _userStorage = null;

    public UserController() {

    }

    public static UserController getInstance(){
        if(instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public boolean initializeUserController(UserStorage userStorage, Context context) {
        if(_userStorage == null) {
            _userStorage = userStorage;
            if(_userStorage instanceof JsonStorable) {
                _userStorage = (UserStorage) ((JsonStorable)_userStorage).loadFromJson(context);
                if(_userStorage == null) {
                    _userStorage = userStorage;
                }
            }
            return true;
        }
        return false;
    }

    public User getUser(String name) {
        return _userStorage.getUser(name);
    }

    public Boolean saveUser(User user, Context context) {
        return _userStorage.saveUser(user, context);
    }


}
