package com.vasylprokudin.googlemapsweather.di.modules;

import com.vasylprokudin.googlemapsweather.base.VPActivity;
import com.vasylprokudin.googlemapsweather.presentation.view.VPMapActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class VPWeatherActivityModule {

    @Binds
    abstract VPActivity providesActivity(VPMapActivity activity);
}
