package com.jorgereina.sbs;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jorgereina.sbs.model.SbsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://api.lamusica.com/";
    private static final String TAG = "LAGARTO";
    private static final int ELEMENT_AT_POSITION_SIX = 6;
    private Button requestBtn;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.main_view_cl);
        requestBtn = findViewById(R.id.request_btn);

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                        String color = response.body().getDataList().get(ELEMENT_AT_POSITION_SIX).getColor();
                        constraintLayout.setBackgroundColor(Color.parseColor(color));
                        requestBtn.setText(response.body().getDataList().get(ELEMENT_AT_POSITION_SIX).getTitle());
                    }

                    @Override
                    public void onFailure(Call<SbsResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });

            }
        });
    }
}
