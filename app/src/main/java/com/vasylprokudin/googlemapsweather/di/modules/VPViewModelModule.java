package com.vasylprokudin.googlemapsweather.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.vasylprokudin.googlemapsweather.di.util.VPViewModelKey;
import com.vasylprokudin.googlemapsweather.presentation.viewmodel.VPMapViewModel;
import com.vasylprokudin.googlemapsweather.presentation.viewmodel.VPWeatherViewModel;
import com.vasylprokudin.googlemapsweather.presentation.viewmodel.factory.VPViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class VPViewModelModule {

    @Binds
    @IntoMap
    @VPViewModelKey(VPWeatherViewModel.class)
    abstract ViewModel bindWeatherViewModel(VPWeatherViewModel weatherViewModel);

    @Binds
    @IntoMap
    @VPViewModelKey(VPMapViewModel.class)
    abstract ViewModel bindMapViewModel(VPMapViewModel mapViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(VPViewModelFactory factory);
}
