package com.sdaacademy.zientara.rafal.jemsobie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Evil on 08.07.2017.
 */

public class Restaurant {
    //    @SerializedName("id")
//    @Expose
    Long id;


    //http://10.40.21.186:3000/restaurants

    @Expose
    @SerializedName("restaurant_name")
    String name;

    //    @SerializedName("comment")
//    @Expose
    String comment;

    //    @SerializedName("url")
//    @Expose
    String url;

    //    @SerializedName("rating")
//    @Expose
    Float rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
