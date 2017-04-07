package com.atguigu.bibiq.home.view;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.bean.HomeBean;
import com.atguigu.bibiq.bean.HomeStreamingBean;
import com.atguigu.bibiq.home.adapter.HomeRecyclerView;
import com.atguigu.bibiq.home.presenter.HomePresenter;
import com.atguigu.bibiq.utils.ConstantAddress;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:直播页面
 */

public class HomeFragment extends BaseFragment implements HomeView {

    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    @InjectView(R.id.swi)
    SwipeRefreshLayout swipeRefreshLayout;


    private HomeRecyclerView mRecyclerView;

    HomePresenter mHomePresenter;
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
        return null;
    }

    @Override
    protected void initData(String json) {
        mHomePresenter = new HomePresenter(this);
        //设置适配器
        initAdapter();
        Log.e("TAG", "直播数据初始化");
        //显示刷新
        showRefreshing();
        //联网请求
        mHomePresenter.initFromNet();
        //下拉刷新
        dwonRefreshing();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public String getAddress() {
        return ConstantAddress.BBQ_HOME;
    }

    @Override
    public void hideRefreshing() {
        if (swipeRefreshLayout != null ) {
            swipeRefreshLayout.setRefreshing(false);
            // 关闭的时候也使用
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public void showRefreshing() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void dwonRefreshing() {
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FB7299"));
        //设置背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //联网请求
                mHomePresenter.initFromNet();
            }
        });
    }

    @Override
    public void initAdapter() {
        if (mRecyclerView == null) {
            mRecyclerView = new HomeRecyclerView(getActivity());
            //设置适配器
            recyclerview.setAdapter(mRecyclerView);
            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }
    }

    @Override
    public void initStreamingAdapter(HomeStreamingBean homeStreamingBean) {


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
    }

    @Override
    public void initHomeAdapter(HomeBean homeBean) {
        //得到主页数据
        if (homeBean != null) {
            mData = homeBean.getData();
        }
        //适配器获取主页数据
        if (mRecyclerView != null) {
            mRecyclerView.setmHomeBeanData(mData);
        }
        //刷新适配器
        mRecyclerView.notifyDataSetChanged();
    }

}
