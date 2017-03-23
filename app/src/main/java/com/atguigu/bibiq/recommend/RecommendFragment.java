package com.atguigu.bibiq.recommend;

import android.util.Log;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:推荐页面
 */

public class RecommendFragment extends BaseFragment {

    @Override
    public int getLayoutid() {
        return R.layout.fragment_recommend;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    protected void initData(String json) {
        Log.e("TAG", "推荐数据初始化");
    }
}
