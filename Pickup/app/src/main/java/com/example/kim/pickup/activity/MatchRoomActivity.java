package com.example.kim.pickup.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.pickup.R;
import com.example.kim.pickup.controller.MatchController;
import com.example.kim.pickup.fragment.DistanceFragment;
import com.example.kim.pickup.fragment.PopularFragment;
import com.example.kim.pickup.fragment.TimeFragment;
import com.example.kim.pickup.unit.Match;

public class MatchRoomActivity extends AppCompatActivity {

    //TODO: Join match click --> match.popularity++
    String actionBarTitle = "";
    Button joinButton;
    ImageView courtImageView;
    private Match thisMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

        setContentView(R.layout.activity_match_room);

        if(TimeFragment.selectedMatch != null){
            thisMatch = TimeFragment.selectedMatch;
        }else if(DistanceFragment.selectedMatch != null){
            thisMatch = DistanceFragment.selectedMatch;
        }else if(PopularFragment.selectedMatch != null){
            thisMatch = PopularFragment.selectedMatch;
        }

        actionBarTitle = MainActivity.CURRENT_USER_SPORTS;
        actionBar.setTitle(actionBarTitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DE5460")));

        ImageView courtImageView = (ImageView) findViewById(R.id.courtImageView);
        switch(thisMatch.getSportKey()) {
            case "tennis":
                courtImageView.setImageResource(R.drawable.tennis_court);
                break;
            case "tableTennis":
                courtImageView.setImageResource(R.drawable.tabletennis_court);
                break;
            case "soccer":
                courtImageView.setImageResource(R.drawable.soccer_court);
                break;
            default:
                break;
        }

        TextView selectedSportType = (TextView) findViewById(R.id.sportsType);
        selectedSportType.setText(thisMatch.get_matchName());

        TextView matchLocation = (TextView) findViewById(R.id.matchLocation);
        matchLocation.setText(thisMatch.getLocationName());

        TextView player_popularityDetail = (TextView) findViewById(R.id.player_detail_text);
        player_popularityDetail.setText(thisMatch.getUsersCount() + "/" + thisMatch.getTotalCapacity() + " Joined");

        joinButton = (Button) findViewById(R.id.join_game_button);
        if(thisMatch.getOwnerID().equals(MainActivity.CURRENT_USER)) {
            joinButton.setText("Remove Game");
            joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //remove game on click
                    MatchController.getInstance().removeMatch(thisMatch);
                    finish();
                    MatchController.getInstance().getList(MainActivity.CURRENT_USER_SPORTS,  getBaseContext());
                }
            });
        } else {
            if(thisMatch.checkIfUserIsAlreadyJoined(MainActivity.CURRENT_USER)) {
                // if user is already inside the room
                // remove him from list, update parse data
                joinButton.setText("Withdraw");
                joinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //withdraw from list
                        MatchController.getInstance().withdrawFromMatch(thisMatch);
                        finish();
                        MatchController.getInstance().getList(MainActivity.CURRENT_USER_SPORTS, getBaseContext());
                    }
                });
            } else {
                joinButton.setText("Join Game");
                joinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MatchController.getInstance().joinMatch(thisMatch);
                        finish();
                        MatchController.getInstance().getList(MainActivity.CURRENT_USER_SPORTS, getBaseContext());
                    }
                });
            }

        }

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
        if (id == R.id.action_like_match) {
            thisMatch.setPopularity(thisMatch.getPopularity() + 1);
            MatchController.getInstance().updateMatch(thisMatch);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
