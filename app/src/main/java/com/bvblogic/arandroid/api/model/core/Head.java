package com.bvblogic.arandroid.api.model.core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hanz on 14.04.2018.
 */

public class Head<T> {

    @SerializedName("args")
    @Expose
    private T t;

    public Head() {
    }

    public Head(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
