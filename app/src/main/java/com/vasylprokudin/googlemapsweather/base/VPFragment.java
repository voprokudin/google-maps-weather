package com.vasylprokudin.googlemapsweather.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public abstract class VPFragment extends DaggerFragment {

    protected abstract int getGetLayoutResId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getGetLayoutResId(), container, false);
//        ButterKnife.bind(this, view);
        return view;
    }

    public void showProgressBar() {
        getVPActivity().showProgressBar();
    }

    public void hideProgressBar() {
        getVPActivity().hideProgressBar();
    }

    public VPActivity getVPActivity() {
        return ((VPActivity) getActivity());
    }
}
