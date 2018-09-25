package com.jorgereina.sbs;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SbsApi {

    //http://api.lamusica.com/api/categories
    @GET("api/categories")
    Call<SbsResponse> getData();
}
