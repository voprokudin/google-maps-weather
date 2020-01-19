package com.vasylprokudin.googlemapsweather.data.rest;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VPWeatherIcon {

    @GET("wn/{icon_code}@2x.png")
    Single<String> getWeatherIconByCode(
            @Path("iconCode") String iconCode
    );
}
