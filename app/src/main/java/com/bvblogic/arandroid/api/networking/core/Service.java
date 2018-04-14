package com.bvblogic.arandroid.api.networking.core;

import com.bvblogic.arandroid.api.networking.error.NetworkError;

/**
 * Created by hanz on 14.04.2018.
 */

public abstract class Service<T> {
    protected final T networkService;

    public Service(T networkService) {
        this.networkService = networkService;
    }

    public interface Callback<T>{
        void onSucces(T t);
        void onError(NetworkError networkError);
    }
}
