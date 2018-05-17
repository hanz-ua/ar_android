package com.bvblogic.arandroid.fragment;

import android.widget.Toast;

import com.bvblogic.arandroid.R;
import com.bvblogic.arandroid.fragment.core.BaseFragment;
import com.bvblogic.arandroid.navigator.core.ResourceManager;
import com.bvblogic.arandroid.navigator.core.ResourceNames;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by hanz on 17.05.2018.
 */

@EFragment(R.layout.fragment_second)
public class SecondFragment extends BaseFragment {

    @FragmentArg("key")
    String text;

    @AfterViews
    public void initData() {
        FirstFragment firstFragment = (FirstFragment) getFragmentManager().findFragmentByTag(ResourceNames.FIRST_FRAGMENT);
        if (firstFragment != null) {
            firstFragment.updateText(text);
        }
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void backPressed() {
        navigatorManager.getMainManager(this).removeFragment();
    }
}
