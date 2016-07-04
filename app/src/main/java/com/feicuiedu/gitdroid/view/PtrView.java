package com.feicuiedu.gitdroid.view;

/**
 * Created by liuchengyu on 2016/7/1.
 */
public interface PtrView<T> {
    //显示内容视图
    void showContentView();

    //显示错误视图
    void showErroView(String msg);

    //显示空视图
    void showEmptyView();

    //刷新数据
    void refreshData(T t);

    //停止刷新
    void stopRefresh();

    void showMessage();
}
