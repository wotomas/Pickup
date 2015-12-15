package com.example.kim.pickup.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kim.pickup.R;
import com.example.kim.pickup.fragment.TimeFragment;
import com.example.kim.pickup.unit.Match;

public class MatchRoomActivity extends AppCompatActivity {

    //TODO: Get this from Match Da
    String actionBarTitle = "";
    private Match thisMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

        setContentView(R.layout.activity_match_room);

        thisMatch = TimeFragment.selectedMatch;
        actionBarTitle = thisMatch.get_matchName();
        actionBar.setTitle(actionBarTitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DE5460")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
