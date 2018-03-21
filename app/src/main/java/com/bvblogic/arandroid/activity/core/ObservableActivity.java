package com.bvblogic.arandroid.activity.core;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by hanz on 20.03.2018.
 */

public interface ObservableActivity {
    String ON_BACK_PRESSED = "OnBackPressed";
    List<Consumer<String>> backPressedListeners = new ArrayList<>();

    void setBackPressedListener(Consumer<String> backPressedListener);

    void removeBackPressedListener(Consumer<String> backPressedListener);


}

