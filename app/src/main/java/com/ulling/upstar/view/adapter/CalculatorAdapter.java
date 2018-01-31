package com.ulling.upstar.view.adapter;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.ulling.lib.core.entities.QcBaseItem;
import com.ulling.lib.core.listener.OnSingleClickListener;
import com.ulling.lib.core.util.QcLog;
import com.ulling.lib.core.viewutil.adapter.QcBaseViewHolder;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.model.CoinCalculator;
import com.ulling.upstar.view.viewholder.CalculatorViewHolder;

import java.util.ArrayList;

/**
 *
 */
public class CalculatorAdapter extends QcRecyclerBaseAdapter<CoinCalculator> {
    public static final int TYPE_TOP = 1;
    public static final int TYPE_SUB = 2;

    public CalculatorAdapter(Context qCon, QcRecyclerItemListener qcRecyclerItemListener) {
        super(qCon, qcRecyclerItemListener);
    }

    @Override
    protected void needInitToOnCreate() {
    }

    @Override
    public void needResetData() {
    }

    @Override
    public void setViewModel(AndroidViewModel viewModel) {
    }

    @Override
    protected QcBaseViewHolder needViewHolderFromItemViewType(int viewType, ViewDataBinding binding) {
        return new CalculatorViewHolder(binding);
    }

    @Override
    protected int needLayoutIdFromItemViewType(int viewType) {
        return R.layout.item_calculator;
    }

    @Override
    protected void needUILoadFailBinding(QcBaseViewHolder holder, int position, QcBaseItem item) {
    }

    @Override
    protected void needUILoadProgressBinding(QcBaseViewHolder holder, int position, QcBaseItem item) {
    }

    @Override
    protected void needUICustomBinding(int viewType, QcBaseViewHolder holder, final int position, QcBaseItem item) {
        if (holder instanceof CalculatorViewHolder) {
            final CalculatorViewHolder calculatorViewHolder = (CalculatorViewHolder) holder;

            calculatorViewHolder.item = (CoinCalculator) item;
            QcLog.e("itemList  ==" + itemList.size() + " , " + position);

            if (calculatorViewHolder.item.getType() == TYPE_TOP) {
                calculatorViewHolder.viewBinding.includedItemCalculatorResult.getRoot().setVisibility(View.GONE);

                calculatorViewHolder.viewBinding.includedItemCalculatorAdd.btnSell.setOnClickListener(new OnSingleClickListener() {
                    @Override
                    public void onSingleClick(View v) {
                        if (itemList != null && itemList.size() > 1) {
                            calculatorViewHolder.item.setType(TYPE_SUB);
                            itemList.remove(position);
                            notifyItemRemoved(position);
                        }
                    }
                });
                calculatorViewHolder.viewBinding.includedItemCalculatorAdd.btnBuy.setOnClickListener(new OnSingleClickListener() {
                    @Override
                    public void onSingleClick(View v) {
                        if (itemList != null && itemList.size() > 0) {
                            calculatorViewHolder.item.setType(TYPE_SUB);
                            CoinCalculator mCoinCalculator = new CoinCalculator();
                            mCoinCalculator.setType(CalculatorAdapter.TYPE_TOP);
                            itemList.add(mCoinCalculator);
                            notifyItemInserted(position + 1);
                        }
                    }
                });

            } else {
                calculatorViewHolder.viewBinding.includedItemCalculatorResult.getRoot().setVisibility(View.VISIBLE);
                calculatorViewHolder.viewBinding.includedItemCalculatorResult.btnCancle.setOnClickListener(new OnSingleClickListener() {
                    @Override
                    public void onSingleClick(View v) {
                        if (itemList != null && itemList.size() > 1) {
                            CoinCalculator mCoinCalculator = new CoinCalculator();
                            if (qcRecyclerItemListener != null)
                                qcRecyclerItemListener.onDeleteItem(position, mCoinCalculator);
                            itemList.remove(position);
                            notifyItemRemoved(position);
                        }
                    }
                });

            }


        }
    }

}
