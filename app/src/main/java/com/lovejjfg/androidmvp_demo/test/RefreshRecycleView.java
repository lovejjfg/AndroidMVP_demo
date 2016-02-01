package com.lovejjfg.androidmvp_demo.test;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.lovejjfg.androidmvp_demo.R;


/**
 * Created by zhangjun on 2016-02-01.
 * todo 为什么填充了显示不出来
 */
public class RefreshRecycleView extends LinearLayout {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public RefreshRecycleView(Context context) {
        this(context, null);
    }

    public RefreshRecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_cardview, null);
        View viewById = view.findViewById(R.id.card_view);
//        mSwipeRefreshLayout = new SwipeRefreshLayout(getContext());
//        mRecyclerView = new RecyclerView(getContext());
//        mSwipeRefreshLayout.addView(mRecyclerView, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        addView(mSwipeRefreshLayout, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

//        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        initListener();
//        View netError = view.findViewById(R.id.vs_net_error);
//        View noData = view.findViewById(R.id.vs_no_date);
    }

    private void initListener() {
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        if (null != listener) {
            mSwipeRefreshLayout.setOnRefreshListener(listener);
        }
    }

    public void setAdapter(MyBaseAdapter myAdapter) {
        mRecyclerView.setAdapter(myAdapter);
    }

    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }

    public void setLayoutManager(GridLayoutManager manager) {
        if (null == manager) {
            throw new RuntimeException("请设置manager!");
        }
        mRecyclerView.setLayoutManager(manager);

    }

    public void addOnScrollListener(InfiniteScrollListener infiniteScrollListener) {
        if (null != infiniteScrollListener) {
            mRecyclerView.addOnScrollListener(infiniteScrollListener);
        }
    }

//    @Override
//    public void onRefresh() {
//
//    }
}
