package com.atguigu.bibiq.type;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.type.bean.TypeBean;
import com.atguigu.bibiq.type.bean.TypeHandBean;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import okhttp3.Call;

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
    /**
     * 头部数据
     */
    private List<TypeBean.DataBean> mHandData;

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
        mHandData = typeBean.getData();
        initFromNet();
        //得到数据

    }

    /**
     * 解析分区除了头部的多种数据
     */
    private void initFromNet() {
        OkHttpUtils.get()
                .url(ConstantAddress.BBQ_TYPE_HAND)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "分区请求失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "分区请求成功");
                        if (mHandData != null && mHandData.size() > 0) {
                            //设置适配器
                            mTypeRecylerView = new TypeRecylerView(getActivity(), mHandData);
                        }
                        TypeHandBean typeHandBean = JSON.parseObject(response, TypeHandBean.class);
                        //得到头部数据
                        List<TypeHandBean.DataBean> data = typeHandBean.getData();
                        //适配器得到头部数据
                        mTypeRecylerView.setHandData(data);
                        //设置适配器
                        recyclerviewType.setAdapter(mTypeRecylerView);
                        //设置布局管理器
                        recyclerviewType.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    }
                });

    }

}
