package com.ulling.upstar.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentCoinCalculatorBinding;

public class CoinCalculatorFragment extends QcBaseLifeFragment {

    private FragmentCoinCalculatorBinding viewBinding;

    public CoinCalculatorFragment() {
    }

    public static CoinCalculatorFragment getInstance() {
        CoinCalculatorFragment fragment = new CoinCalculatorFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void needInitToOnCreate() {

//        viewBinding.includedAppBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    @Override
    protected void needResetData() {

    }

    @Override
    protected void needInitViewModel() {

    }

    @Override
    protected int needGetLayoutId() {
        return R.layout.fragment_coin_calculator;
    }

    @Override
    protected void needUIBinding() {
        viewBinding = (FragmentCoinCalculatorBinding) getViewDataBinding();
//        setToolbar(viewBinding.toolbar);
//        actionBar.setTitle(getResources().getString(R.string.menu_coin_calculator));
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }
}
