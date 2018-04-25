package com.bvblogic.arandroid.api.presenter.core;

import com.bvblogic.arandroid.api.core.BaseView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by hanz on 14.04.2018.
 */

public abstract class Presenter<T, Y> {

    protected final T service;
    protected final BaseView<Y> yBaseView;
    protected CompositeDisposable subscription;

    public Presenter(T service, BaseView<Y> yBaseView) {
        this.service = service;
        this.yBaseView = yBaseView;
        this.subscription = new CompositeDisposable();
    }

    protected void onStop(){
        subscription.dispose();
    }
}
