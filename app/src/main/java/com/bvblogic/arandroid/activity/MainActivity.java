package com.bvblogic.arandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.bvblogic.arandroid.R;
import com.bvblogic.arandroid.activity.core.BaseActivity;
import com.bvblogic.arandroid.navigator.NavigatorManager;
import com.bvblogic.arandroid.navigator.core.ResourceManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

@WindowFeature({Window.FEATURE_NO_TITLE})
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Bean
    public NavigatorManager navigatorManager;

    @AfterViews
    public void initView() {
        navigatorManager
                .getMainManager(null)
                .moveFragmentTo(
                        ResourceManager.
                                FragmentId.
                                FIRST_FRAGMENT);
    }
}
