package com.ulling.upstar.view;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.listener.OnSingleClickListener;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentCoinCalculatorBinding;
import com.ulling.upstar.model.CoinCalculator;
import com.ulling.upstar.model.CoinPriceRatio;
import com.ulling.upstar.model.Menu;
import com.ulling.upstar.view.adapter.CalculatorAdapter;

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

    private CalculatorAdapter calculatorAdapter;
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
        coinCalculators = new ArrayList<CoinCalculator>();
        CoinCalculator mCoinCalculator = new CoinCalculator();
        mCoinCalculator.setType(CalculatorAdapter.TYPE_TOP);
        coinCalculators.add(mCoinCalculator);
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

        calculatorAdapter = new CalculatorAdapter(qCon, new QcRecyclerBaseAdapter.QcRecyclerItemListener() {
            @Override
            public void onItemClick(View view, int position, Object o) {
                // 매수
//                viewBinding.nestedScrollView.fullScroll(NestedScrollView.FOCUS_DOWN);
            }

            @Override
            public void onItemLongClick(View view, int position, Object o) {

            }

            @Override
            public void onItemCheck(boolean checked, int position, Object o) {

            }

            @Override
            public void onDeleteItem(int itemPosition, Object o) {
                // 매도
            }

            @Override
            public void onReload() {

            }
        });
        viewBinding.recyclerViewCalculator.setAdapter(calculatorAdapter);
        viewBinding.recyclerViewCalculator.setLayoutManager(new LinearLayoutManager(qCon));
        viewBinding.recyclerViewCalculator.setNestedScrollingEnabled(false);
    }

    @Override
    protected void needUIEventListener() {
        viewBinding.includedItemCalculator.btnInfoExpened.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                isInfoView = !isInfoView;
                if (isInfoView) {
                    viewBinding.includedItemCalculator.layInfo.setVisibility(View.VISIBLE);
//                    viewBinding.includedItemCalculator.recyclerView.setVisibility(View.VISIBLE);
                    viewBinding.includedItemCalculator.btnInfoExpened.animate()
                            .rotation(180)
                            .setInterpolator(new AccelerateInterpolator())
                            .setDuration(200)
                            .start();
//                viewBinding.nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
                    showAlertDialog();
                } else {
                    viewBinding.includedItemCalculator.layInfo.setVisibility(View.GONE);
//                    viewBinding.includedItemCalculator.recyclerView.setVisibility(View.GONE);
                    viewBinding.includedItemCalculator.btnInfoExpened.animate()
                            .rotation(0)
                            .setInterpolator(new AccelerateInterpolator())
                            .setDuration(200)
                            .start();
//                    viewBinding.nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
                    showAlertDialog();
                }
            }
        });
    }

    @Override
    protected void needSubscribeUiFromViewModel() {
        calculatorAdapter.addAll(coinCalculators);
    }

    public void setSubType(Menu menu) {
    }


    private void showAlertDialog() {
        BuyingDialog.show(getActivity(), true, "매수", new BuyingDialog.DialogListener() {
            @Override
            public void onClickOk() {

            }

            @Override
            public void onDismiss() {

            }
        });
    }
}
