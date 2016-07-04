package com.feicuiedu.gitdroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.FooterView;
import com.feicuiedu.gitdroid.view.PtrPageView;
import com.feicuiedu.gitdroid.view.PtrView;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by liuchengyu on 2016/6/30.
 */
public class RepoListFragment extends Fragment implements PtrPageView {
    private static final String TAG = "RepoListFragment";
    //下拉刷新
    @Bind(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @Bind(R.id.lvRepos)
    ListView              listView;
    @Bind(R.id.emptyView)
    TextView              emptyView;
    @Bind(R.id.errorView)
    TextView              errorView;

    private ArrayAdapter<String> adapter;

   // private List<String> datas = new ArrayList<>();

    private static int count;

    private ReopListPresenter presenter;

    private FooterView footerView;//上拉加载更多的视图

    public static RepoListFragment getInstance(String language) {
        RepoListFragment fragment = new RepoListFragment();
        Bundle           args     = new Bundle();
        args.putSerializable("key", language);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter=new ReopListPresenter(this);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);

        //adapter.addAll(datas);
        //Log.d(TAG, "onViewCreated: ssssssssss"+datas);
        listView.setAdapter(adapter);
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.loadData();
                Log.d(TAG, "onRefreshBegin: aaaaaaaa");
            }
        });
        footerView = new FooterView(getContext());
        //上拉加载更多（listview 滑动到最后的位置了，就可以loadmore）
        Mugen.with(listView, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                Toast.makeText(getContext(), "loadmore", Toast.LENGTH_SHORT).show();
                presenter.loadMore();
            }

            //是否正在加载，此方法用来避免重复加载
            @Override
            public boolean isLoading() {
                return listView.getFooterViewsCount() > 0 && footerView.isLoading();
            }

            //是否所有数据都已加载
            @Override
            public boolean hasLoadedAllItems() {
                return listView.getFooterViewsCount() > 0 && footerView.isComplete();
            }
        }).start();
    }

    @OnClick({R.id.emptyView, R.id.errorView})
    public void autoRefresh() {
        ptrClassicFrameLayout.autoRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //这是上拉加载更多视图实现---------------------------------------
    @Override
    public void addMoreData(List<String> datas) {
        adapter.addAll(datas);
    }

    @Override
    public void hideLoadMore() {
        listView.removeFooterView(footerView);
    }

    @Override
    public void showLoadMoreLoading() {
        if (listView.getFooterViewsCount() == 0) {
            listView.addFooterView(footerView);
        }
        footerView.showLoading();
    }

    @Override
    public void showLoadMoreErro(String msg) {
        if (listView.getFooterViewsCount() == 0) {
            listView.addFooterView(footerView);
        }
        footerView.showError(msg);
    }

    @Override
    public void showLoadMoreEnd() {

        if (listView.getFooterViewsCount()==0){
            listView.addFooterView(footerView);
        }
        footerView.showComplete();
    }

    //这是下拉刷新视图的实现
    @Override
    public void showContentView() {
        ptrClassicFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showErroView(String msg) {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void refreshData(List<String> datas) {
        adapter.clear();
        adapter.addAll(datas);
        Log.d(TAG, "refreshData: dddddd"+datas);
    }

    @Override
    public void stopRefresh() {
        ptrClassicFrameLayout.refreshComplete();//下拉刷新完成
    }

    @Override
    public void showMessage() {

    }
    //
}
