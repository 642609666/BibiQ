package com.atguigu.bibiq.tothem;

import android.util.Log;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:追番页面
 */

public class ToThemFragment extends BaseFragment {

    @Override
    public int getLayoutid() {
        return R.layout.fragment_play;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    protected void initData(String json) {
        Log.e("TAG", "追番数据初始化");
    }
}
