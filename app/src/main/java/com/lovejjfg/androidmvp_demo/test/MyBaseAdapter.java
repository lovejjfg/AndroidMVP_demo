package com.lovejjfg.androidmvp_demo.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjun on 2016-01-26.
 */
public abstract class MyBaseAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private final Context mContext;

    public LayoutInflater getInflater() {
        if (null != mInflater) {
            return mInflater;
        } else {
            mInflater = LayoutInflater.from(mContext);
        }
        return mInflater;
    }

    private LayoutInflater mInflater;
    private final ArrayList<String> mItems;
    private final MainPresenter mPresenter;
    private View noDataView;
    private View netErrorView;


    public MainPresenter getmPresenter() {
        return mPresenter;
    }

    public ArrayList<String> getmItems() {
        return mItems;
    }

    public MyBaseAdapter(Context context, ArrayList<String> items, MainPresenter presenter) {
        mInflater = LayoutInflater.from(context);
        mItems = items;
        mPresenter = presenter;
        mContext = context;
        if (context instanceof ItemClickListener) {
            setOnItemClickListener((ItemClickListener) context);
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        noDataView = onCreateNoDataView(parent);
        if (null != noDataView) {

            noDataView.setVisibility(View.INVISIBLE);
        }
        netErrorView = onCreateNetErrorView(parent);
        if (null != netErrorView) {
            netErrorView.setVisibility(View.INVISIBLE);
        }

        if (viewType == TYPE_ITEM) {
            return onCreateItemViewHolder(parent, viewType);
        } else if (viewType == TYPE_FOOTER) {


            return onCreateFooterViewHolder(parent, viewType);
        }
        return null;


    }

    public void loadError(String msg) {
        if (null != netErrorView) {
            netErrorView.setVisibility(View.VISIBLE);
        }
        onLoadError();


    }

    public void dataEmpty() {
        if (null != noDataView) {
            noDataView.setVisibility(View.VISIBLE);
        }
        onDataEmpty();
    }


    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
//            view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return new FooterViewHolder(onCreateFooterView(parent));
    }

    protected abstract View onCreateFooterView(ViewGroup parent);

    protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);

    protected abstract View onCreateNetErrorView(ViewGroup parent);

    protected abstract View onCreateNoDataView(ViewGroup parent);

    @Override
    public int getItemViewType(int position) {
        if (position < getDataItemCount()
                && getDataItemCount() > 0) {
            return TYPE_ITEM;
        }
        return TYPE_FOOTER;
    }

    public int getDataItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemCount() {
        return mItems.size() + 1;
    }

    public void addData(List list) {
        if (list != null) {
            mItems.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_FOOTER:
                bindFooterViewHolder(holder);
                break;
            case TYPE_ITEM:
                bindItemViewHolder(holder, position);
                break;
        }

    }

    /**
     * 没有网络数据是
     */
    public abstract void onLoadError();

    public abstract void onDataEmpty();

    public void bindFooterViewHolder(RecyclerView.ViewHolder holder) {

        ((FooterViewHolder) holder).view.setVisibility(getmPresenter().isLoadingMore() ? View.VISIBLE : View.GONE);
//        notifyItemChanged(getItemCount());

    }

    public abstract void bindItemViewHolder(RecyclerView.ViewHolder holder, final int position);


    public ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClickListener(RecyclerView.ViewHolder holder, View view, int position);
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        itemClickListener = listener;
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        View view;


        public FooterViewHolder(View view) {
            super(view);
            this.view = view;
        }

    }
}
