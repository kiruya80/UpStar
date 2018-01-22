package com.ulling.lib.core.viewutil.adapter;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.lib.core.entities.QcBaseItem;
import com.ulling.lib.core.util.QcLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by P100651 on 2017-07-20.
 * <p>
 * https://github.com/googlesamples/android-architecture-components/blob/master/BasicSample/app/src/main/java/com/example/android/persistence/ui/ProductAdapter.java
 * <p>
 * <p>
 * 해결 및 최적화가 가능한지 체크 리스트
 * <p>
 * 1. 공통으로 사용할 수 있는 데이터 리스트? 2. 데이터 모델이 필요할까
 */
public abstract class QcRecyclerBaseAdapter<T> extends RecyclerView.Adapter<QcBaseViewHolder> {
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_LOAD_FAIL = -999;
    public static final int TYPE_LOAD_PROGRESS = 999;

    public Context qCon;
//    public QcBaseLifeFragment qFragment;
    private AndroidViewModel viewModel;
    public QcRecyclerItemListener qcRecyclerItemListener;
    /**
     * data set
     */
//    public LiveData<List<T>> itemList;
    public List<T> itemList;

    public interface QcRecyclerItemListener<T> {
        //        void onItemClick(View view, int position, T t, String... transName);
        void onItemClick(View view, int position, T t);

        void onItemLongClick(View view, int position, T t);

        void onItemCheck(boolean checked, int position, T t);

        void onDeleteItem(int itemPosition, T t);

        void onReload();
    }
    /**
     * 필수
     * need~ 시작
     */
    /**
     * 1-1.
     * <p>
     * adapter data 초기화
     */
    protected abstract void needInitToOnCreate();

    /**
     * 1-2.
     * 리셋할 데이터 정의
     */
    public abstract void needResetData();

    /**
     * 2.
     * 뷰모델 설정
     */
    public abstract void setViewModel(AndroidViewModel viewModel);


    /**
     * 3.
     * <p>
     * View Type 결정
     */
    protected abstract int needLayoutIdFromItemViewType(int viewType);

    /**
     * 4.
     * onCreateViewHolder 에서 뷰홉더 생성하기
     * @param viewType
     * @param binding
     * @return
     */
    protected abstract QcBaseViewHolder needViewHolderFromItemViewType(int viewType, ViewDataBinding binding);

    /**
     * 실패시
     *
     * @param holder
     * @param position
     * @param item
     */
    protected abstract void needUILoadFailBinding(QcBaseViewHolder holder, int position, QcBaseItem item);

    /**
     * 프로그레스
     *
     * @param holder
     * @param position
     * @param item
     */
    protected abstract void needUILoadProgressBinding(QcBaseViewHolder holder, int position, QcBaseItem item);

    /**
     * 커스텀뷰바인딩
     *
     * @param viewType
     * @param holder
     * @param position
     * @param item
     */
    protected abstract void needUICustomBinding(int viewType, QcBaseViewHolder holder, final int position, QcBaseItem item);

    public boolean isViewModel() {
        if (viewModel == null) {
            return false;
        } else {
            return true;
        }
    }

    public AndroidViewModel getViewModel() {
        return viewModel;
    }

    /**
     * 옵션
     * opt
     *
     * optAnimationResume
     * optAnimationPause
     */
    /**
     * 애니메이션 시작
     */
    protected void optAnimationResume() {
    }

    /**
     * 애니메이션 정지
     */
    protected void optAnimationPause() {
    }


    /**
     * 아답터 시작
     */
    public QcRecyclerBaseAdapter(Context qCon, QcRecyclerItemListener qcRecyclerItemListener) {
        super();
        this.qCon = qCon;
        this.qcRecyclerItemListener = qcRecyclerItemListener;
        needInitToOnCreate();
        needResetData();
    }

