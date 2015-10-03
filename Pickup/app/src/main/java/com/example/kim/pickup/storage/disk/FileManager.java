package com.example.kim.pickup.storage.disk;

/**
 * Created by kim on 2015-10-03.
 */

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager {
    private String Tag = "FileManager";
    private static FileManager instance = new FileManager();

    public FileManager(){

    }

    public static FileManager getInstance(){
        return instance;
    }

    public void deleteUserFile(Context context) {
        File dir = context.getFilesDir();
        File file = new File(dir, "USER.txt");
        if(file.delete()){
            Log.d(Tag, "/deleteAllFile: Successfully deleted USER.txt");
        }

    }

    public void writeToFile(String s, String fileName, Context context){
        try{
            /** comp 3111 code
             PrintWriter writer = new PrintWriter(fileName, "UTF-8");
             writer.println(s);
             writer.close();
             **/
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(s.getBytes());
            fos.close();
            Log.d(Tag, "/writeToFile: Saving To Disk at " + fileName);


        }catch(Exception e){
            Log.d(Tag, "/writeToFile: Error in Saving to Disk at " + fileName + " With error: " + e.getMessage());
        }
    }

    public String loadFromFile(String fileName, Context context){
        /**
         BufferedReader br = null;
         String result = "";
         **/
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();
            inputStream.close();
            Log.d(Tag, "/loadFromFile: Loading from Disk at " + fileName);
            return total.toString();

            /**
             String sCurrentLine;
             br = new BufferedReader(new FileReader(fileName));
             while ((sCurrentLine = br.readLine()) != null) {
             result+=sCurrentLine;
             }
             **/
        } catch (IOException e) {
            Log.d(Tag, "/loadFromFile: Error in Loading from Disk at " + fileName);

        }
        return "";
    }
}