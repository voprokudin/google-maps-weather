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
    VPGetWeatherByGeographicCoordinatesUseCase() {}

    @Inject
    VPWeatherService weatherService;

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
    }
}
