package com.example.kim.pickup.fragment;

/**
 * Created by kim on 2015-10-08.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kim.pickup.R;
import com.example.kim.pickup.activity.MainActivity;
import com.example.kim.pickup.activity.MatchRoomActivity;
import com.example.kim.pickup.adapter.CustomTimeFragmentAdapter;
import com.example.kim.pickup.controller.MatchController;
import com.example.kim.pickup.unit.Match;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TimeFragment extends ListFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static Match selectedMatch;

    static final int VIEW_MATH_DETAIL = 0;
    public static ListView list;
    public static CustomTimeFragmentAdapter mAdapter;
    String sportName = MainActivity.CURRENT_USER_SPORTS;
    //String actionBarTitle = sportName;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TimeFragment newInstance(int sectionNumber) {
        TimeFragment fragment = new TimeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public TimeFragment() {

    }

    public Match getSelectedMatch() {
        return selectedMatch;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time, container, false);

        ArrayList<Match> matchArray = new ArrayList<Match>();
        MatchController.getInstance().getList(MainActivity.CURRENT_USER_SPORTS, getContext());
        matchArray = MatchController.getInstance().getTimeList();
        /**
        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DATE, 2);

        Match matchitem = new Match("Football 5 vs 5", date1, 0.5, MainActivity.CURRENT_USER_SPORTS, 15, 13, "nayeon");
        matchArray.add(matchitem);
        **/

        mAdapter = new CustomTimeFragmentAdapter(this.getContext(),inflater,matchArray,R.layout.custom_listview_time);

        list = (ListView) rootView.findViewById(R.id.list2);
        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Match listItem = (Match)list.getItemAtPosition(position);
                selectedMatch = listItem;
                Intent intent = new Intent(getActivity(), MatchRoomActivity.class);
                startActivityForResult(intent, VIEW_MATH_DETAIL);
            }
        }
        );


        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle(MainActivity.CURRENT_USER_SPORTS + " Pickup Sports");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DE5460")));

        return rootView;
    }
}