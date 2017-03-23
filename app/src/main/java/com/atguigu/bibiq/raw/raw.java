package com.atguigu.bibiq.raw;

import com.atguigu.bibiq.R;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/24 0024.
 * 功能:动态页面
 */
public class raw extends com.atguigu.bibiq.base.BaseFragment {
    @Override
    public int getLayoutid() {
        return R.layout.fragment_raw;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    protected void initData(String json) {

    }
}
