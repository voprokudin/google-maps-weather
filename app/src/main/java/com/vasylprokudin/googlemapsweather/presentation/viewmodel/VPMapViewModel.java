package com.vasylprokudin.googlemapsweather.presentation.viewmodel;

import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.vasylprokudin.googlemapsweather.domain.interactor.VPGetLastLocationUseCase;
import com.vasylprokudin.googlemapsweather.domain.interactor.base.VPEmptySingleObserver;
import com.vasylprokudin.googlemapsweather.presentation.navigation.VPWeatherNavigator;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class VPMapViewModel extends ViewModel {

    private static final int REQUEST_CODE = 101;

    @Inject
    VPMapViewModel() {}

    @Inject
    VPWeatherNavigator navigator;

    @Inject
    VPGetLastLocationUseCase getLastLocationUseCase;

    private final MutableLiveData<Location> mutableLiveData = new MutableLiveData<>();

    private FusedLocationProviderClient fusedLocationProviderClient;

    public void fetchLastLocation(FusedLocationProviderClient fusedLocationProviderClient) {
        this.fusedLocationProviderClient = fusedLocationProviderClient;
        getLastLocationUseCase.execute(new GetLastLocationObserver(), fusedLocationProviderClient);
    }

    public LiveData<Location> getCurrentLocationObservable() {
        return mutableLiveData;
    }

    public void checkIfShouldFetchLastLocation(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLastLocation(fusedLocationProviderClient);
            }
        }
    }

    public void showContent(Location currentLocation) {
        navigator.showMapFragment();
        navigator.showWeatherFragment(currentLocation.getLatitude(), currentLocation.getLongitude());
    }

    class GetLastLocationObserver extends VPEmptySingleObserver<Task<Location>> {

        @Override
        public void onSuccess(Task<Location> task) {
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mutableLiveData.setValue(location);
                    }
                }
            });
        }

        @Override
        public void onError(@NotNull Throwable throwable) {
            mutableLiveData.setValue(null);
        }
    }
}
