package com.feicuiedu.gitdroid;

import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.feicuiedu.gitdroid.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {
    // https://github.com//GitDroid.git
    // ButterKnife 库
    // compile 'com.jakewharton:butterknife:7.0.1'
    @Bind(R.id.drawerLayout)DrawerLayout drawerLayout;//抽屉(包含内容+侧滑菜单)
    @Bind(R.id.navigationView)
    NavigationView navigationView;//侧滑菜单视图
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        activityUtils=new ActivityUtils(this);
        //设置navigationMenuView监听器
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.github_hot_repo:
                activityUtils.showToast(R.string.hot_repo);
                break;
            case R.id.tips_daily:
                activityUtils.showToast(R.string.tips_daily);
                break;
            case R.id.arsenal_my_repo:
                activityUtils.showToast(R.string.my_repo);
                break;
            case R.id.tips_share:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}