package com.ulling.upstar.main.view;

import android.animation.Animator;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.listener.OnSingleClickListener;
import com.ulling.lib.core.util.QcLog;
import com.ulling.lib.core.util.QcToast;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentDrawerMenuBinding;
import com.ulling.upstar.main.adapter.MenuAdapter;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static com.ulling.upstar.main.view.MainActivity.FRAG_TYPE_COIN_CALCULATOR;
import static com.ulling.upstar.main.view.MainActivity.FRAG_TYPE_MARKET_PRICE;
import static com.ulling.upstar.main.view.MainActivity.FRAG_TYPE_TALK;

public class DrawerMenuFragment extends QcBaseLifeFragment {

    private static DrawerLayout drawer;
    private FragmentDrawerMenuBinding viewBinding;
    private OnMenuListener listener;

    private List<Menu> menuCoinPriceItems = new ArrayList<Menu>();
    private List<Menu> menuTalkItems = new ArrayList<Menu>();
    private List<Menu> menuCalculatorItems = new ArrayList<Menu>();


    private MenuAdapter adapterCoinPrice;
    private MenuAdapter adapterTalk;
    private MenuAdapter adapterCalculator;

    boolean isPriceRecyclerView = true;
    boolean isTalkRecyclerView = false;
    boolean isCalculatorRecyclerView = false;

    public void setListener(OnMenuListener listener) {
        this.listener = listener;
    }

    public interface OnMenuListener {
        void onSelected(int fragType, int subType);
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


        adapterCoinPrice = new MenuAdapter(qCon, qcPriceRecyclerItemListener);
        adapterTalk = new MenuAdapter(qCon, qcTalkRecyclerItemListener);
        adapterCalculator = new MenuAdapter(qCon, qcCalculatorRecyclerItemListener);

        viewBinding.recyclerViewMarketPrice.setLayoutManager(new GridLayoutManager(qCon, 1));
        viewBinding.recyclerViewMarketPrice.setAdapter(adapterCoinPrice);
        viewBinding.recyclerViewMarketPrice.setNestedScrollingEnabled(false);

        viewBinding.recyclerViewTalk.setLayoutManager(new GridLayoutManager(qCon, 1));
        viewBinding.recyclerViewTalk.setAdapter(adapterTalk);
        viewBinding.recyclerViewTalk.setNestedScrollingEnabled(false);


        viewBinding.recyclerViewCoinCalculator.setLayoutManager(new GridLayoutManager(qCon, 1));
        viewBinding.recyclerViewCoinCalculator.setAdapter(adapterCalculator);
        viewBinding.recyclerViewCoinCalculator.setNestedScrollingEnabled(false);

