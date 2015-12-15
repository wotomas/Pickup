package com.example.kim.pickup.fragment;

/**
 * Created by kim on 2015-10-08.
 */

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.kim.pickup.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DistanceFragment extends ListFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DistanceFragment newInstance(int sectionNumber) {
        DistanceFragment fragment = new DistanceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public DistanceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_distance, container, false);

        ArrayList<String[]> test = new ArrayList<String[]>();
        String[] data = new String[4];
        data[0]="Nayeon LEE";
        data[0]="1hr 20min";
        data[0]="0.7km";
        data[0]="12/13";

        test.add(data);

        ArrayAdapter<String[]> Adapter = new ArrayAdapter<String[]>(getActivity(), R.layout.custom_listview_distance, R.id.rmName, test);
        setListAdapter(Adapter);

        return rootView;
    }
}