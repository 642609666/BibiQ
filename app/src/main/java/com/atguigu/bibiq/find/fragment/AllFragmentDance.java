package com.atguigu.bibiq.find.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.find.adapter.OriginalAdapter;
import com.atguigu.bibiq.find.bean.OriginalBean;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.atguigu.bibiq.view.MyGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/25 0025.
 * 功能:舞蹈排行榜fragment
 */
public class AllFragmentDance extends BaseFragment {
    @InjectView(R.id.gridview)
    MyGridView gridview;
    private OriginalAdapter mAdapter;
    @InjectView(R.id.swi)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_orginal;
    }

    @Override
    public String getChildUrl() {
        return ConstantAddress.BBQ_ALL_DANCE;
    }

    @Override
    protected void initData(String json) {

        //解析数据
        initJson(json);

        //下拉刷新
        refresh();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "数据" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void refresh() {
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FB7299"));
        //设置背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //重新联网请求下载
                initFromNet(ConstantAddress.BBQ_ALL_DANCE);

            }
        });

    }

    private void initFromNet(String json) {
        OkHttpUtils.get()
                .url(json)
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        initJson(response);
                        //关闭进度条
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void initJson(String json) {
        OriginalBean originalBean = JSON.parseObject(json, OriginalBean.class);
        List<OriginalBean.DataBean> data = originalBean.getData();

        if (data != null && data.size() > 0) {
            if (mAdapter == null) {
                mAdapter = new OriginalAdapter(getActivity(), data);
                gridview.setAdapter(mAdapter);
            } else {
                mAdapter.setData(data);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //关闭进度条
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
