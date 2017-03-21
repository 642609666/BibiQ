package com.atguigu.bibiq.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.bean.HomeBean;
import com.atguigu.bibiq.home.adapter.HomeRecyclerView;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.atguigu.bibiq.utils.LoadNet;
import com.atguigu.bibiq.utils.SpUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:直播页面
 */

public class HomeFragment extends BaseFragment {

    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.btn_home_more)
    Button btnHomeMore;
    @InjectView(R.id.scrollView)
    LinearLayout scrollView;

    private HomeRecyclerView mRecyclerView;

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
    }

    /**
     * 联网请求数据
     *
     * @param json
     */
    private void initFromNet(String json) {
        LoadNet.getDataNet(ConstantAddress.BBQ_HOME, new LoadNet.OnGetNet() {
            @Override
            public void onSuccess(String content) {
                Log.e("TAG", "主页数据请求成功");
                //处理解析的json数据
                disposeData(content);
                //保存数据
                SpUtils.saveSP(getActivity(), "json", content);

            }

            @Override
            public void onFailure(String content) {
                Log.e("TAG", "主页数据请求失败" + content);
                //读取SP数据
                String tempJson = SpUtils.getSP(getActivity(), "json");
                if (TextUtils.isEmpty(tempJson) || tempJson == null) {
                    return;
                }
                disposeData(tempJson);
            }
        });
    }

    private void disposeData(String json) {
        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
        //得到数据
        HomeBean.DataBean data = homeBean.getData();

        //数据正常
        if (data != null && data.getBanner().size() > 0) {
            //传给适配器
            Log.e("TAG", "数据解析正常,正在通往适配器的道路上");
            mRecyclerView = new HomeRecyclerView(getActivity(), data);


            //设置适配器
            recyclerview.setAdapter(mRecyclerView);
            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            //设置布局管理器
            // recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btn_home_more)
    public void onClick() {
        Toast.makeText(getActivity(), "更多", Toast.LENGTH_SHORT).show();
    }
}
