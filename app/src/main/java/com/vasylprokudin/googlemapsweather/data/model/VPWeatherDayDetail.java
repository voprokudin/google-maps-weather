package com.vasylprokudin.googlemapsweather.data.model;

import java.util.ArrayList;

public class VPWeatherDayDetail {

    private Long dt;

    private VPMainWeather main;

    private ArrayList<VPWeatherDetail> weather;

    public Long getDt() {
        return dt;
    }

    public VPMainWeather getMain() {
        return main;
    }

    public ArrayList<VPWeatherDetail> getWeather() {
        return weather;
    }
}
