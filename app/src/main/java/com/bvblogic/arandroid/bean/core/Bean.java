package com.bvblogic.arandroid.bean.core;

import android.util.Log;
import android.widget.Toast;

import com.bvblogic.arandroid.activity.core.BaseActivity;
import com.bvblogic.arandroid.api.core.BaseView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by hanz on 17.04.2018.
 */

@EBean
public abstract class Bean<T> implements BaseView<T> {

    @RootContext
    public BaseActivity baseActivity;


    @Override
    public void onFailure(String error) {
        Log.d("Error", error);
    }

    @Override
    public void onSuccess(T t) {
    }

    @Override
    public void showWait() {
    }

    @Override
    public void closeWait() {
    }
}
