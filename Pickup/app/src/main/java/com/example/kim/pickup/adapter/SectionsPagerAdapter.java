package com.example.kim.pickup.adapter;

/**
 * Created by kim on 2015-10-08. Contribution: Nayeon 2015-10-14
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.kim.pickup.R;
import com.example.kim.pickup.fragment.DistanceFragment;
import com.example.kim.pickup.fragment.PopularFragment;
import com.example.kim.pickup.fragment.TimeFragment;

import java.util.Locale;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context _context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        _context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position == 0) {
            return DistanceFragment.newInstance(position + 1);
        } else if(position == 1) {
            return TimeFragment.newInstance(position+1);
        } else if(position == 2) {
            return PopularFragment.newInstance(position +1);
        } else {
            return null;
        }

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return _context.getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return _context.getString(R.string.title_section2).toUpperCase(l);
            case 2:
                return _context.getString(R.string.title_section3).toUpperCase(l);
        }
        return null;
    }
}