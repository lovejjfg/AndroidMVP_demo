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

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindItemsInteractorImpl implements FindItemsInteractor {

    private ArrayList<String> list;

    @Override
    public void loadItems(final OnLoadDateListener listener) {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(1800);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int i = ((int) (Math.random()*10))%2;
                        switch (i) {
                            case 1:
                                listener.onRefreshFinished(createArrayList());
                                break;
                            case 0:
                                listener.onLoadEmpty();
                                break;
                        }
                    }
                });

            }
        }.start();
    }

    @Override
    public void loadMoreItems(final OnLoadDateListener listener) {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(3000);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int i = ((int) (Math.random()*10))%2;
                        switch (i) {
                            case 0:
                                listener.onLoadMoreFinished(createMoreList());
                                break;
                            case 1:
                                listener.onLoadMoreFinished(null);
                                break;
                        }
                    }
                });

            }
        }.start();
    }

    private ArrayList<String> createArrayList() {
        ArrayList<String> list = new ArrayList<>();
        List<String> strings = Arrays.asList(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
                "Item 9",
                "Item 10"
        );
        list.addAll(strings);
        return list;
    }

    private ArrayList<String> createMoreList() {
        list = new ArrayList<>();
        list.addAll(Arrays.asList(
                "Item 11",
                "Item 12",
                "Item 13",
                "Item 14",
                "Item 15",
                "Item 16",
                "Item 17",
                "Item 18",
                "Item 19",
                "Item 20"
        ));
        return list;
    }

}
