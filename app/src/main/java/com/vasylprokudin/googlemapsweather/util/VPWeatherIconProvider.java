package com.vasylprokudin.googlemapsweather.util;

import javax.inject.Inject;
import static com.vasylprokudin.googlemapsweather.C.Strings.BASE_WEATHER_ICON_BODY_ADDRESS;
import static com.vasylprokudin.googlemapsweather.C.Strings.BASE_WEATHER_ICON_FORMAT_ADDRESS;

public class VPWeatherIconProvider {

    @Inject
    public VPWeatherIconProvider() {}

    public String provideCurrentIcon(String iconCode) {
        return BASE_WEATHER_ICON_BODY_ADDRESS + iconCode + BASE_WEATHER_ICON_FORMAT_ADDRESS;
    }
}
