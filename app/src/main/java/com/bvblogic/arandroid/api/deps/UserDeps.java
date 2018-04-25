package com.bvblogic.arandroid.api.deps;

import com.bvblogic.arandroid.api.networking.module.NetworkModule;
import com.bvblogic.arandroid.bean.UserBeanPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hanz on 17.04.2018.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface UserDeps {

    void inject(UserBeanPresenter userBeanPresenter);
}
