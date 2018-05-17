package com.bvblogic.arandroid.activity.core;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.annimon.stream.Stream;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by hanz on 20.03.2018.
 */

public abstract class BaseActivity
        extends FragmentActivity
        implements ObservableActivity {
    private Observable<String> observable
            = Observable.just(ON_BACK_PRESSED);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void setBackPressedListener(Consumer<String> backPressedListener) {
        backPressedListeners.add(backPressedListener);
    }

    @Override
    public void removeBackPressedListener(Consumer<String> backPressedListener) {
        backPressedListeners.remove(backPressedListener);
    }

    @Override
    public void onBackPressed() {
       if(backPressedListeners.size() > 1){
           callLastFragment();
       } else {
           Stream.of(backPressedListeners).
                   forEach(item -> observable.subscribe(item));
       }
}

    public void callLastFragment() {
        try {
            backPressedListeners.
                    get(backPressedListeners.size() - 1).
                    accept(ON_BACK_PRESSED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
