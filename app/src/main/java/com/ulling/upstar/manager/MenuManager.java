package com.ulling.upstar.manager;

import android.content.Context;
import android.content.res.TypedArray;

import com.ulling.upstar.R;
import com.ulling.upstar.model.Menu;
import com.ulling.upstar.view.adapter.MenuAdapter;

import java.util.ArrayList;

import static com.ulling.upstar.view.MainActivity.FRAG_TYPE_COIN_CALCULATOR;
import static com.ulling.upstar.view.MainActivity.FRAG_TYPE_MARKET_PRICE;
import static com.ulling.upstar.view.MainActivity.FRAG_TYPE_TALK;
import static com.ulling.upstar.view.adapter.MenuAdapter.TYPE_SUB;

/**
 * Created by KILHO on 2018. 1. 26..
 */
public class MenuManager {
    private static MenuManager instance = null;
    public Context qCon;
    private ArrayList<Menu> menuItems = new ArrayList<Menu>();

    public static MenuManager getInstance(Context qCon) {
        if (instance == null) {
            instance = new MenuManager(qCon);
        }
        return instance;
    }

    public MenuManager(Context qCon) {
        this.qCon = qCon;
    }

    public ArrayList<Menu> setMenuData() {
        menuItems = new ArrayList<Menu>();

        Menu titlePrice = new Menu(MenuAdapter.TYPE_TITLE, FRAG_TYPE_MARKET_PRICE, qCon.getResources().getString(R.string.menu_market_price), 0);
        String[] menu_market_price_sub = qCon.getResources().getStringArray(R.array.menu_market_price_sub);
        TypedArray typedArray1 = qCon.getResources().obtainTypedArray(R.array.menu_market_price_sub_img);
        getMenuData(titlePrice, menu_market_price_sub, typedArray1);

        Menu titleTalk = new Menu(MenuAdapter.TYPE_TITLE, FRAG_TYPE_TALK, qCon.getResources().getString(R.string.menu_talk), 0);
        String[] menu_talk_sub = qCon.getResources().getStringArray(R.array.menu_talk_sub);
        TypedArray typedArray2 = qCon.getResources().obtainTypedArray(R.array.menu_talk_sub_img);
        getMenuData(titleTalk, menu_talk_sub, typedArray2);

        Menu titleCalculator = new Menu(MenuAdapter.TYPE_TITLE, FRAG_TYPE_COIN_CALCULATOR, qCon.getResources().getString(R.string.menu_coin_calculator), 0);
        String[] menu_coin_calculator_sub = qCon.getResources().getStringArray(R.array.menu_coin_calculator_sub);
        TypedArray typedArray3 = qCon.getResources().obtainTypedArray(R.array.menu_coin_calculator_sub_img);
        getMenuData(titleCalculator, menu_coin_calculator_sub, typedArray3);

        return menuItems;

    }

    private void getMenuData(Menu titleMenu, String[] subMenu, TypedArray resourceTypedArray) {
        menuItems.add(titleMenu);

        for (int i = 0; i < subMenu.length; i++) {
            int resourceId = resourceTypedArray.getResourceId(i, -1);
            Menu menu = new Menu(TYPE_SUB, titleMenu.getFragType(), i, subMenu[i], resourceId);
            menuItems.add(menu);
        }
        resourceTypedArray.recycle();
        resourceTypedArray = null;
    }
}
