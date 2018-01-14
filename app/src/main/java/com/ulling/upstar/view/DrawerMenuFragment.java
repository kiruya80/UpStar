package com.ulling.upstar.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentDrawerMenuBinding;
import com.ulling.upstar.databinding.FragmentMarketPriceBinding;
import com.ulling.upstar.view.adapter.MyItemRecyclerViewAdapter;
import com.ulling.upstar.view.dummy.DummyContent;

public class DrawerMenuFragment extends QcBaseLifeFragment {

    private static DrawerLayout drawer;
    private FragmentDrawerMenuBinding viewBinding;

    private MarketPriceFragment.OnListFragmentInteractionListener mListener;

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyContent.DummyItem item);
    }

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
    protected void optGetArgument(Bundle savedInstanceState) {
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
        viewBinding = (FragmentDrawerMenuBinding) getViewDataBinding();
        viewBinding.recyclerView.setLayoutManager(new GridLayoutManager(qCon, 1));
        viewBinding.recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));

    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }
}

