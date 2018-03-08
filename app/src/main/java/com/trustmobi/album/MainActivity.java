package com.trustmobi.album;

import android.os.Bundle;

import com.trustmobi.album.fragment.HomeFragment;
import com.trustmobi.componentlib.base.BaseFragment;
import com.trustmobi.componentlib.base.BaseFragmentActivity;


public class MainActivity extends BaseFragmentActivity {

    @Override
    protected int getContextViewId() {
        return R.id.trustmobi_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            BaseFragment fragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }





}
