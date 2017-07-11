package com.sdaacademy.zientara.rafal.jemsobie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;

import java.util.List;

/**
 * Created by Evil on 09.07.2017.
 */

public class RestaurantsArrayAdapter extends ArrayAdapter<Restaurant> {

    private final LayoutInflater inflater;
    private List<Restaurant> restaurantList;

    public RestaurantsArrayAdapter(@NonNull Context context, List<Restaurant> restaurantList) {
        super(context, android.R.layout.simple_list_item_1);
        this.restaurantList = restaurantList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Restaurant restaurant = restaurantList.get(position);

        View rowView = convertView;
        if(rowView == null)
            rowView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

        TextView textView1 = (TextView) rowView.findViewById(android.R.id.text1);
        TextView textView2 = (TextView) rowView.findViewById(android.R.id.text2);

        textView1.setText(restaurant.getName());
        textView2.setText(restaurant.getComment());

        return rowView;
    }
}
