package com.example.kim.pickup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.kim.pickup.R;
import com.example.kim.pickup.adapter.SportsItemGridAdapter;
import com.example.kim.pickup.listener.RecyclerItemClickListener;
import com.parse.ParseUser;

public class SportSelectionActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_selection);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SportsItemGridAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //handel item lick
                Log.d("GridActivity", Integer.toString(position));
                ParseUser currentUser = ParseUser.getCurrentUser();
                if(position == 0) {
                    currentUser.put("sport", "tennis");
                } else if(position == 1) {
                    currentUser.put("sport", "tableTennis");
                } else if(position == 2) {
                    currentUser.put("sport", "squash");
                } else if(position == 3) {
                    currentUser.put("sport", "pool");
                } else if(position == 4) {
                    currentUser.put("sport", "badminton");
                } else if(position == 5) {
                    currentUser.put("sport", "basketball");
                } else if(position == 6) {
                    currentUser.put("sport", "soccer");
                } else if(position == 7) {
                    currentUser.put("sport", "volleyball");
                } else {
                    currentUser.put("sport", "none");
                }
                currentUser.saveInBackground();
                Intent intent = new Intent(SportSelectionActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                // tennis -> 0
                // tableTennis -> 1
                // Squash -> 2
                // Pool - > 3
                // Badminton -> 4
                // Basketball -> 5
                // Soccer -> 6
                // Volleyball -> 7


            }
        }));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sport_selection, menu);
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
