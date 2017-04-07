package com.atguigu.bibiq.recommend.view;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.recommend.bean.RecommendBean;
import com.atguigu.bibiq.recommend.presenter.MyRecommendAdapter;
import com.atguigu.bibiq.recommend.presenter.RecommendPresenter;
import com.atguigu.bibiq.utils.ConstantAddress;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:推荐页面
 */

public class RecommendFragment extends BaseFragment implements RecommendView {

    @InjectView(R.id.swi)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<RecommendBean.DataBean> mData;

    private MyRecommendAdapter mAdapter;

    RecommendPresenter mRecommendPresenter;

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
        mRecommendPresenter = new RecommendPresenter(this);
        //下拉刷新
        dwonRefreshing();

        //显示下啦刷新
        showRefreshing();
        Log.e("TAG", "推荐数据初始化");
        //网络请求下载
        mRecommendPresenter.initFromNet();
    }


    @Override
    public String getAddress() {
        return ConstantAddress.BBQ_RECOMMERD;
    }

    @Override
    public void hideRefreshing() {
        if (swipeRefreshLayout != null) {
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
        // 下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //网络请求下载
                mRecommendPresenter.initFromNet();
            }
        });
    }

    @Override
    public void initAdapter(RecommendBean value) {
        if (value != null) {
            mData = value.getData();
        }

        if (mData != null) {

            mAdapter = new MyRecommendAdapter(getActivity(), mData);
            mAdapter.notifyDataSetChanged();

            recyclerview.setAdapter(mAdapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }
    }

}
