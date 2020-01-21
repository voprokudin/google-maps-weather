package com.vasylprokudin.googlemapsweather.presentation.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vasylprokudin.googlemapsweather.R;
import com.vasylprokudin.googlemapsweather.base.VPFragment;
import com.vasylprokudin.googlemapsweather.data.model.VPWeatherInfoModel;
import com.vasylprokudin.googlemapsweather.presentation.view.list.adapter.VPWeatherAdapter;
import com.vasylprokudin.googlemapsweather.presentation.viewmodel.VPWeatherViewModel;
import com.vasylprokudin.googlemapsweather.util.VPDateFormatter;
import com.vasylprokudin.googlemapsweather.util.VPTemperatureConverter;
import com.vasylprokudin.googlemapsweather.util.VPWeatherIconProvider;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import kotlin.jvm.internal.Intrinsics;
import static com.vasylprokudin.googlemapsweather.C.BundleArgs.USER_LATITUDE;
import static com.vasylprokudin.googlemapsweather.C.BundleArgs.USER_LONGITUDE;

public class VPWeatherFragment extends VPFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    VPTemperatureConverter temperatureConverter;

    @Inject
    VPWeatherIconProvider weatherIconProvider;

    @Inject
    VPDateFormatter dateFormatter;

    @Inject
    Context context;

    @BindView(R.id.weatherRecyclerView)
    RecyclerView recyclerView;

    private VPWeatherViewModel viewModel;

    public static VPWeatherFragment newInstance(double latitude, double longitude) {
        VPWeatherFragment fragment = new VPWeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(USER_LATITUDE, latitude);
        bundle.putDouble(USER_LONGITUDE, longitude);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getGetLayoutResId() {
        return R.layout.vp_fragment_weather;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        fetchWeather();
    }

    private void fetchWeather() {
        if (getArguments() != null) {
            double latitude = getArguments().getDouble(USER_LATITUDE);
            double longitude = getArguments().getDouble(USER_LONGITUDE);

            List<Double> coordinates = new ArrayList<>();
            coordinates.add(latitude);
            coordinates.add(longitude);
            viewModel.fetchWeather(coordinates);
        }
    }

    private void setupViewModel() {
        viewModel = provideViewModel();
        observeViewModel(viewModel);
    }

    private void observeViewModel(VPWeatherViewModel viewModel) {
        viewModel.getVPWeatherObservable().observe(this, new Observer<VPWeatherInfoModel>() {
            @Override
            public void onChanged(VPWeatherInfoModel weatherInfoModel) {
                if (weatherInfoModel != null) {
                    initRecyclerView(weatherInfoModel);
                    hideProgressBar();
                } else {
                    hideProgressBar();
                }
            }
        });
    }

    private void initRecyclerView(VPWeatherInfoModel weatherInfoModel) {
        VPWeatherAdapter weatherListAdapter = new VPWeatherAdapter(
                weatherInfoModel,
                temperatureConverter,
                dateFormatter,
                weatherIconProvider,
                context
        );
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(weatherListAdapter);
    }

    @NotNull
    private VPWeatherViewModel provideViewModel() {
        VPWeatherViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(VPWeatherViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ctory).get(T::class.java)");
        return viewModel;
    }
}
