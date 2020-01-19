package com.vasylprokudin.googlemapsweather.util;

import com.vasylprokudin.googlemapsweather.C;
import javax.inject.Inject;

public class VPTemperatureConverter {

    @Inject
    public VPTemperatureConverter() {
    }

    public int convertKelvinToCelsius(double kelvinTemp) {
        double celsiusTemp = kelvinTemp - C.Double.KELVIN_TEMPERATURE;
        return (int) celsiusTemp;
    }
}
