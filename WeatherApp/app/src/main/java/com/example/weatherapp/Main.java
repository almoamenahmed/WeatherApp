package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    double temp;
    @SerializedName("feels_like")
    double feels;
    @SerializedName("humidity")
    int humidity;

    public double getTemp() {
        return temp;
    }

    public double getFeels() {
        return feels;
    }

    public int getHumidity() {
        return humidity;
    }
}
