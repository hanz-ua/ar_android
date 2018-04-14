package com.bvblogic.arandroid.api.service;

import com.bvblogic.arandroid.api.model.core.Head;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hanz on 14.04.2018.
 */

public interface User {


    @GET("get")
    Observable<Head<com.bvblogic.arandroid.api.model.User>>
    getUser(@Query("name") String name);

}
