package com.vasylprokudin.googlemapsweather.data.model;

import java.util.ArrayList;

public class VPWeatherInfoModel {

    private ArrayList<VPWeatherDayDetail> list;

    private VPWeatherCity city;

    public VPWeatherInfoModel(ArrayList<VPWeatherDayDetail> list, VPWeatherCity city) {
        this.list = list;
        this.city = city;
    }

    public ArrayList<VPWeatherDayDetail> getList() {
        return list;
    }

    public VPWeatherCity getCity() {
        return city;
    }

    public void setList(ArrayList<VPWeatherDayDetail> list) {
        this.list = list;
    }

    public void setCity(VPWeatherCity city) {
        this.city = city;
    }
}
