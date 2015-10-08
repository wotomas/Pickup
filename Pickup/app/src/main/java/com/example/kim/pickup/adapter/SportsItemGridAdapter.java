package com.example.kim.pickup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.pickup.R;
import com.example.kim.pickup.unit.SportsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kim on 2015-10-08.
 */
public class SportsItemGridAdapter extends RecyclerView.Adapter<SportsItemGridAdapter.ViewHolder> {
    private List<SportsItem> _items;

    public SportsItemGridAdapter() {
        super();
        _items = new ArrayList<SportsItem>();
        SportsItem sports = new SportsItem();
        sports.set_name("Tennis");
        sports.set_thumbnail(R.drawable.tennis);
        _items.add(sports);

        sports = new SportsItem();
        sports.set_name("Table Tennis");
        sports.set_thumbnail(R.drawable.tabletennis);
        _items.add(sports);

        sports = new SportsItem();
        sports.set_name("Squash");
        sports.set_thumbnail(R.drawable.squash);
        _items.add(sports);

        sports = new SportsItem();
        sports.set_name("Pool");
        sports.set_thumbnail(R.drawable.pool);
        _items.add(sports);

        sports = new SportsItem();
        sports.set_name("Badminton");
        sports.set_thumbnail(R.drawable.badminton);
        _items.add(sports);

        sports = new SportsItem();
        sports.set_name("Basketball");
        sports.set_thumbnail(R.drawable.basketball);
        _items.add(sports);

        sports = new SportsItem();
        sports.set_name("Soccer");
        sports.set_thumbnail(R.drawable.soccer);
        _items.add(sports);

        sports = new SportsItem();
        sports.set_name("Volleyball");
        sports.set_thumbnail(R.drawable.volleyball);
        _items.add(sports);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_sports_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        SportsItem nature = _items.get(i);
        viewHolder.name.setText(nature.get_name());
        viewHolder.imgThumbnail.setImageResource(nature.get_thumbnail());
    }

    @Override
    public int getItemCount() {
        return _items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            name = (TextView)itemView.findViewById(R.id.tv_species);
        }
    }


}
