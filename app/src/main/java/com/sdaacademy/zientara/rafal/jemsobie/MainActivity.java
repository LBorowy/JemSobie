package com.sdaacademy.zientara.rafal.jemsobie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sdaacademy.zientara.rafal.jemsobie.adapter.RecyclerViewAdapter;
import com.sdaacademy.zientara.rafal.jemsobie.adapter.RestaurantsHolderArrayAdapter;
import com.sdaacademy.zientara.rafal.jemsobie.dialogs.AddRestaurantDialogFragment;
import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;
import com.sdaacademy.zientara.rafal.jemsobie.retrofit.BaseRetrofit;
import com.sdaacademy.zientara.rafal.jemsobie.service.RestaurantsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_refreshButton)
    Button refreshButton;

    @BindView(R.id.main_addButton)
    Button addButton;

    @BindView(R.id.main_listView)
    ListView listView;

    @BindView(R.id.main_recyclerView)
    RecyclerView recyclerView;

//    private Retrofit retrofit;
//    private RestaurantsApi restaurantsApi;
    private BaseRetrofit baseRetrofit;
    private List<Restaurant> restaurantList;
    private ProgressDialog progressDialog;
    private ArrayAdapter adapter;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepareRestApi();
        prepareListAdapter();
        downloadRestaurants();
    }

    private void downloadRestaurants() {
        showProgressDialog();
        clearList();
        updateListWithoutRxJava();
//        updateListRxJava();
    }

    private void prepareRestApi() {
       baseRetrofit = new BaseRetrofit();
    }

    private void updateListWithoutRxJava() {
//        Call<List<Restaurant>> allPeople = restaurantsApi.getAllRestaurants();
        Call<List<Restaurant>> allPeople = baseRetrofit.getRestaurantsApi().getAllRestaurants();
        allPeople.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.isSuccessful()) {
                    restaurantList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Restaurant count : " + restaurantList.size(), Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Zepsuło sie\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressDialog();
            }
        });
    }

    private void clearList() {
        restaurantList.clear();
        adapter.notifyDataSetChanged();
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void updateListRxJava() {
        /*restaurantsApi.getAllRestaurantsRxJava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Restaurant>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        clearList();
                    }

                    @Override
                    public void onNext(@NonNull List<Restaurant> reposes) {
                        restaurantList.addAll(reposes);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("SCIAGANIEE", e.getMessage());
                        hideProgressDialog();
                    }

                    @Override
                    public void onComplete() {
                        hideProgressDialog();
                    }
                });*/
    }

    private void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting informations...");
        progressDialog.show();
    }

    private void prepareListAdapter() {
        restaurantList = new ArrayList<>();
//        ListAdapter adapter = new RestaurantsAdapterNope(getApplicationContext(), restaurantList);
//        ListAdapter adapter = new RestaurantsArrayAdapter(getApplicationContext(), restaurantList);
        adapter = new RestaurantsHolderArrayAdapter(getApplicationContext(), restaurantList);
        listView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), restaurantList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @OnClick(R.id.main_addButton)
    public void clickShowAddRestaurantDialog() {
        //// TODO: 09.07.2017 open DialogFragment and add Restaurant

        AddRestaurantDialogFragment addRestaurantDialogFragment = new AddRestaurantDialogFragment();
        addRestaurantDialogFragment.show(getSupportFragmentManager(), null);

//        Restaurant restaurant = new Restaurant();
//        restaurant.setName("Petra Gyros");
//        restaurant.setComment("Dużo/tanio/smacznie");
//        restaurant.setRating(4.1f);
//
////        Call<Restaurant> restaurantCall = restaurantsApi.postRestaurants(restaurant);
//        Call<Restaurant> restaurantCall = baseRetrofit.getRestaurantsApi().postRestaurants(restaurant);
//        restaurantCall.enqueue(new Callback<Restaurant>() {
//            @Override
//            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "Wysłane...", Toast.LENGTH_SHORT).show();
//                    clickRefreshList();
//                }
//                else {
//                    Toast.makeText(MainActivity.this, "Nie wysłane...", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Restaurant> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Nie wysłało się\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @OnClick(R.id.main_refreshButton)
    public void clickRefreshList() {
        downloadRestaurants();
    }
}
