package com.atguigu.bibiq.type;

import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;

import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:分区页面
 */

public class TypeFragment extends BaseFragment {
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
        tvName.setText("分区页面");
    }
}
