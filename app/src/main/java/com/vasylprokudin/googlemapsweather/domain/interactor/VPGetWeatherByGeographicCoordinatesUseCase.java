package com.vasylprokudin.googlemapsweather.domain.interactor;

import com.vasylprokudin.googlemapsweather.data.model.VPWeatherInfoModel;
import com.vasylprokudin.googlemapsweather.data.rest.VPWeatherService;
import com.vasylprokudin.googlemapsweather.domain.interactor.base.VPSingleUseCase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Single;
import static com.vasylprokudin.googlemapsweather.C.Keys.WEATHER_KEY;

public class VPGetWeatherByGeographicCoordinatesUseCase extends VPSingleUseCase<VPWeatherInfoModel, List<Double>> {

    @Inject
    public VPGetWeatherByGeographicCoordinatesUseCase() {}

    @Inject
    public VPWeatherService weatherService;

    @NotNull
    @Override
    public Single<VPWeatherInfoModel> buildUseCaseSingle(@Nullable List<Double> coordinates) {
        if (coordinates != null) {
            return weatherByGeolocation(coordinates);
        } else {
            return Single.error(new IllegalStateException("location must be provided"));
        }
    }

    private Single<VPWeatherInfoModel> weatherByGeolocation(List<Double> coordinates) {
        return weatherService.getWeatherByGeolocation(coordinates.get(0), coordinates.get(1), WEATHER_KEY);

        /** Here I should get iconCode field from API and then using
         *
         * map().flatMapIterable() operators iterate each iconCode field in Array and make
         *
         * separate network call for each retrieved iconCode using flatMap() operator
         *
         * and update icon field of VPWeatherDetail model. Then I will be have all data and
         *
         * will be able populate recycle view with all data retrieved from 2 different APIs.
         **/
    }
}
