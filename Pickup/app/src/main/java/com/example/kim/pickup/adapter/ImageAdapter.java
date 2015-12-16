package com.example.kim.pickup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kim.pickup.R;
import com.example.kim.pickup.activity.MainActivity;

/**
 * Created by nylee on 16/12/15.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    String [] result;
    private static LayoutInflater inflater=null;
    private Integer[] mThumbIds;


    public ImageAdapter(Context context, String[] prgmNameList, Integer[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        mContext=context;
        mThumbIds=prgmImages;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        final int pos = position;

        rowView = inflater.inflate(R.layout.user_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(mThumbIds[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Link to chat w. them
                Toast.makeText(mContext, "You Clicked " + result[pos], Toast.LENGTH_LONG).show();
            }
        });

        return rowView;

       /* ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);



        return imageView;*/
    }
/*
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.temp_profile, R.drawable.temp_profile2,
            R.drawable.temp_profilecopy3, R.drawable.temp_profilecopy
            /*
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };*/
}