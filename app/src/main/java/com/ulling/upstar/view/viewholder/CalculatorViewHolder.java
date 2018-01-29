package com.ulling.upstar.view.viewholder;

import android.databinding.ViewDataBinding;

import com.ulling.lib.core.viewutil.adapter.QcBaseViewHolder;
import com.ulling.upstar.databinding.ItemCalculatorBinding;
import com.ulling.upstar.model.CoinCalculator;

/**
 * Created by KILHO on 2018. 1. 21..
 */
public class CalculatorViewHolder extends QcBaseViewHolder {
    public ItemCalculatorBinding viewBinding;
    public CoinCalculator item;

    public CalculatorViewHolder(ViewDataBinding binding) {
        super(binding);
        viewBinding = (ItemCalculatorBinding) getBinding();
    }

}
