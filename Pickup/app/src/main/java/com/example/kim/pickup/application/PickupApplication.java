package com.example.kim.pickup.application;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by kim on 2015-10-08.
 */
public class PickupApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "1Hsvzr8iLElh5rxnSK67gxqIMsTdcerxilwTH6BP", "XKniSkI7duYm3Sv3kjCc8fAeldsXsMW85ELX9qUu");

        /*
        * ParseObject testObject = new ParseObject("TestObject");
        * testObject.put("foo", "bar");
        * testObject.saveInBackground();
        */
    }
}
