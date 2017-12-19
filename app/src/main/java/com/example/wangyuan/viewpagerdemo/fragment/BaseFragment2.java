package com.example.wangyuan.viewpagerdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment2 extends Fragment {
    private View mLayoutView;
    public Context mContext;
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    /**
     * 初始化标志位(只有View初始化之后这个才为true)
     */
    protected boolean isPrepared;

    /**
     * 初始化布局
     */
    public abstract int getLayoutRes();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {//可见
            isVisible = true;
            onVisible();
        } else {//不可见
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        Log.i("lazyLoadData", "lazyLoadData: ");
        lazyLoadData();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
    }


    /**
     * 只用于网络操作
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoadData();

    /**
     * 初始化View的操作
     *
     * @param view
     */
    protected abstract void initViewAndData(View view);

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutView = getCreateView(inflater, container);
        mContext = getActivity();
        //View初始化了
        initViewAndData(mLayoutView);
        isPrepared=true;
        initPresenter();
        lazyLoadData();
        Log.i("onCreateView", "onCreateView: ");
        return mLayoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared =false;//销毁界面  初始化参数
        isVisible = false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    /**
     * 获取Fragment布局文件的View
     *
     * @param inflater
     * @param container
     * @return
     */
    private View getCreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutRes(), container, false);
    }



}
