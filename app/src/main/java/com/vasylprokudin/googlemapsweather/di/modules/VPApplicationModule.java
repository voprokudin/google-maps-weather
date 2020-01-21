package com.vasylprokudin.googlemapsweather.di.modules;

import com.vasylprokudin.googlemapsweather.base.VPActivity;
import com.vasylprokudin.googlemapsweather.data.rest.VPWeatherService;
import com.vasylprokudin.googlemapsweather.presentation.navigation.VPWeatherNavigator;
import com.vasylprokudin.googlemapsweather.util.VPDateFormatter;
import com.vasylprokudin.googlemapsweather.util.VPTemperatureConverter;
import com.vasylprokudin.googlemapsweather.util.VPWeatherIconProvider;
import com.vasylprokudin.googlemapsweather.util.fragment.VPFragmentUtil;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {VPViewModelModule.class})
public class VPApplicationModule {

    @Provides
    Retrofit provideRetrofit() {
        String BASE_URL = "https://api.openweathermap.org/data/2.5/";
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    VPWeatherService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(VPWeatherService.class);
    }

    @Provides
    VPFragmentUtil provideVPFragmentUtil() {
        return new VPFragmentUtil();
    }

    @Provides
    VPWeatherNavigator provideVPProductsNavigator(
            VPActivity activity,
            VPFragmentUtil fragmentUtil
    ) {
        return new VPWeatherNavigator(activity, fragmentUtil);
    }

    @Provides
    VPTemperatureConverter provideVPTemperatureConverter() {
        return new VPTemperatureConverter();
    }

    @Provides
    VPDateFormatter provideVPDateFormatter() {
        return new VPDateFormatter();
    }

    @Provides
    VPWeatherIconProvider provideVPWeatherIconProvider() {
        return new VPWeatherIconProvider();
    }
}
