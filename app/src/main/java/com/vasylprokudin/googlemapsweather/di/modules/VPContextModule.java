package com.vasylprokudin.googlemapsweather.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class VPContextModule {

    @Binds
    abstract Context provideContext(Application application);
}
