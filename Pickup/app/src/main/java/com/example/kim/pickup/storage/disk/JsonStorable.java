package com.example.kim.pickup.storage.disk;

import android.content.Context;

/**
 * Created by kim on 2015-10-03.
 */
public interface JsonStorable {
    public String getFileName();
    public Object loadFromJson(Context context);
    public void saveToJson(Context context);
}