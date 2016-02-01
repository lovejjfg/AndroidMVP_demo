/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lovejjfg.androidmvp_demo.test;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * A scroll listener for RecyclerView to load more items as you approach the end.
 * <p/>
 * Adapted from https://gist.github.com/ssinss/e06f12ef66c51252563e
 */
public  class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    // The minimum number of items remaining before we should loading more.
    private static final int VISIBLE_THRESHOLD = 4;

    private final GridLayoutManager layoutManager;
    private final MainPresenter dataLoading;

    public InfiniteScrollListener(GridLayoutManager layoutManager, MainPresenter dataLoading) {
        this.layoutManager = layoutManager;
        this.dataLoading = dataLoading;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        final int visibleItemCount = recyclerView.getChildCount();
        final int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        Log.e("lastPos----->", lastVisibleItemPosition+"");
        Log.e("totalItemCount----->", totalItemCount+"");
        Log.e("visibleItemCount----->", visibleItemCount+"");

        final int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        Log.e("dy::", dy + "");
        if (!dataLoading.isLoadingMore() &&
                (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD) && dy > 0) {
            dataLoading.onLoadMore();
        }
    }
}
