package com.bvblogic.arandroid.navigator;

import com.bvblogic.arandroid.fragment.core.BaseFragment;
import com.bvblogic.arandroid.navigator.core.Manager;
import com.bvblogic.arandroid.navigator.core.NavigatorBaseManager;

import org.androidannotations.annotations.EBean;

/**
 * Created by hanz on 24.03.2018.
 */

@EBean
public class NavigatorManager extends NavigatorBaseManager {

    @Override
    public Manager getMainManager(BaseFragment baseFragment) {
        return new ManagerMain(baseFragment, baseActivity);
    }
}
