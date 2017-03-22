package com.atguigu.bibiq.home;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.bean.HomeBean;
import com.atguigu.bibiq.bean.HomeStreamingBean;
import com.atguigu.bibiq.home.adapter.HomeRecyclerView;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.atguigu.bibiq.utils.LoadNet;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:直播页面
 */

public class HomeFragment extends BaseFragment {

    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    @InjectView(R.id.swi)
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeRecyclerView mRecyclerView;
    /**
     * 主页解析的数据除了直播
     */
    private HomeBean.DataBean mData;
    /**
     * 主页直播的数据
     */
    private List<HomeStreamingBean.DataBean> mHomeStreamingBeanData;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_play;
    }

    @Override
    public String getChildUrl() {
        return ConstantAddress.BBQ_HOME;
    }

    @Override
    protected void initData(String json) {
        Log.e("TAG", "直播数据初始化");
        //联网请求数据
        initFromNet(json);
        //下拉刷新
        refresh();
    }

    /**
     * 下拉刷新
     */
    private void refresh() {
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置颜色
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.RED);
        //设置背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_orange_dark);
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initFromNet("");

            }
        });

    }


    /**
     * 联网请求数据
     *
     * @param json
     */
    private void initFromNet(final String json) {
        /**
         * 解析直播的数据
         */
        LoadNet.getDataNet(ConstantAddress.STREAMING, new LoadNet.OnGetNet() {
            @Override
            public void onSuccess(String content) {
                try {
                    //停止刷新
                    swipeRefreshLayout.setRefreshing(false);
                    Log.e("TAG", "主页数据请求成功");
                    //处理主页的json数据
                    HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
                    //得到主页数据
                    if (homeBean != null) {
                        mData = homeBean.getData();
                    }

                    //设置数据
                    if (mData.getBanner().size() > 0 && mData != null) {
                        mRecyclerView = new HomeRecyclerView(getActivity(), mData);
                    }
                    //处理解析的直播json数据
                    HomeStreamingBean homeStreamingBean = JSON.parseObject(content, HomeStreamingBean.class);
                    //得到直播数据
                    if (homeStreamingBean != null) {
                        mHomeStreamingBeanData = homeStreamingBean.getData();
                    }

                    //适配器获取直播数据
                    if (mRecyclerView != null) {
                        mRecyclerView.setmHomeStreamingBeanData(mHomeStreamingBeanData);
                    }
                    //刷新适配器
                    mRecyclerView.notifyDataSetChanged();
                    //设置适配器
                    recyclerview.setAdapter(mRecyclerView);
                    //设置布局管理器
                    recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("TAG", "加载网络错误" + e.getMessage());
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("TAG", "主页数据请求失败" + content);
            }
        });
    }
}