    /**
     * 1. 뷰모델 가져오기
     * 2. 이벤트 리스너 달기
     */
    @Override
    public QcBaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        int viewTypeResId = needLayoutIdFromItemViewType(viewType);
        if (viewTypeResId != 0) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewTypeResId, viewGroup, false);

//            return new QcBaseViewHolder(binding);
            return needViewHolderFromItemViewType(viewType, binding);
        } else {
            return null;
        }
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(QcBaseViewHolder holder, int position) {
        if (holder == null) {
            return;
        }
        if (position < 0) {
            return;
        }
        Object object = getItem(position);
        if (object == null) {
            return;
        }
        if (object instanceof QcBaseItem ) {
            QcBaseItem item = (QcBaseItem) object;
            if (item.getType() == TYPE_LOAD_FAIL) {
                QcLog.i("TYPE_LOAD_FAIL =====" + position);
                needUILoadFailBinding(holder, position, item);
            } else if (item.getType() == TYPE_LOAD_PROGRESS) {
                QcLog.i("TYPE_LOAD_PROGRESS =====" + position);
                needUILoadProgressBinding(holder, position, item);
            } else {
                QcLog.i("TYPE_OTHER =====" + position);
                needUICustomBinding(item.getType(), holder, position, item);
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    protected T getItem(int position) {
        if (itemList != null && itemList.size() >= position) {
            return itemList.get(position);
        } else {
            return null;
        }
    }

    /**
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return getItem(position) == null ? 0 : ((QcBaseItem) getItem(position)).getType();
    }

//    public T getItem(int position) {
//        return itemList == null ? 0 : ((QcBaseItem) getItem(position)).getType();
//        if (itemList != null) {
//            return itemList.get(position);
//        } else {
//            return null;
//        }
//    }


    /**
     * 아이템을 하나씩 추가
     */
    public void add(T item_) {
        if (null == item_) {
            return;
        }
        QcLog.e("add == ");
        if (this.itemList == null)
            this.itemList = new ArrayList<>();
        this.itemList.add(item_);
        notifyItemChanged(itemList.size(), 0);
    }

    /**
     * 아이템 전체를 추가
     * 기존 아이템 삭제됨
     * TYPE_DEFAULT으로 설정
     */
    public void addAll(List<T> itemList_) {
        if (null == itemList_ || itemList_.isEmpty()) {
            return;
        }
//        for (T item : itemList) {
//            if (item instanceof QcBaseItem) {
//                QcBaseItem mQcBaseItem = (QcBaseItem) item;
//                mQcBaseItem.setType(QcRecyclerBaseAdapter.TYPE_DEFAULT);
//            }
//        }

        QcLog.e("add == " + itemList_.size());
        this.itemList = itemList_;

        notifyDataSetChanged();
    }

    /**
     * 기존 리스트에 아이템 리스트 추가
     */
    public void addList(List<T> itemList_) {
        if (null == itemList_ || itemList_.isEmpty()) {
            return;
        }
//        for (T item : itemList) {
//            if (item instanceof QcBaseItem) {
//                QcBaseItem mQcBaseItem = (QcBaseItem) item;
//                mQcBaseItem.setType(QcRecyclerBaseAdapter.TYPE_DEFAULT);
//            }
//        }
        if (this.itemList == null)
            this.itemList = new ArrayList<>();
        this.itemList.addAll(itemList_);

        notifyDataSetChanged();
    }

    /**
     * 같은거는 제외하고 추가
     * @param itemList_
     */
    public void addListDiffResult(final List<T> itemList_) {
        if (itemList == null) {
            this.itemList = itemList_;
            notifyItemRangeInserted(0, itemList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return itemList.size();
                }

                @Override
                public int getNewListSize() {
                    return itemList_.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//                    return itemList.get(oldItemPosition).getAnswerId() ==
//                            itemList_.get(newItemPosition).getAnswerId();
                    return itemList.get(oldItemPosition).equals(
                            itemList_.get(newItemPosition));
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    T newItem = itemList_.get(newItemPosition);
                    T oldItem = itemList.get(oldItemPosition);

                    return oldItem.equals(newItem);
                }


                @Nullable
                @Override
                public Object getChangePayload(int oldItemPosition, int newItemPosition) {
                    // Implement method if you're going to use ItemAnimator
                    return super.getChangePayload(oldItemPosition, newItemPosition);
                }
            });

            this.itemList.clear();
            this.itemList.addAll(itemList_);
            diffResult.dispatchUpdatesTo(this);
        }
    }

    public void remove(T item_) {
        if (null == item_) {
            return;
        }
        if (this.itemList != null) {
            QcLog.e("remove == ");
            this.itemList.remove(item_);
            notifyItemChanged(itemList.size(), 0);
        }
    }

    public void addProgress(T item_) {
        QcLog.e("addProgress =====");
        if (getItemCount() == 0) {
            return;
        }
        if (!removeProgress())
            return;
        QcLog.e("addProgress =====");
        add(item_);
    }

    public boolean removeProgress() {
        if (getItemCount() == 0)
            return false;

        QcLog.e("removeProgress == " + itemList.size());
        for (int i = itemList.size() - 1; i >= 0; i--) {
            QcBaseItem mQcBaseItem = (QcBaseItem) itemList.get(i);
            if (mQcBaseItem.getType() == TYPE_LOAD_PROGRESS) {
                itemList.remove(i);
            }
        }
//        int position = 0;
//        for (T item : itemList) {
//            QcBaseItem mQcBaseItem = (QcBaseItem) item;
//            if (mQcBaseItem.getType() == TYPE_LOAD_PROGRESS) {
//                itemList.remove(position);
//            }
//            position++;
//        }
        return true;
    }

    public boolean isProgress() {
        if (getItemCount() == 0)
            return false;

        for (int i = itemList.size() - 1; i >= 0; i--) {
            QcBaseItem mQcBaseItem = (QcBaseItem) itemList.get(i);
            if (mQcBaseItem.getType() == TYPE_LOAD_PROGRESS) {
                return true;
            }
        }
//        for (T item : itemList) {
//            QcBaseItem mQcBaseItem = (QcBaseItem) item;
//            if (mQcBaseItem.getType() == TYPE_LOAD_PROGRESS) {
//                return true;
//            }
//        }
        return false;
    }

    /**
     * @param item_
     */
    public void addLoadFail(T item_) {
        if (getItemCount() == 0) {
            return;
        }
        if (!removeLoadFail())
            return;
        QcLog.e("addLoadFail =====");
        add(item_);
    }

    public boolean removeLoadFail() {
        if (getItemCount() == 0)
            return false;

        QcLog.e("removeLoadFail == ");

        for (int i = itemList.size() - 1; i >= 0; i--) {
            QcBaseItem mQcBaseItem = (QcBaseItem) itemList.get(i);
            if (mQcBaseItem.getType() == TYPE_LOAD_FAIL) {
                itemList.remove(i);
            }
        }
//        int position = 0;
//        for (T item : itemList) {
//            QcBaseItem mQcBaseItem = (QcBaseItem) item;
//            if (mQcBaseItem.getType() == TYPE_LOAD_FAIL) {
//                itemList.remove(position);
//            }
//            position++;
//        }
        return true;
    }

    public boolean isLoadFail() {
        if (getItemCount() == 0)
            return false;

        for (int i = itemList.size() - 1; i >= 0; i--) {
            QcBaseItem mQcBaseItem = (QcBaseItem) itemList.get(i);
            if (mQcBaseItem.getType() == TYPE_LOAD_FAIL) {
                return true;
            }
        }
//        for (T item : itemList) {
//            QcBaseItem mQcBaseItem = (QcBaseItem) item;
//            if (mQcBaseItem.getType() == TYPE_LOAD_FAIL) {
//                return true;
//            }
//        }

        return false;
    }


}