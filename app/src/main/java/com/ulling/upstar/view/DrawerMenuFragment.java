package com.ulling.upstar.view;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentDrawerMenuBinding;
import com.ulling.upstar.manager.MenuManager;
import com.ulling.upstar.view.adapter.MenuAdapter;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;

import static com.ulling.upstar.view.adapter.MenuAdapter.TYPE_SUB;
import static com.ulling.upstar.view.MainActivity.FRAG_TYPE_COIN_CALCULATOR;
import static com.ulling.upstar.view.MainActivity.FRAG_TYPE_MARKET_PRICE;
import static com.ulling.upstar.view.MainActivity.FRAG_TYPE_TALK;

/**
 * 드로우 메뉴
 */
public class DrawerMenuFragment extends QcBaseLifeFragment {

    private static DrawerLayout drawer;
    private FragmentDrawerMenuBinding viewBinding;
    private OnMenuListener listener;

    private ArrayList<Menu> menuItems = new ArrayList<Menu>();

    private MenuAdapter adapterMenu;
    private int position = 1;

    public void setListener(OnMenuListener listener) {
        this.listener = listener;
    }

    public interface OnMenuListener {
        void onSelected(Menu menu);
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

        menuItems = MenuManager.getInstance(qCon).setMenuData();
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
        if (listener != null && menu.getType() == TYPE_SUB && this.position != position) {
            this.position = position;
            listener.onSelected(menu);
        } else {
            if (drawer != null)
                drawer.closeDrawers();
        }
    }

    private QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu> qcRecyclerItemListener = new QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu>() {

        @Override
        public void onItemClick(View view, int position, Menu menu) {
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