        viewBinding.btnPriceExpened.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                isPriceRecyclerView = !isPriceRecyclerView;
                if (isPriceRecyclerView) {
//                    viewBinding.btnPriceExpened.setBackgroundResource(R.drawable.ic_expand_more);
                    viewBinding.btnPriceExpened.animate().rotation(180).setDuration(500).withLayer()
                            .setInterpolator(new AccelerateInterpolator()).start();
                } else {
//                    viewBinding.btnPriceExpened.setBackgroundResource(R.drawable.ic_expand_less);
                    viewBinding.btnPriceExpened.animate().rotation(180).setDuration(500).withLayer()
                            .setInterpolator(new AccelerateInterpolator()).start();
                }
                setCoinPriceView();
            }
        });
        viewBinding.btnTalkExpened.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                isTalkRecyclerView = !isTalkRecyclerView;
                if (isTalkRecyclerView) {
                    viewBinding.btnTalkExpened.setBackgroundResource(R.drawable.ic_expand_less);
                    viewBinding.btnTalkExpened.animate().rotation(-180).setDuration(500).setInterpolator(new AccelerateInterpolator()).start();
                } else {
                    viewBinding.btnTalkExpened.setBackgroundResource(R.drawable.ic_expand_more);
                    viewBinding.btnTalkExpened.animate().rotation(180).setDuration(500).setInterpolator(new AccelerateInterpolator()).start();
                }
                setTalkView();

            }
        });
        viewBinding.btnCalculatorExpened.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                isCalculatorRecyclerView = !isCalculatorRecyclerView;
                if (isCalculatorRecyclerView) {
                    viewBinding.btnCalculatorExpened.setBackgroundResource(R.drawable.ic_expand_less);
                    viewBinding.btnCalculatorExpened.animate().rotation(-180).setDuration(500).setInterpolator(new AccelerateInterpolator()).start();
                } else {
                    viewBinding.btnCalculatorExpened.setBackgroundResource(R.drawable.ic_expand_more);
                    viewBinding.btnCalculatorExpened.animate().rotation(180).setDuration(500).setInterpolator(new AccelerateInterpolator()).start();
                }
                setCalculatorView();

            }
        });

        setCoinPriceData();
        setTalkData();
        setCalculatorData();

        setCoinPriceView();
        setTalkView();
        setCalculatorView();
    }


    private void setCoinPriceData() {
        menuCoinPriceItems = new ArrayList<Menu>();
        String[] menu_market_price_sub = getResources().getStringArray(R.array.menu_market_price_sub);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.menu_market_price_sub_img);

        for (int i = 0; i < menu_market_price_sub.length; i++) {
            int resourceId = typedArray.getResourceId(i, -1);
            Menu menu = new Menu(FRAG_TYPE_MARKET_PRICE, menu_market_price_sub[i], resourceId);
            menuCoinPriceItems.add(menu);
        }
        typedArray.recycle();
    }

    private void setCoinPriceView() {

        if (isPriceRecyclerView) {

            viewBinding.recyclerViewMarketPrice.animate()
                    .setDuration(1000)
                    .translationY(-400)
                    .withLayer()
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            viewBinding.recyclerViewMarketPrice.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

//            viewBinding.recyclerViewMarketPrice.setVisibility(View.VISIBLE);
        } else {
            viewBinding.recyclerViewMarketPrice.animate()
                    .setDuration(1000)
                    .translationY(400)
                    .withLayer()
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            viewBinding.recyclerViewMarketPrice.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            viewBinding.recyclerViewMarketPrice.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

//            viewBinding.recyclerViewMarketPrice.setVisibility(View.GONE);
        }
    }

    private void setTalkData() {
        menuTalkItems = new ArrayList<Menu>();
        String[] menu_talk_sub = getResources().getStringArray(R.array.menu_talk_sub);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.menu_talk_sub_img);

        for (int i = 0; i < menu_talk_sub.length; i++) {
            int resourceId = typedArray.getResourceId(i, -1);
            Menu menu = new Menu(FRAG_TYPE_TALK, menu_talk_sub[i], resourceId);
            menuTalkItems.add(menu);
        }
        typedArray.recycle();
    }

    private void setTalkView() {
        if (isTalkRecyclerView) {
            viewBinding.recyclerViewTalk.setVisibility(View.VISIBLE);
            viewBinding.btnTalkExpened.setBackgroundResource(R.drawable.ic_expand_less);
        } else {
            viewBinding.recyclerViewTalk.setVisibility(View.GONE);
            viewBinding.btnTalkExpened.setBackgroundResource(R.drawable.ic_expand_more);
        }
    }

    private void setCalculatorData() {
        menuCalculatorItems = new ArrayList<Menu>();
        String[] menu_coin_calculator_sub = getResources().getStringArray(R.array.menu_coin_calculator_sub);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.menu_talk_sub_img);

        for (int i = 0; i < menu_coin_calculator_sub.length; i++) {
            int resourceId = typedArray.getResourceId(i, -1);
            Menu menu = new Menu(FRAG_TYPE_COIN_CALCULATOR, menu_coin_calculator_sub[i], resourceId);
            menuCalculatorItems.add(menu);
        }
        typedArray.recycle();
    }

    private void setCalculatorView() {
        if (isCalculatorRecyclerView) {
            viewBinding.recyclerViewCoinCalculator.setVisibility(View.VISIBLE);
//            viewBinding.btnCalculatorExpened.setBackgroundResource(R.drawable.ic_expand_less);
        } else {
            viewBinding.recyclerViewCoinCalculator.setVisibility(View.GONE);
//            viewBinding.btnCalculatorExpened.setBackgroundResource(R.drawable.ic_expand_more);
        }
    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {
        adapterCoinPrice.addAll(menuCoinPriceItems);
        adapterTalk.addAll(menuTalkItems);
        adapterCalculator.addAll(menuCalculatorItems);

        QcLog.e("viewBinding.recyclerViewMarketPrice == " + viewBinding.recyclerViewMarketPrice.getHeight());
    }


    @Override
    protected void needDestroyData() {
        qcPriceRecyclerItemListener = null;
        qcTalkRecyclerItemListener = null;
        qcCalculatorRecyclerItemListener = null;
    }

    public void moveFragment(View view, int position, Menu menu) {
        if (listener != null) {
            if (FRAG_TYPE_MARKET_PRICE == menu.getType()) {
                listener.onSelected(MainActivity.FRAG_TYPE_MARKET_PRICE, position);

            } else if (FRAG_TYPE_TALK == menu.getType()) {
                listener.onSelected(MainActivity.FRAG_TYPE_TALK, position);

            } else if (FRAG_TYPE_COIN_CALCULATOR == menu.getType()) {
                listener.onSelected(MainActivity.FRAG_TYPE_COIN_CALCULATOR, position);
            }
        }
    }

    private QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu> qcPriceRecyclerItemListener = new QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu>() {

        @Override
        public void onItemClick(View view, int position, Menu menu) {
            QcToast.getInstance().show("onItemClick == " + menu.toString());
            moveFragment(view, position, menu);
        }

        @Override
        public void onItemLongClick(View view, int position, Menu menu) {

        }

        @Override
        public void onItemCheck(boolean checked, int position, Menu menu) {

        }

        @Override
        public void onDeleteItem(int itemPosition, Menu menu) {

        }

        @Override
        public void onReload() {

        }
    };


    private QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu> qcTalkRecyclerItemListener = new QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu>() {

        @Override
        public void onItemClick(View view, int position, Menu menu) {
            QcToast.getInstance().show("onItemClick == " + menu.toString());
            moveFragment(view, position, menu);
        }

        @Override
        public void onItemLongClick(View view, int position, Menu menu) {

        }

        @Override
        public void onItemCheck(boolean checked, int position, Menu menu) {

        }

        @Override
        public void onDeleteItem(int itemPosition, Menu menu) {

        }

        @Override
        public void onReload() {

        }
    };


    private QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu> qcCalculatorRecyclerItemListener = new QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu>() {

        @Override
        public void onItemClick(View view, int position, Menu menu) {
            QcToast.getInstance().show("onItemClick == " + menu.toString());
            moveFragment(view, position, menu);
        }

        @Override
        public void onItemLongClick(View view, int position, Menu menu) {

        }

        @Override
        public void onItemCheck(boolean checked, int position, Menu menu) {

        }

        @Override
        public void onDeleteItem(int itemPosition, Menu menu) {

        }

        @Override
        public void onReload() {

        }
    };


}

