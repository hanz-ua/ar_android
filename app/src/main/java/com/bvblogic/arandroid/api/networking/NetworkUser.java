package com.bvblogic.arandroid.api.networking;

import com.bvblogic.arandroid.api.model.core.Head;
import com.bvblogic.arandroid.api.networking.core.Service;
import com.bvblogic.arandroid.api.networking.error.NetworkError;
import com.bvblogic.arandroid.api.service.User;
import com.google.common.eventbus.Subscribe;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by hanz on 14.04.2018.
 */

public class NetworkUser extends Service<User> {
    public NetworkUser(User networkService) {
        super(networkService);
    }

    public Subscription getUser(String userName,
                                Callback<
                    com.bvblogic.arandroid.api.model.User>
                                        headCallback) {
        return networkService.getUser(userName)
                .subscribeOn(Schedulers.newThread())
               // .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable::error)
                .subscribe(
                        new Subscriber
                                <Head<com.bvblogic.arandroid.api.model.User>>() {
                            @Override
                            public void onCompleted() {
                            }
                            @Override
                            public void onError(Throwable e) {
                                headCallback.onError(new NetworkError(e));}
                            @Override
                            public void onNext(Head<com.bvblogic.arandroid.api.model.User> userHead) {
                                headCallback.onSucces(userHead.getT());
                            }
                        });

    }


}
