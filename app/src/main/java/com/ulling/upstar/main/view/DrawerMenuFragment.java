package com.ulling.upstar.main.view;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.util.QcToast;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentDrawerMenuBinding;
import com.ulling.upstar.main.adapter.MenuAdapter;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;

import static com.ulling.upstar.main.adapter.MenuAdapter.TYPE_SUB;
import static com.ulling.upstar.main.view.MainActivity.FRAG_TYPE_COIN_CALCULATOR;
import static com.ulling.upstar.main.view.MainActivity.FRAG_TYPE_MARKET_PRICE;
import static com.ulling.upstar.main.view.MainActivity.FRAG_TYPE_TALK;

/**
 * 드로우 메뉴
 */
public class DrawerMenuFragment extends QcBaseLifeFragment {

    private static DrawerLayout drawer;
    private FragmentDrawerMenuBinding viewBinding;
    private OnMenuListener listener;

    private ArrayList<Menu> menuItems = new ArrayList<Menu>();

    private MenuAdapter adapterMenu;

    boolean isPriceRecyclerView = true;
    boolean isTalkRecyclerView = false;
    boolean isCalculatorRecyclerView = false;
    private float marketPriceHeight = 0;

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


        adapterMenu = new MenuAdapter(qCon, qcRecyclerItemListener);

        viewBinding.recyclerView.setLayoutManager(new GridLayoutManager(qCon, 1));
        viewBinding.recyclerView.setAdapter(adapterMenu);
        viewBinding.recyclerView.setNestedScrollingEnabled(false);

        setMenuData();
    }


    private void setMenuData() {

        menuItems = new ArrayList<Menu>();

        Menu titlePrice = new Menu(MenuAdapter.TYPE_TITLE, FRAG_TYPE_MARKET_PRICE, getResources().getString(R.string.menu_market_price), 0);
        Menu titleTalk = new Menu(MenuAdapter.TYPE_TITLE, FRAG_TYPE_TALK, getResources().getString(R.string.menu_talk), 0);
        Menu titleCalculator = new Menu(MenuAdapter.TYPE_TITLE, FRAG_TYPE_COIN_CALCULATOR, getResources().getString(R.string.menu_coin_calculator), 0);

        menuItems.add(titlePrice);

        String[] menu_market_price_sub = getResources().getStringArray(R.array.menu_market_price_sub);
        TypedArray typedArray1 = getResources().obtainTypedArray(R.array.menu_market_price_sub_img);

        for (int i = 0; i < menu_market_price_sub.length; i++) {
            int resourceId = typedArray1.getResourceId(i, -1);
            Menu menu = new Menu(TYPE_SUB, FRAG_TYPE_MARKET_PRICE, menu_market_price_sub[i], resourceId);
            menuItems.add(menu);
        }
        typedArray1.recycle();

        menuItems.add(titleTalk);
        String[] menu_talk_sub = getResources().getStringArray(R.array.menu_talk_sub);
        TypedArray typedArray2 = getResources().obtainTypedArray(R.array.menu_talk_sub_img);

        for (int i = 0; i < menu_talk_sub.length; i++) {
            int resourceId = typedArray2.getResourceId(i, -1);
            Menu menu = new Menu(TYPE_SUB, FRAG_TYPE_TALK, menu_talk_sub[i], resourceId);
            menuItems.add(menu);
        }
        typedArray2.recycle();


        menuItems.add(titleCalculator);
        String[] menu_coin_calculator_sub = getResources().getStringArray(R.array.menu_coin_calculator_sub);
        TypedArray typedArray3 = getResources().obtainTypedArray(R.array.menu_talk_sub_img);

        for (int i = 0; i < menu_coin_calculator_sub.length; i++) {
            int resourceId = typedArray3.getResourceId(i, -1);
            Menu menu = new Menu(TYPE_SUB, FRAG_TYPE_COIN_CALCULATOR, menu_coin_calculator_sub[i], resourceId);
            menuItems.add(menu);
        }
        typedArray3.recycle();
    }




    @Override
    protected void needUIEventListener() {
    }

    @Override
    protected void needSubscribeUiFromViewModel() {
        adapterMenu.addAll(menuItems);
     }


    @Override
    protected void needDestroyData() {
        qcRecyclerItemListener = null;
    }

    public void moveFragment(View view, int position, Menu menu) {
        if (listener != null && menu.getType() == TYPE_SUB) {
            if (FRAG_TYPE_MARKET_PRICE == menu.getType()) {
                listener.onSelected(menu.getFragType(), position-1);

            } else if (FRAG_TYPE_TALK == menu.getType()) {
                listener.onSelected(menu.getFragType(), position-2);

            } else if (FRAG_TYPE_COIN_CALCULATOR == menu.getType()) {
                listener.onSelected(menu.getFragType(), position-3);
            }
        }
    }

    private QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu> qcRecyclerItemListener = new QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu>() {

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

