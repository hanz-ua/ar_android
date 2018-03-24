package com.bvblogic.arandroid.navigator;

import com.bvblogic.arandroid.R;
import com.bvblogic.arandroid.activity.core.BaseActivity;
import com.bvblogic.arandroid.fragment.FirstFragment_;
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
        switch (id) {
            case ResourceManager.FragmentId.FIRST_FRAGMENT:
                baseActivity.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                FirstFragment_.builder().build(),
                                ResourceNames.FIRST_FRAGMENT)
                        .commit();
                break;
        }
    }

    @Override
    public void removeFragment() {
        baseFragment.getActivity().getFragmentManager()
                .beginTransaction().remove(baseFragment)
                .commit();
    }
}
