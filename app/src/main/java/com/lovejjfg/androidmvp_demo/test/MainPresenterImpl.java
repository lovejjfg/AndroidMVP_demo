/*
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

package com.lovejjfg.androidmvp_demo.test;

import java.util.ArrayList;

public class MainPresenterImpl implements MainPresenter, OnLoadDateListener {

    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;
    private MyBaseAdapter mAdapter;
    private boolean isLoadingMore;
    private boolean isRefresh = false;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        findItemsInteractor = new FindItemsInteractorImpl();
        if (mainView != null) {
            mainView.showProgress();
        }
        findItemsInteractor.loadItems(this);
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onItemClicked(int position) {
//        if (mainView != null) {
//            mainView.showMessage(mItems.get(position));
//        }
    }

    @Override
    public void onLoadMore() {
        isLoadingMore = true;
        mAdapter.notifyItemChanged(mAdapter.getDataItemCount());
        findItemsInteractor.loadMoreItems(this);

    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onRefresh() {
//        if (null != mItems) {
//            mItems.clear();
//        }
        isRefresh = true;
        if (mainView != null) {
            mainView.onRefreshView(true);
            mainView.showProgress();
        }
        findItemsInteractor.loadItems(this);

    }

    @Override
    public void setAdapter(MyBaseAdapter myAdapter) {
        mAdapter = myAdapter;
    }


    @Override
    public void onRefreshFinished(ArrayList<String> items) {
        isRefresh = false;
        mainView.onRefreshView(false);
        mainView.setItems(items);
        mainView.hideProgress();
    }

    @Override
    public void onLoadMoreFinished(ArrayList<String> items) {
        isLoadingMore = false;
        if (mainView != null) {
//            mItems.addAll(items);
            if (mAdapter.getDataItemCount() > 15) {
                mainView.showMessage("没有更多数据了！！");
                mAdapter.notifyItemChanged(mAdapter.getDataItemCount());
                return;
            }
            mainView.hideProgress();
            mAdapter.addData(items);
        }

    }

    @Override
    public void onLoadError(String msg) {
        isLoadingMore = false;
        isRefresh = false;
        if (null != mAdapter) {
            mAdapter.notifyItemChanged(mAdapter.getDataItemCount());
            mAdapter.loadError(msg);
        }
//        if (mainView != null) {
//            mainView.loadError();
//        }
    }

    @Override
    public void onLoadEmpty() {
        isLoadingMore = false;
        isRefresh = false;
        mainView.hideProgress();

        if (null != mAdapter) {
            mAdapter.notifyItemChanged(mAdapter.getDataItemCount());
            mAdapter.dataEmpty();
        }
        if (mainView != null) {
            mainView.onDataEmpty();
        }
    }

    public void setRefreshing(final boolean isRefreshing) {


    }


    @Override
    public boolean isLoadingMore() {
        return isLoadingMore;
    }
}
