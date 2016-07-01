package com.feicuiedu.gitdroid.home;

import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {
    // https://github.com//GitDroid.git
    // ButterKnife 库
    // compile 'com.jakewharton:butterknife:7.0.1'
    @Bind(R.id.drawerLayout) DrawerLayout   drawerLayout;//抽屉(包含内容+侧滑菜单)
    @Bind(R.id.navigationView)
                             NavigationView navigationView;//侧滑菜单视图
    @Bind(R.id.toolbar)
                             Toolbar        toolber;
    private ActivityUtils activityUtils;
    private MenuItem menuItem;

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
        //actionbar 处理
        setSupportActionBar(toolber);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolber,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        //默认第一个menu项为选中
//        menuItem=navigationView.getMenu().findItem(R.id.github_hot_repo);
//        menuItem.se
        HotRepoFragment hotRepoFragment=new HotRepoFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.container,hotRepoFragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        if (menuItem.isChecked()){
//            menuItem.setChecked(false);
//        }
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
//            case R.id.tips_share:
//                //测试代码 按下分享 关闭左侧滑菜单navigationview
//                drawerLayout.closeDrawer(GravityCompat.START);
//                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //如navigationview 是开的->关闭
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}