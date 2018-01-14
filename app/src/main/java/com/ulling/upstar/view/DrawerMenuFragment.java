package com.ulling.upstar.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.upstar.R;

public class DrawerMenuFragment extends QcBaseLifeFragment {

    private static DrawerLayout drawer;

    public DrawerMenuFragment() {
    }

    public static DrawerMenuFragment getInstance(DrawerLayout drawer_) {
        DrawerMenuFragment fragment = new DrawerMenuFragment();
        drawer = drawer_;
        return fragment;
    }


    @Override
    protected void needInitToOnCreate() {

    }

    @Override
    protected void needResetData() {
//        if (drawer != null)
//            drawer.closeDrawers();

    }

    @Override
    protected void needInitViewModel() {

    }

    @Override
    protected int needGetLayoutId() {
        return R.layout.fragment_drawer_menu;
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

