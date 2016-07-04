package com.feicuiedu.gitdroid.home;

import android.os.AsyncTask;
import android.util.Log;

import com.feicuiedu.gitdroid.view.PtrPageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * MVP
 * View
 * Presenter
 * <p>
 * Fragment 在做视图工作（实现了ptrpagerview 视图借口， 也就是下拉和上拉功能视图借口）
 * <p>
 * presenter 里做了具体要做的业务（下拉刷新获取数据，上拉加载更多数据），以及视图的触发
 */

public class ReopListPresenter {
    private PtrPageView pageView;
    private static final String TAG = "ReopListPresenter";

    public ReopListPresenter(PtrPageView pageView) {
        this.pageView = pageView;
    }

    //这是下拉刷新视图层的业务逻辑----------------------
    public void loadData() {
        new LoadDataTask().execute();
    }

    public void loadMore() {
        new LoadMoreTask().execute();
    }

    private static int count = 0;

    private final class LoadDataTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            //模拟网络连接
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final int size=new Random().nextInt(40);
            final ArrayList<String> loadDatas=new ArrayList<>();
            for (int i = 0; i < size; i++) {
                loadDatas.add("我是第"+(++count));
                Log.d(TAG, "doInBackground: sdsdffsdf"+count);
            }
            return loadDatas;
        }

        @Override
        protected void onPostExecute(List<String> datas) {
            super.onPostExecute(datas);
            int size=datas.size();
            //模拟空数据时的（视图）情况
            if (size==0){
                pageView.showEmptyView();//listview不可见了，
            }
            //模拟错误数据时的（视图）情况
            else if (size==1){
                pageView.showErroView("unkown erro");
            }else {
                pageView.showContentView();
                pageView.refreshData(datas);
            }
            pageView.stopRefresh();
        }
    }

    private final class LoadMoreTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pageView.showLoadMoreLoading();
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final ArrayList<String> loadDatas=new ArrayList<>();
            for (int i = 0; i < 10; i++) {

                loadDatas.add("我是loadMore"+i);
            }
            return loadDatas;
        }

        @Override
        protected void onPostExecute(List<String> datas) {
            super.onPostExecute(datas);
            pageView.addMoreData(datas);
            pageView.hideLoadMore();
        }
    }
}
