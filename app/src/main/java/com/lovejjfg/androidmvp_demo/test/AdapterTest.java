package com.lovejjfg.androidmvp_demo.test;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lovejjfg.androidmvp_demo.R;

import java.util.ArrayList;

/**
 * Created by zhangjun on 2016-02-01.
 */
public class AdapterTest extends MyBaseAdapter {
    public AdapterTest(Context context, ArrayList<String> items, MainPresenter presenter) {
        super(context, items, presenter);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footerview, parent, false);
//            view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return new FooterViewHolder(view);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cardview, parent, false);
//            view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return new ItemViewHolder(view);
    }

    @Override
    protected View onCreateNetErrorView(ViewGroup parent) {
        return mInflater.inflate(R.layout.view_net_error, parent, false);
    }

    @Override
    protected View onCreateNoDataView(ViewGroup parent) {
        return mInflater.inflate(R.layout.view_no_date, parent, false);
    }

    @Override
    public void onLoadError() {

    }

    @Override
    public void onDataEmpty() {

    }

    @Override
    public void bindFooterViewHolder(RecyclerView.ViewHolder holder) {

        ((FooterViewHolder)holder).bar.setVisibility(mPresenter.isLoadingMore() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void bindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ItemViewHolder)holder).mButton.setText(mItems.get(position));
        ((ItemViewHolder)holder).mCardView.setOnClickListener(new View.OnClickListener() {
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
}
