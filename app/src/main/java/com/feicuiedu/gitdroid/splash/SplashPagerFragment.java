package com.feicuiedu.gitdroid.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.adapter.ViewPagerAdapter;

import java.io.BufferedReader;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by liuchengyu on 2016/6/29.
 */
public class SplashPagerFragment extends Fragment {
    @Bind(R.id.viewPager)
    ViewPager       viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    ViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter=new ViewPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);

    }
}
