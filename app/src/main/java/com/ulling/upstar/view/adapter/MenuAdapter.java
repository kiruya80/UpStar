package com.ulling.upstar.view.adapter;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.ulling.lib.core.entities.QcBaseItem;
import com.ulling.lib.core.viewutil.adapter.QcBaseViewHolder;
import com.ulling.lib.core.viewutil.adapter.QcRecyclerBaseAdapter;
import com.ulling.upstar.R;
import com.ulling.upstar.view.viewholder.MenuViewHolder;
import com.ulling.upstar.manager.ImageManager;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;

/**
 *
 */
public class MenuAdapter extends QcRecyclerBaseAdapter<Menu> {
    public static final int TYPE_TITLE = 1;
    public static final int TYPE_SUB = 2;

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
        if (holder instanceof MenuViewHolder) {
            final MenuViewHolder menuViewHolder = (MenuViewHolder) holder;

            menuViewHolder.item = (Menu) item;
            if (menuViewHolder.item.getType() == TYPE_TITLE) {
                menuViewHolder.viewBinding.titleRoot.setVisibility(View.VISIBLE);
                menuViewHolder.viewBinding.subRoot.setVisibility(View.GONE);

                menuViewHolder.viewBinding.txtTitleName.setText(menuViewHolder.item.getName());
                menuViewHolder.viewBinding.btnPriceExpened.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != qcRecyclerItemListener) {
                            ArrayList<Integer> index = setItemOpend(menuViewHolder.item.getFragType());
                            if (itemOpened[position]) {
                                menuViewHolder.viewBinding.btnPriceExpened.animate()
                                        .rotation(180)
                                        .setInterpolator(new AccelerateInterpolator())
                                        .setDuration(200)
                                        .start();
                            } else {
                                menuViewHolder.viewBinding.btnPriceExpened.animate()
                                        .rotation(0)
                                        .setInterpolator(new AccelerateInterpolator())
                                        .setDuration(200)
                                        .start();
                            }
                            notifyItemRangeChanged(position + 1, index.size() + 1);
                        }
                    }
                });

                if (position != 0) {
                    menuViewHolder.viewBinding.imgDriver.setVisibility(View.VISIBLE);
                } else {
                    menuViewHolder.viewBinding.imgDriver.setVisibility(View.GONE);
                }
            } else if (menuViewHolder.item.getType() == TYPE_SUB) {
                menuViewHolder.viewBinding.titleRoot.setVisibility(View.GONE);
                menuViewHolder.viewBinding.imgDriver.setVisibility(View.GONE);

                if (!itemOpened[position]) {
                    menuViewHolder.viewBinding.subRoot.setVisibility(View.VISIBLE);
                    menuViewHolder.item = (Menu) item;
                    menuViewHolder.viewBinding.txtSubName.setText(menuViewHolder.item.getName());
                    menuViewHolder.viewBinding.subRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (null != qcRecyclerItemListener) {
                                qcRecyclerItemListener.onItemClick(v, position, menuViewHolder.item);
                            }
                        }
                    });

                    if (menuViewHolder.item.getImgUrl() != null && !"".equals(menuViewHolder.item.getImgUrl())) {
                        ImageManager.getInstance()
                                .setImage(qCon,
                                        menuViewHolder.item.getImgUrl(),
                                        R.mipmap.ic_launcher_round,
                                        R.mipmap.ic_launcher_round,
                                        menuViewHolder.viewBinding.img);

                    } else {
                        menuViewHolder.viewBinding.img.setImageResource(menuViewHolder.item.getImgResourceId());
                    }

                } else {
                    menuViewHolder.viewBinding.subRoot.setVisibility(View.GONE);
                }
            }
        }
    }

    private ArrayList<Integer> setItemOpend(int fragType) {
        ArrayList<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < itemList.size(); i++) {
            Menu item = (Menu) itemList.get(i);
            if (item.getFragType() == fragType) {
                itemOpened[i] = !itemOpened[i];
                index.add(i);
            }
        }
        return index;
    }
}
