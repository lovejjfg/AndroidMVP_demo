package com.lovejjfg.androidmvp_demo.test;/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import com.lovejjfg.androidmvp_demo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener, MyAdapter.ItemClickListener, View.OnClickListener {

    private RecyclerView mRecycleView;
    private MainPresenter presenter;
    private SwipeRefreshLayout mSwip;
    private ViewStub mVsNoData;
    private ViewStub mVsNetError;
    private View noDataView;
    //    private View noDataView;
//    private RefreshRecycleView mRefreshRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noDataView = findViewById(R.id.vs_no_date);
//        mRefreshRecycleView = (RefreshRecycleView) findViewById(R.id.rrc);
        mRecycleView = (RecyclerView) findViewById(R.id.rv);
        mSwip = (SwipeRefreshLayout) findViewById(R.id.srl);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        mRecycleView.setLayoutManager(manager);
        mSwip.setOnRefreshListener(this);
        presenter = new MainPresenterImpl(this);
        mRecycleView.addOnScrollListener(new InfiniteScrollListener(manager, presenter));


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        onRefreshView(true);
        if (noDataView != null) {
            noDataView.setVisibility(View.INVISIBLE);
        }
//        mRecycleView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        onRefreshView(false);
        if (noDataView != null) {
            noDataView.setVisibility(View.INVISIBLE);
        }
//        mRecycleView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(ArrayList<String> items) {
        mRecycleView.setVisibility(View.VISIBLE);
        MyBaseAdapter myAdapter = new AdapterTest(this, items, presenter);
        presenter.setAdapter(myAdapter);
        mRecycleView.setAdapter(myAdapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadError() {
    }

    @Override
    public void onDataEmpty() {
//        if (noDataView == null) {
//            noDataView = mVsNoData.inflate();
//        }
        noDataView.setVisibility(View.VISIBLE);
        mRecycleView.setVisibility(View.INVISIBLE);
        noDataView.setOnClickListener(this);

    }

    @Override
    public void onRefreshView(final boolean isRefreshing) {
        mSwip.setRefreshing(isRefreshing);
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Override
    public void onItemClickListener(RecyclerView.ViewHolder holder, View view, int position) {
        presenter.onItemClicked(position);
    }


    @Override
    public void onClick(View v) {
        if (v == noDataView) {
            presenter.onRefresh();
        }
    }
}
