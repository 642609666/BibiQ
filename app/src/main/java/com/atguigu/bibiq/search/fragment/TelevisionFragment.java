package com.atguigu.bibiq.search.fragment;

import com.atguigu.bibiq.R;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/26 0026.
 * 功能:搜索界面内影视fragment
 */
public class TelevisionFragment extends com.atguigu.bibiq.base.BaseFragment {
    @Override
    public int getLayoutid() {
        return R.layout.fragment_television;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    protected void initData(String json) {

    }
}
