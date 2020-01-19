package com.vasylprokudin.googlemapsweather.base;

import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.vasylprokudin.googlemapsweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

abstract public class VPActivity extends DaggerAppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarProgressBar)
    MaterialProgressBar toolbarProgressBar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        hideProgressBar();
    }

    public void hideProgressBar() {
        toolbarProgressBar.setVisibility(View.GONE);
    }

    public void showProgressBar() {
        toolbarProgressBar.setVisibility(View.VISIBLE);
    }
}

