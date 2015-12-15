package com.example.kim.pickup.storage.disk;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileManager {
    private static FileManager instance = new FileManager();
    private final String _TAG = "FileManager";

    public FileManager() {

    }

    public static FileManager getInstance() {
        return instance;
    }

    public void deleteMatchJson(Context context) {
        File dir = context.getFilesDir();
        File file = new File(dir, "MATCHJSON.txt");
        if(file.delete()){
            Log.d(_TAG, "Internal File MATCHJSON.txt was deleted");
        }
    }


    public void writeToFile(String inputString, String fileName, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(inputString.getBytes());
            fos.close();
            Log.d(_TAG, "MATCHJSON file was saved correctly");
        } catch (Exception e) {
            Log.e(_TAG, "Error while writing to MATCHJSON file", e);
        }
    }

    public String loadFromFile(String fileName, Context context) {
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();
            inputStream.close();
            Log.d(_TAG, "Successfully loading from MATCHJSON file");
            return total.toString();
        } catch (Exception e) {
            Log.e(_TAG, "Error while loading from MATCHJSON file", e);
        }
        return "";


    }
}