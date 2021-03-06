package com.atguigu.bibiq.base;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:
 */

public abstract class BaseHolder<T> {

    public final Context mContext;
    private View rootView;
    private T t;

    public T getT() {
        return t;
    }

    /**
     * 设置控件数据
     * setChildData();
     * T t 得到对应的数据
     */
    public void setData(T t) {
        this.t = t;
        setChildData();
    }


    public BaseHolder(Context context) {
        this.mContext = context;
        rootView = initView();
        ButterKnife.inject(this, rootView);
        rootView.setTag(this);
    }


    public View getView() {
        return rootView;
    }

    /**
     * 设置控件数据
     * setChildData();
     * T t 得到对应的数据
     */
    protected abstract void setChildData();

    /**
     * 初始化view布局
     * UiUtils.getView(R.layout.adapter_invest_all);
     */
    protected abstract View initView();
}

