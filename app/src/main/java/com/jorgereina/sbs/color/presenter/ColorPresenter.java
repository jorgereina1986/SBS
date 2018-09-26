package com.jorgereina.sbs.color.presenter;

import android.util.Log;

import com.jorgereina.sbs.BasePresenter;
import com.jorgereina.sbs.color.network.SbsApi;
import com.jorgereina.sbs.model.SbsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorPresenter extends BasePresenter<ColorContract.ColorView> implements ColorContract.Presenter {
    private static final String BASE_URL = "http://api.lamusica.com/";
    private static final String TAG = "LAGARTO";
    private static final int ELEMENT_AT_POSITION_SIX = 6;


    public ColorPresenter() {
    }

    @Override
    public void onColorRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SbsApi service = retrofit.create(SbsApi.class);
        Call<SbsResponse> call = service.getData();
        call.enqueue(new Callback<SbsResponse>() {
            @Override
            public void onResponse(Call<SbsResponse> call, Response<SbsResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getDataList().get(ELEMENT_AT_POSITION_SIX).getTitle());
                //getting color at position 6
                view.hideProgress();
                String color = response.body().getDataList().get(ELEMENT_AT_POSITION_SIX).getColor();
                String title = response.body().getDataList().get(ELEMENT_AT_POSITION_SIX).getTitle();
                view.showColorRequest(color);
                view.showText(title);
            }

            @Override
            public void onFailure(Call<SbsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
