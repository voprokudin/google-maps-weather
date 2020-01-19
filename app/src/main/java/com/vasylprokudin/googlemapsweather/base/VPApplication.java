package com.vasylprokudin.googlemapsweather.base;

import com.vasylprokudin.googlemapsweather.di.components.ApplicationComponent;
import com.vasylprokudin.googlemapsweather.di.components.DaggerApplicationComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class VPApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }
}
