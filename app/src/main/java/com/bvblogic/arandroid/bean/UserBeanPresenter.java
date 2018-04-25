package com.bvblogic.arandroid.bean;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bvblogic.arandroid.R;
import com.bvblogic.arandroid.api.model.User;
import com.bvblogic.arandroid.api.presenter.UserPresenter;
import com.bvblogic.arandroid.bean.core.UserBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hanz on 17.04.2018.
 */

@EBean
public class UserBeanPresenter extends UserBean {

    @ViewById(R.id.text)
    TextView textView;

    @Override
    public void init() {
        super.init();
        getUserDeps().inject(this);
    }

    @AfterViews
    public void getUser() {
        new UserPresenter(networkUser,
                this).getUser("Yura");
    }

    @Override
    public void onSuccess(User user) {
        textView.setText("user = " + user.getName());
        Log.d("onSuccess", user.getName());
        Toast.makeText(baseActivity, user.getName(),
                Toast.LENGTH_SHORT).show();
    }
}
