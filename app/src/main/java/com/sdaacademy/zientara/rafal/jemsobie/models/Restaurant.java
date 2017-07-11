package com.sdaacademy.zientara.rafal.jemsobie.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Evil on 08.07.2017.
 */

public class Restaurant implements Parcelable { // parcelable wrzucamy do Bundle
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

    public Restaurant() {
    }

    protected Restaurant(Parcel in) {
        name = in.readString();
        comment = in.readString();
        url = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(comment);
        parcel.writeString(url);
    }
}
