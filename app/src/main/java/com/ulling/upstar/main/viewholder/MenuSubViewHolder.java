package com.ulling.upstar.main.viewholder;

import android.databinding.ViewDataBinding;

import com.ulling.lib.core.viewutil.adapter.QcBaseViewHolder;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.ItemMenuBinding;
import com.ulling.upstar.databinding.ItemMenuSubBinding;
import com.ulling.upstar.model.Menu;

/**
 * Created by KILHO on 2018. 1. 21..
 */
public class MenuSubViewHolder extends QcBaseViewHolder {
    public ItemMenuSubBinding viewBinding;
    public Menu item;

    public MenuSubViewHolder(ViewDataBinding binding) {
        super(binding);
        viewBinding = (ItemMenuSubBinding) getBinding();
    }

}
