package com.vasylprokudin.googlemapsweather.di.modules;

import com.vasylprokudin.googlemapsweather.presentation.view.VPMapActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class VPActivityBuildersModule {

    @ContributesAndroidInjector(modules = {
            VPFragmentsBindingModule.class,
            VPWeatherActivityModule.class,
            VPApplicationModule.class
            })
    abstract VPMapActivity contributeWeatherActivity();
}
