package com.bvblogic.arandroid.navigator;


import android.support.v4.app.FragmentTransaction;

import com.bvblogic.arandroid.R;
import com.bvblogic.arandroid.activity.core.BaseActivity;
import com.bvblogic.arandroid.fragment.FirstFragment_;
import com.bvblogic.arandroid.fragment.SecondFragment_;
import com.bvblogic.arandroid.fragment.core.BaseFragment;
import com.bvblogic.arandroid.navigator.core.BaseManager;
import com.bvblogic.arandroid.navigator.core.ResourceManager;
import com.bvblogic.arandroid.navigator.core.ResourceNames;

/**
 * Created by hanz on 24.03.2018.
 */

class ManagerMain extends BaseManager {
    public ManagerMain(BaseFragment baseFragment, BaseActivity baseActivity) {
        super(baseFragment, baseActivity);
    }

    @Override
    public void moveFragmentTo(int id, Object... o) {
        FragmentTransaction fragmentTransaction = baseActivity.getSupportFragmentManager().
                beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        switch (id) {
            case ResourceManager.FragmentId.FIRST_FRAGMENT:
                fragmentTransaction.replace(R.id.fragment_container,
                        FirstFragment_.builder().build(),
                        ResourceNames.FIRST_FRAGMENT)
                        .commit();
                break;
            case ResourceManager.FragmentId.SECOND_FRAGMENT:
                fragmentTransaction.add(R.id.fragment_container,
                        SecondFragment_.builder().arg("key", o.length >= 1 ? (String) o[0] : null).build(),
                        ResourceNames.SECOND_FRAGMENT)
                        .commit();
                break;
        }
    }

    @Override
    public void removeFragment() {
        if (baseFragment.getActivity() != null)
            baseFragment.getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                    .remove(baseFragment)
                    .commit();
    }
}
