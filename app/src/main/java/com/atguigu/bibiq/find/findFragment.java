package com.atguigu.bibiq.find;

import android.util.Log;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;

import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:发现页面
 */

public class findFragment extends BaseFragment {
    @InjectView(R.id.tv_name)
    TextView tvName;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_find;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    protected void initData(String json) {
        Log.e("TAG", "发现数据初始化");
        tvName.setText("发现页面");
    }
}
