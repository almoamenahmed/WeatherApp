package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {
    @SerializedName("main")
    Main main;

    @SerializedName("weather")
    List<Weather> weathers=null;

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }
}
