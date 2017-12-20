package com.example.wangyuan.viewpagerdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangyuan on 2017/12/18.
 */

public abstract class BaseFragment extends Fragment {
    boolean isInit;
    private Context mContext;
    private View view;
    private boolean isLoad;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadDate();
    }

    /**
     * 懒加载
     */

    protected void lazyLoad() {
        initData();
    }

    protected abstract void initData();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getResourcesId(), container, false);//此处加载布局
        isInit = true;//布局加载完成标识符
        initView(view);//控件查找相应操作
        isCanLoadDate();//加载数据
        return view;
    }

    protected  void isCanLoadDate(){
        if (!isInit) {//未完成布局加载  拦截返回
            return;
        }

        if (getUserVisibleHint()) {// 当前fragment是否可见  可见加载
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {//=不可见  停止加载操作
                stopLoad();
            }
        }
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit =false;//销毁界面  初始化参数
        isLoad = false;
    }

    protected  void stopLoad(){};

    protected abstract void initView(View view);

    protected abstract int getResourcesId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
    }



}
