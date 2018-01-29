package com.ulling.upstar.view;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.listener.OnSingleClickListener;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentCoinCalculatorBinding;
import com.ulling.upstar.model.CoinCalculator;
import com.ulling.upstar.model.CoinPriceRatio;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;

/**
 * 메뉴
 * ㄴ 자산관리
 */
public class CoinCalculatorFragment extends QcBaseLifeFragment {

    private FragmentCoinCalculatorBinding viewBinding;
    private boolean isInfoView = false;
    // 코인 정보
    private CoinPriceRatio coinPriceRatio;

    private ArrayList<CoinCalculator> coinCalculators;

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
        viewBinding.includedItemCalculator.btnInfoExpened.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                isInfoView = !isInfoView;
                if (isInfoView) {
                    viewBinding.includedItemCalculator.layInfo.setVisibility(View.VISIBLE);
                    viewBinding.includedItemCalculator.recyclerView.setVisibility(View.VISIBLE);
                    viewBinding.includedItemCalculator.btnInfoExpened.animate()
                            .rotation(180)
                            .setInterpolator(new AccelerateInterpolator())
                            .setDuration(200)
                            .start();
                } else {
                    viewBinding.includedItemCalculator.layInfo.setVisibility(View.GONE);
                    viewBinding.includedItemCalculator.recyclerView.setVisibility(View.GONE);
                    viewBinding.includedItemCalculator.btnInfoExpened.animate()
                            .rotation(0)
                            .setInterpolator(new AccelerateInterpolator())
                            .setDuration(200)
                            .start();
                }
            }
        });


//        viewBinding.includedItemCalculatorAdd.btnConfirm.setOnClickListener(new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View v) {
//                viewBinding.includedItemCalculatorResult.getRoot().setVisibility(View.VISIBLE);
//                viewBinding.nestedScrollView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        viewBinding.nestedScrollView.fullScroll(NestedScrollView.FOCUS_DOWN);
//                    }
//                });
//
//            }
//        });
//
//        viewBinding.includedItemCalculatorAdd.btnCancle.setOnClickListener(new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View v) {
//                viewBinding.includedItemCalculatorResult.getRoot().setVisibility(View.GONE);
//                viewBinding.nestedScrollView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        viewBinding.nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
//                    }
//                });
//            }
//        });

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }

    public void setSubType(Menu menu) {
    }


    private void showAlertDialog() {
        BuyingDialog.show(getActivity(), true, new BuyingDialog.DialogListener() {
            @Override
            public void onClickOk() {

            }

            @Override
            public void onDismiss() {

            }
        });
    }
}
