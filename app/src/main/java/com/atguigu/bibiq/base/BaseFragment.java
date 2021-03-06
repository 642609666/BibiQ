package com.atguigu.bibiq.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.bibiq.view.Loadingpager;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/20 0020.
 * 功能:父类fragment布局
 */

public abstract class BaseFragment extends Fragment {
    private Loadingpager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPager = new Loadingpager(getActivity()) {
            @Override
            protected void onSuccess(ResultState resultState, View sucessView) {
                ButterKnife.inject(BaseFragment.this, sucessView);
                initData(resultState.getJson());
            }

            @Override
            protected String getUrl() {
                return getChildUrl();
            }

            @Override
            public int getViewId() {
                return getLayoutid();
            }
        };

        return loadingPager;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        //initData();

        loadingPager.loadData();
    }

    /**
     * 添加布局
     * @return
     */
    public abstract int getLayoutid();
    /**
     * 数据地址
     * @return
     */
    //每一个fragment返回的地址
    public abstract String getChildUrl();

    /**
     * 解析数据
     * @param json
     */
    protected abstract void initData(String json);


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
        OkHttpUtils.delete().tag(this);
    }
}
