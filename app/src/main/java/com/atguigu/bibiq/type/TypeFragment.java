package com.atguigu.bibiq.type;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.type.bean.TypeBean;
import com.atguigu.bibiq.utils.ConstantAddress;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:分区页面
 */

public class TypeFragment extends BaseFragment {

    @InjectView(R.id.recyclerview_type)
    RecyclerView recyclerviewType;

    private TypeRecylerView mTypeRecylerView;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_type;
    }

    @Override
    public String getChildUrl() {
        return ConstantAddress.BBQ_TYPE;
    }

    @Override
    protected void initData(String json) {
        Log.e("TAG", "分区数据初始化");
        TypeBean typeBean = JSON.parseObject(json, TypeBean.class);
        //得到数据
        List<TypeBean.DataBean> data = typeBean.getData();
        if (data != null && data.size() > 0) {
            //设置适配器
            mTypeRecylerView = new TypeRecylerView(getActivity(),data);
            //设置适配器
            recyclerviewType.setAdapter(mTypeRecylerView);
            //设置布局管理器
            recyclerviewType.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }

    }

}
