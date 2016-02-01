package com.lovejjfg.androidmvp_demo.test;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lovejjfg.androidmvp_demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjun on 2016-01-26.
 */
public class MyAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private final LayoutInflater mInflater;
    private final ArrayList<String> mItems;
    private boolean isFinished;
    private final MainPresenter mPresenter;


    public MyAdapter(Context context, ArrayList<String> items, MainPresenter presenter) {
        mInflater = LayoutInflater.from(context);
        mItems = items;
        mPresenter = presenter;
        setOnItemClickListener((ItemClickListener) context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cardview, parent, false);
//            view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footerview, parent, false);
//            view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
        return null;

//        View view = mInflater.inflate(R.layout.list_item_cardview, parent, false);
//        return new ItemViewHolder(view);

    }

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
                bindFooterViewHolder((FooterViewHolder) holder);
                break;
            case TYPE_ITEM:
                bindItemView((ItemViewHolder) holder, position);
                break;
        }

    }

    private void bindFooterViewHolder(FooterViewHolder holder) {
        Log.e("isLoading--->", mPresenter.isLoadingMore() + "");
        holder.bar.setVisibility(mPresenter.isLoadingMore() ? View.VISIBLE : View.GONE);
    }

    private void bindItemView(final ItemViewHolder holder, final int position) {
        holder.mButton.setText(mItems.get(position));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClickListener(holder, holder.itemView, position);
            }
        });
    }




    class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        Button mButton;

        public ItemViewHolder(View view) {
            super(view);
            mButton = (Button) view.findViewById(R.id.bt);
            mCardView = (CardView) view.findViewById(R.id.card_view);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar bar;

        public FooterViewHolder(View view) {
            super(view);
            bar = (ProgressBar) view.findViewById(R.id.progressbar);
        }

    }

    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClickListener(RecyclerView.ViewHolder holder, View view, int position);
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        itemClickListener = listener;
    }
}
