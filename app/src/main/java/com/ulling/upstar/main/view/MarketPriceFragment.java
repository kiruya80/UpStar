
package com.ulling.upstar.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentMarketPriceBinding;
import com.ulling.upstar.main.adapter.MenuAdapter;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 거래소별 시세
 * https://steemit.com/kr/@nhj12311/api
 * 
 */
public class MarketPriceFragment extends QcBaseLifeFragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private FragmentMarketPriceBinding viewBinding;
    public List<Menu> menuItems = new ArrayList<Menu>();
    private MenuAdapter menuAdapter;


    public MarketPriceFragment() {
    }

    public static MarketPriceFragment getInstance(int columnCount) {
        MarketPriceFragment fragment = new MarketPriceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    public void setSubType(int subType) {
    }

    @Override
    protected void needInitToOnCreate() {
//        resetMenuData();
    }

    @Override
    protected void optGetArgument(Bundle savedInstanceState) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
    }

    @Override
    protected void needResetData() {

    }

    @Override
    protected void needInitViewModel() {

    }

    @Override
    protected int needGetLayoutId() {
        return R.layout.fragment_market_price;
    }

    @Override
    protected void needUIBinding() {
        viewBinding = (FragmentMarketPriceBinding) getViewDataBinding();

//        setToolbar(viewBinding.toolbar);
//
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (mColumnCount <= 1) {
            viewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(qCon));
        } else {
            viewBinding.recyclerView.setLayoutManager(new GridLayoutManager(qCon, mColumnCount));
        }
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


