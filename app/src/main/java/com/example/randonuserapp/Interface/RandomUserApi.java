package com.example.randonuserapp.Interface;

import com.example.randonuserapp.model.RandomUserResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserApi {

    @GET("api")
    Call<RandomUserResults> getData(@Query("results") String resultsName);

}
