package com.ulling.upstar.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.listener.OnSingleClickListener;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentBuyCoinBinding;
import com.ulling.upstar.databinding.FragmentSellCoinBinding;


public class SellCoinFragment extends QcBaseLifeFragment {
    private FragmentSellCoinBinding viewBinding;

    public SellCoinFragment() {
    }

    public static SellCoinFragment getInstance() {
        SellCoinFragment fragment = new SellCoinFragment();
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
        return R.layout.fragment_sell_coin;
    }

    @Override
    protected void needUIBinding() {
        viewBinding = (FragmentSellCoinBinding) getViewDataBinding();
        viewBinding.btnOk.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                hideSoft(viewBinding.editAverage);
                hideSoft(viewBinding.editAmount);
                hideSoft(viewBinding.editPrice);
            }
        });
    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }

    public void hideSoftAll( ) {
        hideSoft(viewBinding.editAverage);
        hideSoft(viewBinding.editAmount);
        hideSoft(viewBinding.editPrice);
    }

    private void hideSoft(EditText editText) {
        InputMethodManager imm = (InputMethodManager) qCon.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
