package com.bvblogic.arandroid.bean.core;

import com.bvblogic.arandroid.api.deps.DaggerUserDeps;
import com.bvblogic.arandroid.api.deps.UserDeps;
import com.bvblogic.arandroid.api.model.User;
import com.bvblogic.arandroid.api.networking.NetworkUser;
import com.bvblogic.arandroid.api.networking.module.NetworkModule;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by hanz on 17.04.2018.
 */

@EBean
public abstract class UserBean extends Bean<User> {

    private UserDeps userDeps;

    public UserDeps getUserDeps() {
        return userDeps;
    }

    @Inject
    public NetworkUser networkUser;

    @AfterInject
    public void init() {
        userDeps = DaggerUserDeps.builder().
                networkModule(
                        new NetworkModule(
                                new File(
                                        baseActivity.getCacheDir(),
                                        "responses"),
                                "http://httpbin.org/")).build();
    }
}
