package com.atguigu.bibiq.tothem;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.tothem.bean.ToThemBean;
import com.atguigu.bibiq.utils.ConstantAddress;

import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:追番页面
 */

public class ToThemFragment extends BaseFragment {

    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
   // @InjectView(R.id.swi)
  //  SwipeRefreshLayout swipeRefreshLayout;
    private MyToThemRecyclerView mAdapter;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_tothem;
    }

    @Override
    public String getChildUrl() {
        return ConstantAddress.BBQ_TOFAN;
    }

    @Override
    protected void initData(String json) {
        Log.e("TAG", "追番数据初始化");
        //下拉刷新
        //  refresh();

        //解析数据
        initJson(json);
    }

    private void initJson(String json) {
        ToThemBean toThemBean = JSON.parseObject(json, ToThemBean.class);
        ToThemBean.ResultBean result = toThemBean.getResult();

        //停止刷新
      //  swipeRefreshLayout.setRefreshing(false);
        if (result != null) {
            mAdapter = new MyToThemRecyclerView(getActivity(), result);
            //设置适配器
            recyclerview.setAdapter(mAdapter);
            //设置布局管理
            recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }
    }

    /**
     * 下拉刷新
     */
    private void refresh() {
      //  swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置颜色
      //  swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.RED);
        //设置背景颜色
     //   swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_orange_dark);
        //下拉刷新
     //   swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                initJson("");
//            }
//        });

    }
}
