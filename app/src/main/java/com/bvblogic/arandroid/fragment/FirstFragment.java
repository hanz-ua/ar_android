package com.bvblogic.arandroid.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.bumptech.glide.request.RequestOptions;
import com.bvblogic.arandroid.R;
import com.bvblogic.arandroid.fragment.core.BaseFragment;
import com.bvblogic.arandroid.navigator.core.ResourceManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hanz on 24.03.2018.
 */

@EFragment(R.layout.fragment_first)
public class FirstFragment extends BaseFragment {

    @Override
    public void backPressed() {
        super.backPressed();
    }


    @AfterViews
    public void initToolbar() {
        navigatorManager.
                getMainManager(this).
                initToolbar(ResourceManager.ToolbarId.SIMPLE,
                        R.string.app_name);
    }

}
