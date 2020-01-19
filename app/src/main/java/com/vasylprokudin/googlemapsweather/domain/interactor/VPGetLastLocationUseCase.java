package com.vasylprokudin.googlemapsweather.domain.interactor;

import android.location.Location;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;
import com.vasylprokudin.googlemapsweather.domain.interactor.base.VPSingleUseCase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import io.reactivex.Single;

public class VPGetLastLocationUseCase extends VPSingleUseCase<Task<Location>, FusedLocationProviderClient> {

    @Inject
    public VPGetLastLocationUseCase() {}

    @NotNull
    @Override
    public Single<Task<Location>> buildUseCaseSingle(@Nullable FusedLocationProviderClient fusedLocationProviderClient) {
        if (fusedLocationProviderClient != null) {
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            return Single.just(task);
        } else {
            return Single.error(new IllegalStateException("product code must be provided"));
        }
    }
}
