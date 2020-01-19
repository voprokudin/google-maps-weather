package com.vasylprokudin.googlemapsweather.data.rest;

import com.vasylprokudin.googlemapsweather.data.model.VPWeatherInfoModel;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VPWeatherService {

    @GET("forecast")
    Single<VPWeatherInfoModel> getWeatherByGeolocation(
            @Query("lat") Double latitude,
            @Query("lon") Double longitude,
            @Query("appid") String mapKey
    );
}
