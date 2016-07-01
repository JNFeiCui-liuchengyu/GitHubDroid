package com.feicuiedu.gitdroid.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuchengyu on 2016/6/30.
 */
public class HotRepoPagerAdapter extends FragmentPagerAdapter{
    private final List<String> language;
    public HotRepoPagerAdapter(FragmentManager fm) {
        super(fm);
        language=new ArrayList<>();
        language.add("java 1");
        language.add("java 2");
        language.add("java 3");
        language.add("java 4");
        language.add("java 5");
        language.add("java 6");
        language.add("java 7");

    }

    @Override
    public Fragment getItem(int position) {
        return RepoListFragment.getInstance(language.get(position));
    }

    @Override
    public int getCount() {
        return language.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return language.get(position);
    }
}
