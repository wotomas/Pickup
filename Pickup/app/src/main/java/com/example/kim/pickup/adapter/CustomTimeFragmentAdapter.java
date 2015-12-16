package com.example.kim.pickup.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.pickup.R;
import com.example.kim.pickup.activity.MainActivity;
import com.example.kim.pickup.unit.Match;
import com.parse.ParseGeoPoint;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by nylee on 12/12/15.
 */
public class CustomTimeFragmentAdapter extends BaseAdapter {
    Context _context;
    LayoutInflater _inflater;
    ArrayList<Match> _matchList;
    int _layout;

    public CustomTimeFragmentAdapter(Context _context, LayoutInflater _inflater, ArrayList<Match> _matchList, int _layout) {
        this._context = _context;
        this._inflater = _inflater;
        this._matchList = _matchList;
        this._layout = _layout;
    }

    @Override
    public int getCount() {
        return _matchList.size();
    }

    @Override
    public Object getItem(int position) {
        return _matchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(_context).inflate(_layout, parent, false);
            //itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
        }

        Calendar now = Calendar.getInstance();
        Calendar startTime = _matchList.get(position).get_startTime();
        String remainingText;

        long remaining = startTime.getTimeInMillis() - now.getTimeInMillis();
        long hours = remaining / 3600000;
        if(hours <24){
            long mins = remaining / 60000 % 60;
            remainingText = String.valueOf(hours)+"h "+String.valueOf(mins)+"m";
        }else{
            long day = hours/24;
            long hour = hours%24;
            remainingText = String.valueOf(day)+"d "+String.valueOf(hour)+"h";
        }

        TextView roomName = (TextView) convertView.findViewById(R.id.rmName);
        roomName.setText(_matchList.get(position).get_matchName());

        TextView priorityOne = (TextView) convertView.findViewById(R.id.priorityOneText);//time
        priorityOne.setText(remainingText);

        TextView priorityTwo = (TextView) convertView.findViewById(R.id.priorityTwoText);//distance
        ParseGeoPoint matchLocation = new ParseGeoPoint();
        matchLocation = _matchList.get(position).get_location();

        ParseGeoPoint myLocation = new ParseGeoPoint(MainActivity.currentX, MainActivity.currentY);
        Log.d("GeoPoints", myLocation.getLatitude() + " " + myLocation.getLongitude());
        DecimalFormat decim = new DecimalFormat("#.###");
        priorityTwo.setText(String.valueOf(Double.parseDouble(decim.format(matchLocation.distanceInKilometersTo(myLocation))))+"km");

        TextView priorityThree = (TextView) convertView.findViewById(R.id.priorityThreeText);//popularity
        priorityThree.setText(String.valueOf(_matchList.get(position).getPopularity() + " " + _matchList.get(position).getUsersCount()) + "/" + String.valueOf(_matchList.get(position).getTotalCapacity()));

        ImageView userImage = (ImageView) convertView.findViewById(R.id.rmProfileImage);
        userImage.setImageResource(R.drawable.temp_profile);

        return convertView;
    }
}
