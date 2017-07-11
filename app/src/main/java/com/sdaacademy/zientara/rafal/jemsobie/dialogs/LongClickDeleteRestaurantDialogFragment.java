package com.sdaacademy.zientara.rafal.jemsobie.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sdaacademy.zientara.rafal.jemsobie.MainActivity;
import com.sdaacademy.zientara.rafal.jemsobie.R;
import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;
import com.sdaacademy.zientara.rafal.jemsobie.retrofit.BaseRetrofit;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RENT on 2017-07-11.
 */

public class LongClickDeleteRestaurantDialogFragment extends DialogFragment {


    public static final String RESTAURANT_KEY = "restaurant_key";
    private Restaurant restaurant;
    @BindView(R.id.ask_textView)
    TextView askTextView;

    public static LongClickDeleteRestaurantDialogFragment newInstance(Restaurant restaurant) {
        LongClickDeleteRestaurantDialogFragment longClickDeleteRestaurantDialogFragment = new LongClickDeleteRestaurantDialogFragment();
        Bundle bundle = new Bundle();
        // zapisywanie klikniętej restauracji
        bundle.putParcelable(RESTAURANT_KEY, restaurant); // parcelable w modelu / restaurants
        longClickDeleteRestaurantDialogFragment.setArguments(bundle);
        return longClickDeleteRestaurantDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //// TODO: 2017-07-11 pobierz restauracje
        // jeżeli nie ma nulla na restauracji z bundla wyżej, to lecimy z deleteRestaurant
        Bundle arguments = getArguments();
        if (arguments != null) {
            restaurant = arguments.getParcelable(RESTAURANT_KEY);
        }

    }

    @Nullable
    @Override // tworzy widok
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.long_click_delete_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override // po stworzeniu widoku
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // // TODO: 2017-07-11 szczegóły restauracji
        askTextView.append("\n" + restaurant.getName());
    }

    @OnClick(R.id.askButton_yes)
    public void deleteRestaurant() {
        Call<Restaurant> restaurantCall = new BaseRetrofit().getRestaurantsApi().deleteRestaurants(restaurant.getId());
        restaurantCall.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Usunięte...", Toast.LENGTH_SHORT).show();
                    onDeleteSuccessCallBack();
                    dismiss();
                }
                else {
                    Toast.makeText(getActivity(), "Nie usunięte response...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

                Toast.makeText(getActivity(), "Nie usunięte failure..." + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onDeleteSuccessCallBack() {
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).clickRefreshList();
        }
        else {
            Log.w(getClass().getSimpleName(), String.format("Don't forget to implement %s", OnDeleteSuccess.class.getSimpleName()));
        }
    }

    // pozyskiwanie błędu
    private String getErrorString(Response<Restaurant> response) {
        String errorMessage = null;

        try {
            errorMessage = response.errorBody().string();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage = e.getMessage();
        }
        return errorMessage;
    }

    @OnClick(R.id.askButton_no)
    public void cancel() {
        dismiss();
    }

    // implementujemy do MainActivity
    public interface OnDeleteSuccess {
        void onDeleteSuccess();
    }

}
