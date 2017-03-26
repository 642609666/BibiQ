package com.atguigu.bibiq.search.fragment;

import com.atguigu.bibiq.R;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/26 0026.
 * 功能:搜索界面内UP主fragment
 */
public class UPFragment extends com.atguigu.bibiq.base.BaseFragment {
    @Override
    public int getLayoutid() {
        return R.layout.fragment_up;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    protected void initData(String json) {

    }
}
