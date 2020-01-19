package com.vasylprokudin.googlemapsweather.util.fragment;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.vasylprokudin.googlemapsweather.base.VPFragment;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VPFragmentUtil {

    @Inject
    public VPFragmentUtil(){}

    public void replaceFragmentAllowingStateLoss(
            FragmentManager fragmentManager,
            VPFragment fragment,
            @IdRes Integer containerViewId,
            Boolean addToBackStack
    ) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }
}
