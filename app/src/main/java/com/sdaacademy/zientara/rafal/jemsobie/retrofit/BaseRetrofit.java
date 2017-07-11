package com.sdaacademy.zientara.rafal.jemsobie.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sdaacademy.zientara.rafal.jemsobie.service.RestaurantsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RENT on 2017-07-10.
 */

public class BaseRetrofit {
    private static String ENDPOINT = "http://10.40.21.186:3000/";

    private final Retrofit retrofit;
    private final RestaurantsApi restaurantsApi;

    public BaseRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())//potrzebne aby można było dać Observable zamiast Call
                .build();

        restaurantsApi = retrofit.create(RestaurantsApi.class);
    }

    public RestaurantsApi getRestaurantsApi() {
        return restaurantsApi;
    }
}
