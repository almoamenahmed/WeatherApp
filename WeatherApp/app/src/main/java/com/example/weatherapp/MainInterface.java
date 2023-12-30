package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainInterface {
    @GET("weather?q=Detroit&appid=1f1759c1d0feb050922ad9715e703f47")
    Call<Model> getData();

    @GET("weather")
    Call<Model> getCityData(@Query("q") String q,@Query("appid") String appid);
}
