package com.ulling.upstar.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
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
import com.ulling.lib.core.util.QcLog;
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
public class CoinCalculatorFragment extends QcBaseLifeFragment implements TabHost.OnTabChangeListener {

    public static final String TAB_TYPE_01 = "TAB_TYPE_01";
    public static final String TAB_TYPE_02 = "TAB_TYPE_02";

    private FragmentCoinCalculatorBinding viewBinding;
    private boolean isInfoView = false;
    // 코인 정보
    private CoinPriceRatio coinPriceRatio;

    private CalculatorAdapter calculatorAdapter;
    private ArrayList<CoinCalculator> coinCalculators;
    private BuyCoinFragment mBuyCoinFragment;
    private SellCoinFragment mSellCoinFragment;


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


        mBuyCoinFragment = new BuyCoinFragment();
        mSellCoinFragment = new SellCoinFragment();

        viewBinding.tabHost.setOnTabChangedListener(this);
//        setupTabs();

        // http://stex.tistory.com/35
        viewBinding.tabHost.setup(qCon, getChildFragmentManager(), R.id.content);
        TabHost.TabSpec tabSpec1 = viewBinding.tabHost.newTabSpec(TAB_TYPE_01);
        tabSpec1.setIndicator(getResources().getString(R.string.str_sell));
        tabSpec1.setIndicator(getTabIndicator(viewBinding.tabHost.getContext(), getResources().getString(R.string.str_sell), R.drawable.bg_rounded_pink));
        viewBinding.tabHost.addTab(tabSpec1, BuyCoinFragment.class, null);

        TabHost.TabSpec tabSpec2 = viewBinding.tabHost.newTabSpec(TAB_TYPE_02);
        tabSpec2.setIndicator(getResources().getString(R.string.str_buy));
        tabSpec2.setIndicator(getTabIndicator(viewBinding.tabHost.getContext(), getResources().getString(R.string.str_buy), R.drawable.bg_rounded_blue));
        viewBinding.tabHost.addTab(tabSpec2, SellCoinFragment.class, null);

        setTabColor(TAB_TYPE_01);

        viewBinding.tabHost.getTabWidget().setShowDividers(TabWidget.SHOW_DIVIDER_MIDDLE);
        viewBinding.tabHost.getTabWidget().setDividerDrawable(R.drawable.bg_tab_driver);
        viewBinding.tabHost.setCurrentTab(mCurrentTab);
    }

    private void setupTabs() {
        viewBinding.tabHost.setup(qCon, getChildFragmentManager(), android.R.id.tabcontent);// important!
        viewBinding.tabHost.addTab(newTab(TAB_TYPE_01, R.string.str_sell, R.id.buyContent));
        viewBinding.tabHost.addTab(newTab(TAB_TYPE_02, R.string.str_buy, R.id.sellContent));
    }

    private TabHost.TabSpec newTab(String tag, int labelId, int tabContentId) {
        View indicator = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_coin_calculator,
                (ViewGroup) viewBinding.getRoot().findViewById(android.R.id.tabs), false);
        ((TextView) indicator.findViewById(R.id.text)).setText(labelId);

        TabHost.TabSpec tabSpec = viewBinding.tabHost.newTabSpec(tag);
        tabSpec.setIndicator(indicator);
        tabSpec.setContent(tabContentId);
        return tabSpec;
    }


    private void setTabColor(String tabId) {
        for (int i = 0; i < viewBinding.tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) viewBinding.tabHost.getTabWidget().getChildAt(i).findViewById(R.id.textTitle); // 탭에 있는 TextView 지정후
            View background = (View) viewBinding.tabHost.getTabWidget().getChildAt(i);
            if (i == viewBinding.tabHost.getCurrentTab()) {
                tv.setTextColor(getResources().getColor(R.color.color_white));
                if (TAB_TYPE_01.equals(tabId)) {
                    background.setBackgroundColor(getResources().getColor(R.color.color_pink_500));
                    background.setBackgroundResource(R.drawable.bg_rounded_pink);
                } else if (TAB_TYPE_02.equals(tabId)) {
                    background.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    background.setBackgroundResource(R.drawable.bg_rounded_blue);
                }
            } else {
                if (TAB_TYPE_01.equals(tabId)) {
                    tv.setTextColor(getResources().getColor(R.color.colorAccent));
                    background.setBackgroundResource(R.drawable.bg_rounded_blue_stroke);
                } else if (TAB_TYPE_02.equals(tabId)) {
                    tv.setTextColor(getResources().getColor(R.color.color_pink_500));
                    background.setBackgroundResource(R.drawable.bg_rounded_pink_stroke);
                }
            }
        }
    }
    private int mCurrentTab = 0;

    @Override
    public void onTabChanged(String tabId) {
        QcLog.e("onTabChanged == " + tabId);
        setTabColor(tabId);
        if (TAB_TYPE_01.equals(tabId)) {
            updateTab(tabId, R.id.buyContent);
            mCurrentTab = 0;
        } else if (TAB_TYPE_02.equals(tabId)) {
            updateTab(tabId, R.id.sellContent);
            mCurrentTab = 1;
        }
    }
    private void updateTab(String tabId, int placeholder) {
        FragmentManager fm = getFragmentManager();
        QcBaseLifeFragment mQcBaseLifeFragment = null;
        if (fm.findFragmentByTag(tabId) == null) {
            if (TAB_TYPE_01.equals(tabId)) {
                mQcBaseLifeFragment = SellCoinFragment.getInstance();
            } else if (TAB_TYPE_02.equals(tabId)) {
                mQcBaseLifeFragment = BuyCoinFragment.getInstance();
            }
            fm.beginTransaction()
                    .replace(placeholder, mQcBaseLifeFragment, tabId)
                    .commit();
        }
    }

    private View getTabIndicator(Context context, String title, int resId) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab, null);
        LinearLayout layTab = (LinearLayout) view.findViewById(R.id.layTab);
        layTab.setBackgroundResource(resId);
        TextView textTitle = (TextView) view.findViewById(R.id.textTitle);
        textTitle.setText(title);
        return view;
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
