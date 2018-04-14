package com.bvblogic.arandroid.api.presenter;

import com.bvblogic.arandroid.api.core.BaseView;
import com.bvblogic.arandroid.api.model.User;
import com.bvblogic.arandroid.api.networking.NetworkUser;
import com.bvblogic.arandroid.api.networking.core.Service;
import com.bvblogic.arandroid.api.networking.error.NetworkError;
import com.bvblogic.arandroid.api.presenter.core.Presenter;


import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hanz on 14.04.2018.
 */

public class UserPresenter extends Presenter<NetworkUser, User> {
    public UserPresenter(NetworkUser service,
                         BaseView<User> userBaseView) {
        super(service, userBaseView);
    }

    public void getUser(String user) {
        yBaseView.showWait();
        Subscription subscription =
                service.getUser(user, new Service.Callback<User>() {
                    @Override
                    public void onSucces(User user) {
                        yBaseView.closeWait();
                        yBaseView.onSuccess(user);
                        onStop();
                    }

                    @Override
                    public void onError(NetworkError networkError) {
                        yBaseView.closeWait();
                        yBaseView.onFailure(networkError.getAppErrorMessage());
                        onStop();
                    }
                });
        super.subscription.add(subscription);
    }
}
