package com.sdaacademy.zientara.rafal.jemsobie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;

import java.util.List;

/**
 * Created by Evil on 09.07.2017.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
    private List<Restaurant> restaurantList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_2, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Restaurant restaurant = restaurantList.get(i);
        customViewHolder.name.setText(restaurant.getName());
        customViewHolder.comment.setText(restaurant.getComment());
    }

    /*
        //Render image using Picasso library
        if (!TextUtils.isEmpty(restaurant.getUrl())) {
            Picasso.with(context).load(restaurant.getUrl())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(customViewHolder.imageView);
        }*/

    @Override
    public int getItemCount() {
        return (null != restaurantList ? restaurantList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView comment;

        CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(android.R.id.text1);
            this.comment = (TextView) view.findViewById(android.R.id.text2);
        }
    }
}