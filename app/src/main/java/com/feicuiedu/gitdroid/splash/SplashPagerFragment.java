package com.feicuiedu.gitdroid.splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.adapter.SplashPagerAdapter;
import com.feicuiedu.gitdroid.pager.Pager2;

import butterknife.Bind;
import butterknife.BindColor;
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
    SplashPagerAdapter adapter;

    @BindColor(R.color.colorRed)
    int red;

    @BindColor(R.color.colorGreen)
    int         green;
    @BindColor(R.color.colorYellow)
    int         yellow;
    @Bind(R.id.content)
    FrameLayout frameLayout;

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

        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pageChangeListener);
        indicator.setViewPager(viewPager);


    }

    private final ViewPager.OnPageChangeListener pageChangeListener = new ViewPager
            .OnPageChangeListener() {
        final ArgbEvaluator evaluator = new ArgbEvaluator();

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == 0) {
                int color = (int) evaluator.evaluate(positionOffset, red, green);
                frameLayout.setBackgroundColor(color);
                return;
            }
            if (position == 1) {
                int color = (int) evaluator.evaluate(positionOffset, red, yellow);
                frameLayout.setBackgroundColor(color);
                return;
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 2) {
                Pager2 pager2= (Pager2) adapter.getView(2);
                pager2.showAnimation();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
