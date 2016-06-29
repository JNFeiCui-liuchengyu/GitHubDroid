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
import android.widget.ImageView;

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

    @Bind(R.id.layoutPhone)
    FrameLayout layoutPhone;
    @Bind(R.id.ivPhoneBlank)
    ImageView   ivPhoneBlank;
    @Bind(R.id.ivPhone)
    ImageView   ivPhone;

    @BindColor(R.color.colorRed)
    int         red;
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
        viewPager.addOnPageChangeListener(phoneViewHandler);
        indicator.setViewPager(viewPager);

    }

    private final ViewPager.OnPageChangeListener phoneViewHandler = new ViewPager
            .OnPageChangeListener() {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //当viewpager在第二个页面和第三个页面之间时(总是1) 手机要和viewpager一起平移
            if (position == 0) {
                float scale = 0.3f + positionOffset * 0.7f;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                ivPhone.setAlpha(positionOffset);
                int scroll = (int) ((positionOffset - 1) * 300);
                layoutPhone.setTranslationX(scroll);
                return;
            }
            if (position == 1) {
                layoutPhone.setTranslationX(-positionOffsetPixels);
                return;
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

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
                Pager2 pager2 = (Pager2) adapter.getView(2);
                pager2.showAnimation();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
