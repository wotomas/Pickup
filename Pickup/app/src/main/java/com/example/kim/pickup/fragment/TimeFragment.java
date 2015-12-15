package com.example.kim.pickup.fragment;

/**
 * Created by kim on 2015-10-08.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.kim.pickup.CustomFragmentAdapter;
import com.example.kim.pickup.R;
import com.example.kim.pickup.activity.CreateMatchActivity;
import com.example.kim.pickup.activity.MatchRoomActivity;
import com.example.kim.pickup.unit.Match;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class TimeFragment extends ListFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    static final int VIEW_MATH_DETAIL = 0;


    String sportName = "Basketball";
    String actionBarTitle = sportName;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time, container, false);

        ArrayList<Match> matchArray = new ArrayList<Match>();

        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DATE, 2);


        Match matchitem = new Match("Football 5 vs 5", date1, 0.5, 1, 15, 13, 20149785);
        matchArray.add(matchitem);

        CustomFragmentAdapter _adapter = new CustomFragmentAdapter(this.getContext(),inflater,matchArray,R.layout.custom_listview_time);

        final ListView list = (ListView) rootView.findViewById(R.id.list2);
        list.setAdapter(_adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = list.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), MatchRoomActivity.class);
                startActivityForResult(intent, VIEW_MATH_DETAIL);
            }
        }
        );


        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle(actionBarTitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DE5460")));

        return rootView;
    }
}