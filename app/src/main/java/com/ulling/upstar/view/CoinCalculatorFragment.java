package com.ulling.upstar.view;

import android.os.Bundle;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentCoinCalculatorBinding;
import com.ulling.upstar.model.Menu;

/**
 * 메뉴
 * ㄴ 자산관리
 */
public class CoinCalculatorFragment extends QcBaseLifeFragment {

    private FragmentCoinCalculatorBinding viewBinding;

    public CoinCalculatorFragment() {
    }

    public static CoinCalculatorFragment getInstance() {
        CoinCalculatorFragment fragment = new CoinCalculatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setSubType(int subType) {
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
        return R.layout.fragment_coin_calculator;
    }

    @Override
    protected void needUIBinding() {
        viewBinding = (FragmentCoinCalculatorBinding) getViewDataBinding();

    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }

    public void setSubType(Menu menu) {
    }
}
