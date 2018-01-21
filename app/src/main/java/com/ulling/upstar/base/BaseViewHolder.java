package com.ulling.upstar.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by KILHO on 2018. 1. 21..
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract int needItemLayoutId();
}
