package com.ulling.upstar.main.adapter;

import android.animation.ValueAnimator;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.entities.QcBaseItem;
import com.ulling.lib.core.util.QcLog;
import com.ulling.lib.core.viewutil.adapter.QcBaseViewHolder;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.main.viewholder.MenuSubViewHolder;
import com.ulling.upstar.main.viewholder.MenuViewHolder;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;
import java.util.List;


//public class MenuAdapter extends RecyclerView.Adapter<QcBaseViewHolder> {
public class MenuAdapter extends QcRecyclerBaseAdapter<Menu> {
    public static final int TYPE_MENU_MAIN = 0;
    public static final int TYPE_MENU_MAIN_SUB = 1;

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
        if (TYPE_MENU_MAIN == viewType) {
            return new MenuViewHolder(binding);

        } else if (TYPE_MENU_MAIN_SUB == viewType) {
            return new MenuSubViewHolder(binding);

        } else {
            return null;
        }
    }

    @Override
    protected int needLayoutIdFromItemViewType(int viewType) {
        if (TYPE_MENU_MAIN == viewType) {
            return R.layout.item_menu;

        } else if (TYPE_MENU_MAIN_SUB == viewType) {
            return R.layout.item_menu_sub;

        } else {
            return 0;
        }
    }

    @Override
    protected void needUILoadFailBinding(QcBaseViewHolder holder, int position, QcBaseItem item) {

    }

    @Override
    protected void needUILoadProgressBinding(QcBaseViewHolder holder, int position, QcBaseItem item) {

    }

    @Override
    protected void needUICustomBinding(int viewType, QcBaseViewHolder holder, final int position, QcBaseItem item) {
        if (TYPE_MENU_MAIN == viewType && holder instanceof MenuViewHolder) {
            final MenuViewHolder menuViewHolder = (MenuViewHolder) holder;

            menuViewHolder.item = (Menu) item;
            menuViewHolder.viewBinding.name.setText(menuViewHolder.item.getName());
//            menuViewHolder.viewBinding.root.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (null != qcRecyclerItemListener) {
//                        qcRecyclerItemListener.onItemClick(v, position, menuViewHolder.item);
//                    }
//                }
//            });
            menuViewHolder.viewBinding.btnExpened.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        } else if (TYPE_MENU_MAIN_SUB == viewType && holder instanceof MenuSubViewHolder) {
            final MenuSubViewHolder menuSubViewHolder = (MenuSubViewHolder) holder;

//            menuSubViewHolder.item = (Menu) item;
//            menuSubViewHolder.viewBinding.name.setText(menuSubViewHolder.item.getSubMenu().getName());
//            menuSubViewHolder.viewBinding.root.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (null != qcRecyclerItemListener) {
//                        qcRecyclerItemListener.onItemClick(v, position, menuSubViewHolder.item);
//                    }
//                }
//            });
        }
    }
}
