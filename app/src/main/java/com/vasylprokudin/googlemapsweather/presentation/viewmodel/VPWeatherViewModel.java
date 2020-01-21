package com.vasylprokudin.googlemapsweather.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.vasylprokudin.googlemapsweather.data.model.VPWeatherInfoModel;
import com.vasylprokudin.googlemapsweather.domain.interactor.VPGetWeatherByGeographicCoordinatesUseCase;
import com.vasylprokudin.googlemapsweather.domain.interactor.base.VPEmptySingleObserver;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import javax.inject.Inject;

public class VPWeatherViewModel extends ViewModel {

    @Inject
    VPWeatherViewModel() {}

    @Inject
    VPGetWeatherByGeographicCoordinatesUseCase getWeatherByGeographicCoordinatesUseCase;

    private final MutableLiveData<VPWeatherInfoModel> mutableLiveData = new MutableLiveData<>();

    public LiveData<VPWeatherInfoModel> getVPWeatherObservable() {
        return mutableLiveData;
    }

    public void fetchWeather(List<Double> coordinates) {
        getWeatherByGeographicCoordinatesUseCase.execute(new GetWeatherByGeographicCoordinatesObserver(), coordinates);
    }

    class GetWeatherByGeographicCoordinatesObserver extends VPEmptySingleObserver<VPWeatherInfoModel> {

        @Override
        public void onSuccess(VPWeatherInfoModel weatherInfoModel) {
            mutableLiveData.setValue(weatherInfoModel);
        }

        @Override
        public void onError(@NotNull Throwable throwable) {
            mutableLiveData.setValue(null);
        }
    }
}
