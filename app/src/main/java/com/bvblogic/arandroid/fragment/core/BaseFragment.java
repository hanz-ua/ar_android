package com.bvblogic.arandroid.fragment.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bvblogic.arandroid.R;
import com.bvblogic.arandroid.activity.core.BaseActivity;
import com.bvblogic.arandroid.activity.core.OnBackPressedListener;
import com.bvblogic.arandroid.navigator.NavigatorManager;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import io.reactivex.functions.Consumer;


/**
 * Created by hanz on 24.03.2018.
 */

@EFragment
public abstract class BaseFragment
        extends Fragment
        implements OnBackPressedListener {

    @Bean
    protected NavigatorManager navigatorManager;

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    @ViewById(R.id.toolbarContainer)
    protected RelativeLayout toolbarContainer;

    public void clickToolbar() {
        Toast.makeText(getActivity(),
                "2379975389733289",
                Toast.LENGTH_SHORT).show();
    }

    public RelativeLayout getToolbarContainer() {
        return toolbarContainer;
    }

    private Consumer<String> onNextAction = s -> backPressed();

    @Override
    public void backPressed() {
        getActivity().finish();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToActivity();
    }

    protected void onAttachToActivity() {
        getBaseActivity().setBackPressedListener(onNextAction);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onAttachToActivity();
    }

    @Override
    public void onDetach() {
        getBaseActivity().removeBackPressedListener(onNextAction);
        super.onDetach();
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}
