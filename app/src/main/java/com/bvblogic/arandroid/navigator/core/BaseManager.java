package com.bvblogic.arandroid.navigator.core;

import android.view.View;
import android.widget.TextView;

import com.bvblogic.arandroid.R;
import com.bvblogic.arandroid.activity.core.BaseActivity;
import com.bvblogic.arandroid.fragment.core.BaseFragment;


/**
 * Created by hanz on 24.03.2018.
 */

public abstract class BaseManager implements Manager {
    protected BaseFragment baseFragment;
    protected BaseActivity baseActivity;

    public BaseManager(BaseFragment baseFragment, BaseActivity baseActivity) {
        this.baseFragment = baseFragment;
        this.baseActivity = baseActivity;
    }

    @Override
    public void initToolbar(int id, int... text) {
        View inflate = null;
        switch (id) {
            case ResourceManager.ToolbarId.SIMPLE:
                inflate = baseActivity
                        .getLayoutInflater()
                        .inflate(R.layout.toolbar_simple,
                                null, false);

                if (text.length > 0) {
                    ((TextView) inflate.
                            findViewById(R.id.text))
                            .setText(text[0]);

                    inflate.findViewById(R.id.text)
                            .setOnClickListener(
                                    v -> baseFragment.clickToolbar()
                            );
                }
                break;
        }

        //add to view1
        baseFragment.getToolbarContainer().removeAllViews();
        baseFragment.getToolbarContainer().addView(inflate);

    }
}
