package com.feicuiedu.gitdroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feicuiedu.gitdroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by liuchengyu on 2016/6/30.
 */
public class RepoListFragment extends Fragment {
    //下拉刷新
    @Bind(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @Bind(R.id.lvRepos)
    ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> datas=new ArrayList<>();
    private static int count;

    public static RepoListFragment getInstance(String language){
        RepoListFragment fragment=new RepoListFragment();
        Bundle args=new Bundle();
        args.putSerializable("key",language);
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
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        adapter.addAll(datas);
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loaData(20);
            }
        });
    }

    private void loaData(final int size) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                datas.clear();
                try {
                    for (int i = 0; i <= size; i++) {
                        count++;
                        datas.add("我是"+count);
                    }
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ptrClassicFrameLayout.post(new Runnable() {
                    @Override
                    public void run() {

                        //添加刷新数据
                        adapter.clear();
                        adapter.addAll(datas);
                        adapter.notifyDataSetChanged();
                        //下拉刷新完成
                        ptrClassicFrameLayout.refreshComplete();
                    }
                });

            }
        }).start();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
