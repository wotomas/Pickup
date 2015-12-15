package com.example.kim.pickup.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.kim.pickup.R;
import com.example.kim.pickup.adapter.NavigationDrawerListAdapter;
import com.example.kim.pickup.adapter.SectionsPagerAdapter;
import com.example.kim.pickup.controller.MatchController;
import com.example.kim.pickup.storage.MatchStorage;
import com.example.kim.pickup.unit.NavigationDrawerItem;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener{
    public static String CURRENT_USER;
    public static String CURRENT_USER_SPORTS;
    private static final String TAG = MainActivity.class.getSimpleName();
    static final int CREATE_MATCH_REQUEST = 0;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    // sports name
    private String mSportName = "";

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;


    private String[] mDrawerTitles;
    private TypedArray mDrawerIcons;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<NavigationDrawerItem> mNavigationDrawerItems;
    private NavigationDrawerListAdapter mDrawerAdapter;

    private Handler handler;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MatchController.getInstance().getList(CURRENT_USER_SPORTS, getBaseContext());
            Log.d("Timer", "Timer is running to get list from server!");
            handler.postDelayed(this, 5000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MatchController.getInstance().initMatchStorage(new MatchStorage(), this);
        //MatchController.getInstance().localMatchListReset(this);
        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        mTitle = mDrawerTitle = getTitle();

        handler  = new Handler();
        mDrawerTitles = getResources().getStringArray(R.array.nav_drawer_items);
        mDrawerIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mNavigationDrawerItems = new ArrayList<NavigationDrawerItem>();

        mNavigationDrawerItems.add(new NavigationDrawerItem(mDrawerTitles[0], mDrawerIcons.getResourceId(0, -1)));
        mNavigationDrawerItems.add(new NavigationDrawerItem(mDrawerTitles[1], mDrawerIcons.getResourceId(1, -1)));
        mNavigationDrawerItems.add(new NavigationDrawerItem(mDrawerTitles[2], mDrawerIcons.getResourceId(2, -1)));
        mNavigationDrawerItems.add(new NavigationDrawerItem(mDrawerTitles[3], mDrawerIcons.getResourceId(3, -1)));
        mDrawerIcons.recycle();

        mDrawerAdapter = new NavigationDrawerListAdapter(getApplication(), mNavigationDrawerItems);
        mDrawerList.setAdapter(mDrawerAdapter);


        // enabling action bar app icon and behaving it as toggle button
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.listdrawable, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                actionBar.setTitle(mSportName + " Pickup Sports");
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                actionBar.setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);


        ParseAnalytics.trackAppOpened(getIntent());

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            navigateToLogin();
        }
        else if( currentUser.get("sport").equals("none") ) {
            //if user exists, but did not select any sports
            //move to select choose activity
            CURRENT_USER = currentUser.getUsername();
            Intent intent = new Intent(MainActivity.this, SportSelectionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            CURRENT_USER = currentUser.getUsername();
            CURRENT_USER_SPORTS = currentUser.get("sport").toString();
            Log.i(TAG, currentUser.getUsername() + " " + currentUser.get("sport"));
        }


        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mSportName = currentUser.get("sport").toString() + "'s";
        actionBar.setTitle(mSportName + " Pickup Sports");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.action_addMatch:
                Intent intent = new Intent(this, CreateMatchActivity.class);
                startActivityForResult(intent,CREATE_MATCH_REQUEST);
                return true;
            case R.id.action_logout:
                //noinspection SimplifiableIfStatement
                ParseUser.logOut();
                navigateToLogin();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.d("Return", "Successfully returned data");
        // Check which request we're responding to
        if (requestCode == CREATE_MATCH_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //save the result from intent returned.
                Log.d(CreateMatchActivity.TAG, "succesfully created match item and returned");
                MatchController.getInstance().saveToParse(this);
            }
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }
}
