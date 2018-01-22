package com.ulling.upstar.main.view;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.util.QcLog;
import com.ulling.lib.core.util.QcToast;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentDrawerMenuBinding;
import com.ulling.upstar.main.adapter.MenuAdapter;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static com.ulling.upstar.main.adapter.MenuAdapter.TYPE_MENU_MAIN;
import static com.ulling.upstar.main.adapter.MenuAdapter.TYPE_MENU_MAIN_SUB;

public class DrawerMenuFragment extends QcBaseLifeFragment {

    private static DrawerLayout drawer;
    private FragmentDrawerMenuBinding viewBinding;
    public List<Menu> menuItems = new ArrayList<Menu>();
    private MenuAdapter menuAdapter;
    private OnMenuListener listener;

    public void setListener(OnMenuListener listener) {
        this.listener = listener;
    }

    public interface OnMenuListener  {
        void onSelected(int main, int sub);
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
        resetMenuData();
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
        viewBinding.recyclerView.setLayoutManager(new GridLayoutManager(qCon, 1));
        menuAdapter = new MenuAdapter(qCon, qcRecyclerItemListener);
        viewBinding.recyclerView.setAdapter(menuAdapter);
    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {
        menuAdapter.addAll(menuItems);
    }


    @Override
    protected void needDestroyData() {
        qcRecyclerItemListener = null;
    }

    private QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu> qcRecyclerItemListener = new QcRecyclerBaseAdapter.QcRecyclerItemListener<Menu>() {

        @Override
        public void onItemClick(View view, int position, Menu menu) {
            QcToast.getInstance().show("onItemClick == " + menu.toString());
            if (listener != null) {
                if (TYPE_MENU_MAIN == menu.getType()) {
                    if (getResources().getString(R.string.menu_market_price).equals(menu.getName())) {
                        listener.onSelected(MainActivity.FRAG_TYPE_MARKET_PRICE, 0);
                    } else if (getResources().getString(R.string.menu_talk).equals(menu.getName())) {
                        listener.onSelected(MainActivity.FRAG_TYPE_TALK, 0);
                    } else if (getResources().getString(R.string.menu_coin_calculator).equals(menu.getName())) {
                        listener.onSelected(MainActivity.FRAG_TYPE_COIN_CALCULATOR, 0);
                    }
                } else if (TYPE_MENU_MAIN_SUB == menu.getType()) {
                    if (getResources().getString(R.string.menu_market_price).equals(menu.getName())) {
                        listener.onSelected(MainActivity.FRAG_TYPE_MARKET_PRICE, 0);
                    } else if (getResources().getString(R.string.menu_talk).equals(menu.getName())) {
                        listener.onSelected(MainActivity.FRAG_TYPE_TALK, 0);
                    } else if (getResources().getString(R.string.menu_coin_calculator).equals(menu.getName())) {
                        listener.onSelected(MainActivity.FRAG_TYPE_COIN_CALCULATOR, 0);
                    }
                }
            }
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


    public void resetMenuData() {
        // Add some sample items.
        String[] menu = getResources().getStringArray(R.array.menu);

        for (int i = 0; i < menu.length; i++) {

              List<Menu.SubMenu> subMenu = new ArrayList<Menu.SubMenu>();

            if (getResources().getString(R.string.menu_market_price).equals(menu[i])) {
                String[] menu_market_price_sub = getResources().getStringArray(R.array.menu_market_price_sub);

                for (int j = 0; j < menu_market_price_sub.length; j++) {
                    Menu.SubMenu menuSub = new Menu.SubMenu(menu_market_price_sub[j]);
                    subMenu.add(menuSub);
                }

            } else if (getResources().getString(R.string.menu_talk).equals(menu[i])) {
                String[] menu_talk_sub = getResources().getStringArray(R.array.menu_talk_sub);
                for (int j = 0; j < menu_talk_sub.length; j++) {
                    Menu.SubMenu menuSub = new Menu.SubMenu(menu_talk_sub[j]);
                    subMenu.add(menuSub);
                }

            } else if (getResources().getString(R.string.menu_coin_calculator).equals(menu[i])) {
                String[] menu_coin_calculator_sub = getResources().getStringArray(R.array.menu_coin_calculator_sub);
                for (int j = 0; j < menu_coin_calculator_sub.length; j++) {
                    Menu.SubMenu menuSub = new Menu.SubMenu(menu_coin_calculator_sub[j]);
                    subMenu.add(menuSub);
                }
            }
            Menu mMenu = new Menu(TYPE_MENU_MAIN, menu[i], subMenu);
            menuItems.add(mMenu);
        }
        QcLog.e("menuItems == " + menuItems.size());
    }
}

