package com.ulling.upstar.main.viewholder;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.ulling.lib.core.viewutil.adapter.QcBaseViewHolder;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.ItemMarketPriceBinding;
import com.ulling.upstar.databinding.ItemMenuBinding;
import com.ulling.upstar.model.Menu;

/**
 * Created by KILHO on 2018. 1. 21..
 */
public class MenuViewHolder extends QcBaseViewHolder {
    public ItemMenuBinding viewBinding;
    public Menu item;

    public MenuViewHolder(ViewDataBinding binding) {
        super(binding);
        viewBinding = (ItemMenuBinding) getBinding();
    }

}
