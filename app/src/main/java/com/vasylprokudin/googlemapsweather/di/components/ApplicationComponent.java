package com.vasylprokudin.googlemapsweather.di.components;

import android.app.Application;
import com.vasylprokudin.googlemapsweather.base.VPApplication;
import com.vasylprokudin.googlemapsweather.di.modules.VPActivityBuildersModule;
import com.vasylprokudin.googlemapsweather.di.modules.VPApplicationModule;
import com.vasylprokudin.googlemapsweather.di.modules.VPContextModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        VPActivityBuildersModule.class,
        VPApplicationModule.class,
        VPContextModule.class
        })
public interface ApplicationComponent extends AndroidInjector<VPApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
