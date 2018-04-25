package com.bvblogic.arandroid.api.networking;

import com.bvblogic.arandroid.api.model.core.Head;
import com.bvblogic.arandroid.api.networking.core.Service;
import com.bvblogic.arandroid.api.networking.error.NetworkError;
import com.bvblogic.arandroid.api.service.User;
import com.google.common.eventbus.Subscribe;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hanz on 14.04.2018.
 */

public class NetworkUser extends Service<User> {
    public NetworkUser(User networkService) {
        super(networkService);
    }

    public Disposable getUser(String userName,
                              Callback<
                                      com.bvblogic.arandroid.api.model.User>
                                      headCallback) {
        return networkService.getUser(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith((new DisposableObserver
                        <Head<com.bvblogic.arandroid.api.model.User>>() {

                    @Override
                    public void onNext(Head
                                               <com.bvblogic.arandroid.api.model.User> t) {
                        headCallback.onSucces(t.getT());
                    }

                    @Override
                    public void onError(Throwable e) {
                        headCallback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onComplete() {
                    }
                }
                ));

    }


}
