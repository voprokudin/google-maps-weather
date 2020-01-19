package com.vasylprokudin.googlemapsweather.presentation.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vasylprokudin.googlemapsweather.R;
import com.vasylprokudin.googlemapsweather.base.VPActivity;
import com.vasylprokudin.googlemapsweather.presentation.viewmodel.VPMapViewModel;
import org.jetbrains.annotations.NotNull;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

public class VPMapActivity extends VPActivity implements OnMapReadyCallback {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private static final int REQUEST_CODE = 101;

    private VPMapViewModel viewModel;

    private Location currentLocation;

    private FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        viewModel = provideViewModel();
        observeViewModel(viewModel);
        checkPermissions();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
    }

    private void fetchLastLocation() {
        showProgressBar();
        viewModel.fetchLastLocation(fusedLocationProviderClient);
    }

    @NotNull
    public final VPMapViewModel provideViewModel() {
        VPMapViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(VPMapViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ctory).get(T::class.java)");
        return viewModel;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Your location");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
        googleMap.addMarker(markerOptions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        viewModel.checkIfShouldFetchLastLocation(requestCode, grantResults);
    }

    private void observeViewModel(VPMapViewModel viewModel) {
        viewModel.getCurrentLocationObservable().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                if (location != null) {
                    currentLocation = location;
                    viewModel.showContent(currentLocation);
                }
            }
        });
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_CODE);
        }
    }
}

