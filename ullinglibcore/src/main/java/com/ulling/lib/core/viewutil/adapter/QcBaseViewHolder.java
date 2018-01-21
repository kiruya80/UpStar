package com.ulling.lib.core.viewutil.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author : KILHO
 * @project : UllingMvpSample
 * @date : 2017. 7. 19.
 * @description :
 * @since :
 */
public abstract class QcBaseViewHolder extends RecyclerView.ViewHolder  {
//        implements OnSingleClickListener.OnSingleClick  {
    private   ViewDataBinding binding;
    public View itemView;
    public QcBaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.binding = DataBindingUtil.bind(itemView);
    }

    public QcBaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj) {
//        binding.setVariable(BR.obj, obj);
//        binding.executePendingBindings();
    }

    public ViewDataBinding getBinding() {
        return binding;
    }


    //    @Override
//    public void onSingleClick(View view) {
//    }

//    @Override
//    public void onClick(View view) {
//        if (view != null) {
//            QcLog.e("onItemClick ==== ");
////            int position = (int) view.getTag();
//            onItemClick(view, 0);
//        }
//    }
}
//public abstract class MyBaseAdapter
//        extends RecyclerView.Adapter<QcBaseViewHolder> {
//    public QcBaseViewHolder onCreateViewHolder(ViewGroup parent,
//                                           int viewType) {
//        LayoutInflater layoutInflater =
//                LayoutInflater.from(parent.getContext());
//        ViewDataBinding binding = DataBindingUtil.inflate(
//                layoutInflater, viewType, parent, false);
//        return new QcBaseViewHolder(binding);
//    }
//
//    public void onBindViewHolder(QcBaseViewHolder holder,
//                                 int position) {
//        Object obj = getObjForPosition(position);
//        holder.bind(obj);
//    }
//    @Override
//    public int getItemViewType(int position) {
//        return getLayoutIdForPosition(position);
//    }
//
//    protected abstract Object getObjForPosition(int position);
//
//    protected abstract int getLayoutIdForPosition(int position);
//}
//public abstract class SingleLayoutAdapter extends MyBaseAdapter {
//    private final int layoutId;
//
//    public SingleLayoutAdapter(int layoutId) {
//        this.layoutId = layoutId;
//    }
//
//    @Override
//    protected int getLayoutIdForPosition(int position) {
//        return layoutId;
//    }
//}