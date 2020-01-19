package com.vasylprokudin.googlemapsweather.presentation.navigation;

import androidx.fragment.app.FragmentManager;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.vasylprokudin.googlemapsweather.R;
import com.vasylprokudin.googlemapsweather.base.VPActivity;
import com.vasylprokudin.googlemapsweather.presentation.view.VPWeatherFragment;
import com.vasylprokudin.googlemapsweather.util.fragment.VPFragmentUtil;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VPWeatherNavigator {

    private VPFragmentUtil fragmentUtil;

    private VPActivity activity;

    private static final Integer FRAGMENT_CONTAINER = R.id.weatherFragmentContainer;

    private FragmentManager fragmentManager;

    @Inject
    public VPWeatherNavigator(VPActivity activity, VPFragmentUtil fragmentUtil) {
        this.fragmentUtil = fragmentUtil;
        this.activity = activity;

        fragmentManager = activity.getSupportFragmentManager();
    }

    public void showWeatherFragment(double latitude, double longitude) {
        fragmentUtil.replaceFragmentAllowingStateLoss(
                fragmentManager,
                VPWeatherFragment.newInstance(latitude, longitude),
                FRAGMENT_CONTAINER,
                false
        );
    }

    public void showMapFragment() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.mapFragment);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync((OnMapReadyCallback) activity);
        }
    }
}
