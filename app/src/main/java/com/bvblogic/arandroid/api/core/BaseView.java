package com.bvblogic.arandroid.api.core;

/**
 * Created by hanz on 14.04.2018.
 */

public interface BaseView<T> {
    void onFailure(String error);

    void onSuccess(T t);

    void showWait();

    void closeWait();
}
