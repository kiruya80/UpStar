package com.ulling.upstar.view.adapter;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.ulling.lib.core.entities.QcBaseItem;
import com.ulling.lib.core.listener.OnSingleClickListener;
import com.ulling.lib.core.viewutil.adapter.QcBaseViewHolder;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.manager.ImageManager;
import com.ulling.upstar.model.CoinCalculator;
import com.ulling.upstar.model.Menu;
import com.ulling.upstar.view.viewholder.CalculatorViewHolder;
import com.ulling.upstar.view.viewholder.MenuViewHolder;

import java.util.ArrayList;

/**
 *
 */
public class CalculatorAdapter extends QcRecyclerBaseAdapter<Menu> {
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
            if (calculatorViewHolder.item.getType() == TYPE_TOP) {
                calculatorViewHolder.viewBinding.includedItemCalculatorResult.getRoot().setVisibility(View.GONE);

                calculatorViewHolder.viewBinding.includedItemCalculatorAdd.btnConfirm.setOnClickListener(new OnSingleClickListener() {
                    @Override
                    public void onSingleClick(View v) {
                        itemOpened[position] = true;
                        if (itemOpened[position]) {

                        } else {

                        }
                    }
                });
                calculatorViewHolder.viewBinding.includedItemCalculatorAdd.btnCancle.setOnClickListener(new OnSingleClickListener() {
                    @Override
                    public void onSingleClick(View v) {
                        notifyItemRemoved(position);
                    }
                });


            } else if (calculatorViewHolder.item.getType() == TYPE_SUB) {
                calculatorViewHolder.viewBinding.includedItemCalculatorResult.getRoot().setVisibility(View.VISIBLE);
                calculatorViewHolder.viewBinding.includedItemCalculatorResult.btnCancle.setOnClickListener(new OnSingleClickListener() {
                    @Override
                    public void onSingleClick(View v) {
                        notifyItemRemoved(position);
                    }
                });
            }
        }
    }

}
