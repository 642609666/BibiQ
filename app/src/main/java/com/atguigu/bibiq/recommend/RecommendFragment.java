package com.atguigu.bibiq.recommend;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.recommend.bean.RecommendBean;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.atguigu.bibiq.view.MyListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:推荐页面
 */

public class RecommendFragment extends BaseFragment {

    @InjectView(R.id.listview)
    MyListView listview;
    @InjectView(R.id.swi)
    SwipeRefreshLayout swipeRefreshLayout;
    private List<RecommendBean.DataBean> mData;

    private MyRecommendAdapter mAdapter;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_recommend;
    }

    @Override
    public String getChildUrl() {
        return ConstantAddress.BBQ_RECOMMERD;
    }

    @Override
    protected void initData(String json) {
        //下拉刷新
        refresh();
        Log.e("TAG", "推荐数据初始化");
        initJson(json);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "position==" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void refresh() {
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        // 设置颜色
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.RED);
        //设置背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_orange_dark);
        // 下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initFromNet();
            }
        });

    }

    private void initFromNet() {
        OkHttpUtils.get()
                .url(ConstantAddress.BBQ_RECOMMERD)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "推荐加载数据成失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "推荐加载数据成功");
                        initJson(response);
                    }
                });
    }

    private void initJson(String json) {
        RecommendBean recommendBean = JSON.parseObject(json, RecommendBean.class);
        mData = recommendBean.getData();
        Log.e("TAG", "推荐页面" + json);
        if (mData != null) {
            swipeRefreshLayout.setRefreshing(false);

            mAdapter = new MyRecommendAdapter(getActivity(), mData);

            listview.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }
}
