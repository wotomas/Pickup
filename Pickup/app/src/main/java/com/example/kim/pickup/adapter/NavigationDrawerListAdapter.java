package com.example.kim.pickup.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.pickup.R;
import com.example.kim.pickup.unit.NavigationDrawerItem;

import java.util.ArrayList;

/**
 * Created by kim on 2015-12-13.
 */
public class NavigationDrawerListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<NavigationDrawerItem> mNavigationDrawerItems;

    public NavigationDrawerListAdapter(Context context, ArrayList<NavigationDrawerItem> navigationDrawerItems) {
        this.mContext = context;
        this.mNavigationDrawerItems = navigationDrawerItems;
    }

    @Override
    public int getCount() {
        return mNavigationDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavigationDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;
        if(convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtCount = (TextView) convertView.findViewById(R.id.counter);

        imgIcon.setImageResource(mNavigationDrawerItems.get(position).getIcon());
        txtTitle.setText(mNavigationDrawerItems.get(position).getTitle());
        if(mNavigationDrawerItems.get(position).isCounterVisible()){
            txtCount.setText(mNavigationDrawerItems.get(position).getCount());
        } else{
            // hide the counter view
            txtCount.setVisibility(View.GONE);
        }

        return convertView;
    }
}
