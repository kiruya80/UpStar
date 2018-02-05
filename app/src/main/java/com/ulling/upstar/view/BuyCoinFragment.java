package com.ulling.upstar.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.listener.OnSingleClickListener;
import com.ulling.lib.core.util.QcLog;
import com.ulling.lib.core.util.QcTextUtils;
import com.ulling.lib.core.util.QcUtils;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentBuyCoinBinding;


public class  BuyCoinFragment extends QcBaseLifeFragment {
    private FragmentBuyCoinBinding viewBinding;

    public BuyCoinFragment() {
    }

    public static BuyCoinFragment getInstance() {
        BuyCoinFragment fragment = new BuyCoinFragment();
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
        return R.layout.fragment_buy_coin;
    }

    @Override
    protected void needUIBinding() {
        viewBinding = (FragmentBuyCoinBinding) getViewDataBinding();
        viewBinding.btnOk.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                hideSoftAll();
                QcLog.e("editAverage == " + viewBinding.editAverage.getText().toString());
                QcLog.e("editAmount == " + viewBinding.editAmount.getText().toString());
                QcLog.e("editPrice == " + viewBinding.editPrice.getText().toString());
            }
        });

        viewBinding.editAverage.addTextChangedListener(new QcTextUtils.NumberTextWatcher(viewBinding.editAverage));
        viewBinding.editAmount.addTextChangedListener(new QcTextUtils.NumberTextWatcher(viewBinding.editAmount));
        viewBinding.editPrice.addTextChangedListener(new QcTextUtils.NumberTextWatcher(viewBinding.editPrice));

    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }

    @Override
    public void onPause() {
        super.onPause();
//        viewBinding.editAverage.setText("");
//        viewBinding.editAmount.setText("");
//        viewBinding.editPrice.setText("");
        hideSoftAll();
    }

    public void hideSoftAll( ) {
        QcUtils.hideSoftKeyboard(qCon, viewBinding.editAverage);
        QcUtils.hideSoftKeyboard(qCon, viewBinding.editAmount);
        QcUtils.hideSoftKeyboard(qCon, viewBinding.editPrice);
    }

}
