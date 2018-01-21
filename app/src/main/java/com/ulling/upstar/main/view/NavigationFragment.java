package com.ulling.upstar.main.view;

import android.os.Bundle;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.upstar.R;

public class NavigationFragment extends QcBaseLifeFragment {

    public NavigationFragment() {
    }

    public static NavigationFragment getInstance() {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void needInitToOnCreate() {

    }

    @Override
    protected void needResetData() {

    }

    @Override
    protected void needInitViewModel() {

    }

    @Override
    protected int needGetLayoutId() {
        return R.layout.fragment_talk;
    }

    @Override
    protected void needUIBinding() {

    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }
}
