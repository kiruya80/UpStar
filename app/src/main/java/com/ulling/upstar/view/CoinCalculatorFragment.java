package com.ulling.upstar.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.listener.OnSingleClickListener;
import com.ulling.lib.core.util.QcActivityUtils;
import com.ulling.lib.core.util.QcLog;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentCoinCalculatorBinding;
import com.ulling.upstar.model.CoinCalculator;
import com.ulling.upstar.model.CoinPriceRatio;
import com.ulling.upstar.model.Menu;
import com.ulling.upstar.view.adapter.CalculatorAdapter;
import com.ulling.upstar.view.adapter.TabPagerAdapter;

import java.util.ArrayList;

/**
 * 메뉴
 * ㄴ 자산관리
 */
public class CoinCalculatorFragment extends QcBaseLifeFragment {

    public static final int TAB_TYPE_BUY = 0;
    public static final int TAB_TYPE_SELL = 1;

    private FragmentCoinCalculatorBinding viewBinding;
    private boolean isInfoView = false;
    // 코인 정보
    private CoinPriceRatio coinPriceRatio;

    private CalculatorAdapter calculatorAdapter;
    private ArrayList<CoinCalculator> coinCalculators;
    private BuyCoinFragment mBuyCoinFragment;
    private SellCoinFragment mSellCoinFragment;

    private int tabType = TAB_TYPE_BUY;


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
        for (int i = 0; i < 20; i++) {
            coinCalculators.add(mCoinCalculator);
        }
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

        viewBinding.btnBuy.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (tabType != TAB_TYPE_BUY) {
                    tabType = TAB_TYPE_BUY;
                    mSellCoinFragment.hideSoftAll();
                    setFragment();
                }
            }
        });
        viewBinding.btnSell.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (tabType != TAB_TYPE_SELL) {
                    tabType = TAB_TYPE_SELL;
                    mBuyCoinFragment.hideSoftAll();
                    setFragment();
                }
            }
        });

        mBuyCoinFragment = new BuyCoinFragment();
        mSellCoinFragment = new SellCoinFragment();

        tabType = TAB_TYPE_BUY;
        setFragment();
    }

    private void setFragment() {
        if (tabType == TAB_TYPE_BUY) {
            QcActivityUtils.replaceFragment(getChildFragmentManager(),
                    mBuyCoinFragment,
                    R.id.content,
                    BuyCoinFragment.class.getSimpleName());

        } else if (tabType == TAB_TYPE_SELL) {
            QcActivityUtils.replaceFragment(getChildFragmentManager(),
                    mSellCoinFragment,
                    R.id.content,
                    SellCoinFragment.class.getSimpleName());
        }
        setTabButton();
    }

    private void setTabButton() {
        if (tabType == TAB_TYPE_BUY) {
            viewBinding.btnBuy.setTextColor(getResources().getColor(R.color.color_white));
            viewBinding.btnBuy.setBackgroundResource(R.drawable.bg_rounded_pink);
            viewBinding.btnSell.setTextColor(getResources().getColor(R.color.colorAccent));
            viewBinding.btnSell.setBackgroundResource(R.drawable.bg_rounded_blue_stroke);

        } else if (tabType == TAB_TYPE_SELL) {
            viewBinding.btnBuy.setTextColor(getResources().getColor(R.color.color_pink_500));
            viewBinding.btnBuy.setBackgroundResource(R.drawable.bg_rounded_pink_stroke);
            viewBinding.btnSell.setTextColor(getResources().getColor(R.color.color_white));
            viewBinding.btnSell.setBackgroundResource(R.drawable.bg_rounded_blue);
        }
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
//                    showAlertDialog();
                } else {
                    viewBinding.includedItemCalculator.layInfo.setVisibility(View.GONE);
//                    viewBinding.includedItemCalculator.recyclerView.setVisibility(View.GONE);
                    viewBinding.includedItemCalculator.btnInfoExpened.animate()
                            .rotation(0)
                            .setInterpolator(new AccelerateInterpolator())
                            .setDuration(200)
                            .start();
//                    viewBinding.nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
//                    showAlertDialog();
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
