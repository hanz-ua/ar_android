package com.bvblogic.arandroid.navigator.core;

/**
 * Created by hanz on 24.03.2018.
 */

public interface Manager {

    void moveFragmentTo(int id, Object... o);

    void removeFragment();

    void initToolbar(int id, int... text);
}
