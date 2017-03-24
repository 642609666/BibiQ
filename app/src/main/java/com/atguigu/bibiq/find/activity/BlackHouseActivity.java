package com.atguigu.bibiq.find.activity;

import android.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseActivity;
import com.atguigu.bibiq.find.adapter.MyBlackHouseAdapter;
import com.atguigu.bibiq.find.bean.BlackHouseDynamicBean;
import com.atguigu.bibiq.find.bean.BlackHouseNoticeBean;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class BlackHouseActivity extends BaseActivity {

    @InjectView(R.id.iv_web_back)
    ImageView ivWebBack;
    @InjectView(R.id.tv_web_name)
    TextView tvWebName;
    @InjectView(R.id.iv_web_more)
    ImageView ivWebMore;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    private MyBlackHouseAdapter mAdapter;
    private ProgressBar mProgressBar;
    private AlertDialog mDialog;


    @Override
    public int getLayoutId() {
        return R.layout.activity_black_house;
    }

    @Override
    protected void initData() {
        mProgressBar = new ProgressBar(this);
        mDialog = new AlertDialog.Builder(this)
                .setMessage("加载中.......")
                .setView(mProgressBar)
                .show();
        //网络请求下载
        initFromNet();


    }

    private void initFromNet() {

        OkHttpUtils.get()
                .tag(this)
                .url(ConstantAddress.BBQ_BLACKHOUSE_NOTICE)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "小黑屋官方通知加载失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "小黑屋官方通知加载成功");
                BlackHouseNoticeBean blackHouseNoticeBean = JSON.parseObject(response, BlackHouseNoticeBean.class);
                final List<BlackHouseNoticeBean.DataBean> data = blackHouseNoticeBean.getData();

                if (data != null && data.size() > 0) {
                    BlackHouseActivity.this.initFromNet(data);
                }
            }
        });
    }

    private void initFromNet(final List<BlackHouseNoticeBean.DataBean> data) {
        OkHttpUtils.get()
                .tag(this)
                .url(ConstantAddress.BBQ_BLACKHOUSE_DYNAMIC)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "小黑屋公示动态加载失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "小黑屋公示动态加载成功");
                        BlackHouseDynamicBean blackHouseDynamicBean = JSON.parseObject(response, BlackHouseDynamicBean.class);
                        List<BlackHouseDynamicBean.DataBean> blackHouseDynamicBeanData = blackHouseDynamicBean.getData();
                        mDialog.dismiss();
                        //判断官方动态数据
                        if (blackHouseDynamicBeanData != null && blackHouseDynamicBeanData.size() > 0) {
                            //设置适配器
                            mAdapter = new MyBlackHouseAdapter(BlackHouseActivity.this, data, blackHouseDynamicBeanData);

                            if (mAdapter != null && recyclerview != null) {
                                recyclerview.setAdapter(mAdapter);

                                recyclerview.setLayoutManager(new LinearLayoutManager(BlackHouseActivity.this, LinearLayoutManager.VERTICAL, false));
                            }
                        }
                    }
                });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
        //根据 Tag 取消请求
        OkHttpUtils.getInstance().cancelTag(this);
    }

    @OnClick({R.id.iv_web_back, R.id.iv_web_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_web_back:
                finish();
                break;
            case R.id.iv_web_more:
                Toast.makeText(BlackHouseActivity.this, "更多", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
