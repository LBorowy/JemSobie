package com.sdaacademy.zientara.rafal.jemsobie.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;

import java.util.List;

/**
 * Created by Evil on 09.07.2017.
 */

public class RestaurantsAdapterNope implements ListAdapter {

    private final LayoutInflater inflater;
    private List<Restaurant> restaurantList;

    public RestaurantsAdapterNope(@NonNull Context context, List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int i) {
        return restaurantList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Restaurant restaurant = restaurantList.get(position);

        View rowView = view;
        if(rowView == null)
            rowView = inflater.inflate(android.R.layout.simple_list_item_2, viewGroup, false);

        TextView textView1 = (TextView) rowView.findViewById(android.R.id.text1);
        TextView textView2 = (TextView) rowView.findViewById(android.R.id.text2);

        textView1.setText(restaurant.getName());
        textView2.setText(restaurant.getComment());

        return rowView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return restaurantList.size();
    }

    @Override
    public boolean isEmpty() {
        return restaurantList.isEmpty();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }
}
