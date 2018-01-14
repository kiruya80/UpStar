
package com.ulling.upstar.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentMarketPriceBinding;
import com.ulling.upstar.view.adapter.MyItemRecyclerViewAdapter;
import com.ulling.upstar.view.dummy.DummyContent;
import com.ulling.upstar.view.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 * content_main
 */
public class MarketPriceFragment extends QcBaseLifeFragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private FragmentMarketPriceBinding viewBinding;

    public MarketPriceFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MarketPriceFragment getInstance(int columnCount) {
        MarketPriceFragment fragment = new MarketPriceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void needInitToOnCreate() {
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
        if (mColumnCount <= 1) {
            viewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(qCon));
        } else {
            viewBinding.recyclerView.setLayoutManager(new GridLayoutManager(qCon, mColumnCount));
        }
        viewBinding.recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));


    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }

    @Override
    protected void needDestroyData() {
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
