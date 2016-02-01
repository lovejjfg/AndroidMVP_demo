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

public interface MainPresenter  {

    void onResume();

    void onItemClicked(int position);

    void onLoadMore();

    void onDestroy();

    void onRefresh();

    void setAdapter(MyBaseAdapter myAdapter);

    /**
     *
     * @return true:正在加载中  false：加载完成
     */
    boolean isLoadingMore();
}
