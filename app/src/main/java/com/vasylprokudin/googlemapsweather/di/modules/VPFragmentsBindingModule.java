package com.vasylprokudin.googlemapsweather.di.modules;

import com.vasylprokudin.googlemapsweather.presentation.view.VPWeatherFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class VPFragmentsBindingModule {

    @ContributesAndroidInjector
    abstract VPWeatherFragment provideWeatherFragment();
}
