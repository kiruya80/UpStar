package com.ulling.upstar.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
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
import com.ulling.upstar.databinding.FragmentSellCoinBinding;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


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
                hideSoftAll();
                QcLog.e("editAverage == " + viewBinding.editAverage.getText().toString());
                QcLog.e("editAmount == " + viewBinding.editAmount.getText().toString());
                QcLog.e("editPrice == " + viewBinding.editPrice.getText().toString());
            }
        });

        viewBinding.btnChange.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                hideSoftAll();

            }
        });

        // 소수점 입력
        // 세자리 자리수 찍기

        // 뒤에 단위 찍기
        // 커서 이동 자유롭게

        // 저장은 숫자로 저장 - 마지막 단위 , 제외 하고 저장
        viewBinding.editAverage.addTextChangedListener(new QcTextUtils.NumberTextWatcher(viewBinding.editAverage));
//        viewBinding.editAmount.addTextChangedListener(new QcTextUtils.NumberTextWatcher(viewBinding.editAmount));
        viewBinding.editPrice.addTextChangedListener(new QcTextUtils.NumberTextWatcher(viewBinding.editPrice));

        viewBinding.editPrice.addTextChangedListener(new QcTextUtils.NumberTextWatcher(viewBinding.editPrice,
                new QcTextUtils.NumberTextWatcher.OnTextWatcherListener() {
                    @Override
                    public void onAfterTextChanged(String numberStr) {
                        if (!"".equals(numberStr)) {
                            long average = Long.parseLong(viewBinding.editAverage.getText().toString().replaceAll(",", ""));
                            viewBinding.textTotalAmount.setText((Long.parseLong(numberStr) / average) + "");
                        }
                    }
                }));

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
