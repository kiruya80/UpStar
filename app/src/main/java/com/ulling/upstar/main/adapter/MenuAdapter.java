package com.ulling.upstar.main.adapter;

import android.animation.ValueAnimator;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.entities.QcBaseItem;
import com.ulling.lib.core.util.QcLog;
import com.ulling.lib.core.viewutil.adapter.QcBaseViewHolder;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.main.viewholder.MenuViewHolder;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;
import java.util.List;


//public class MenuAdapter extends RecyclerView.Adapter<QcBaseViewHolder> {
public class MenuAdapter extends QcRecyclerBaseAdapter<Menu> {

    private static final float MAX_MARGIN = 16;
    private static final float MIN_MARGIN = 2;

    private ValueAnimator marginAnimator = ValueAnimator.ofFloat(MAX_MARGIN, MIN_MARGIN); // replace with dimens

    public MenuAdapter(Context qCon, QcRecyclerItemListener qcRecyclerItemListener) {
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

        return new MenuViewHolder(binding);
    }

    @Override
    protected int needLayoutIdFromItemViewType(int viewType) {

        return R.layout.item_menu;
    }

    @Override
    protected void needUILoadFailBinding(QcBaseViewHolder holder, int position, QcBaseItem item) {

    }

    @Override
    protected void needUILoadProgressBinding(QcBaseViewHolder holder, int position, QcBaseItem item) {

    }

    @Override
    protected void needUICustomBinding(int viewType, QcBaseViewHolder holder, final int position, QcBaseItem item) {
        if ( holder instanceof MenuViewHolder) {
            final MenuViewHolder menuViewHolder = (MenuViewHolder) holder;

            menuViewHolder.item = (Menu) item;
            QcLog.e("item == " + item.toString());
            menuViewHolder.viewBinding.name.setText(menuViewHolder.item.getName());
            menuViewHolder.viewBinding.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != qcRecyclerItemListener) {
                        qcRecyclerItemListener.onItemClick(v, position, menuViewHolder.item);
                    }
                }
            });

            menuViewHolder.viewBinding.img.setImageResource(menuViewHolder.item.getImgUrl());

//            Glide.with(qCon)
//                    .load(url)
//                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                    .error(defaultImageResource)
//                    .placeholder(defaultImageResource)
//                    .dontAnimate()
//                    .into(view);
        }
    }
}
