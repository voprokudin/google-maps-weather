package com.vasylprokudin.googlemapsweather;

public class C {

    public interface Integer {
        int FORECAST_DAYS = 3;
        int ZERO = 0;
    }

    public interface Double {
        double KELVIN_TEMPERATURE = 273.15;
    }

    public interface Strings {
        String CELSIUS = "°C";
        String BASE_WEATHER_ICON_BODY_ADDRESS = "http://openweathermap.org/img/wn/";
        String BASE_WEATHER_ICON_FORMAT_ADDRESS = "@2x.png";
    }

    public interface Keys {
        String WEATHER_KEY = "d1d2ac7038ef81be20e04f3cbe22b83c";
    }

    public interface BundleArgs {
        String USER_LATITUDE = "user_lotitude";
        String USER_LONGITUDE = "user_longitude";
    }
}
