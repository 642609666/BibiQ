package com.atguigu.bibiq.find.activity;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseActivity;
import com.atguigu.bibiq.find.bean.CentreBean;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import okhttp3.Call;

public class CentreActivity extends BaseActivity {

    @InjectView(R.id.gridview)
    GridView gridview;
    @InjectView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @InjectView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @InjectView(R.id.tv_login_forget_password)
    TextView tvLoginForgetPassword;

    private ActivityCentreAdapter mAdapter;
    private boolean flag;

    private int number = 0;
    private CentreBean topicbean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_topic;
    }

    @Override
    protected void initData() {
        //网络请求数据
        initFromNet();

        tvLoginRegister.setText("话题中心");
        tvLoginForgetPassword.setVisibility(View.GONE);
    }

    private void initFromNet() {
        OkHttpUtils.get()
                .url(ConstantAddress.BBQ_ACTIVITY_CENTRE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "话题中心请求失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "话题中心请求成功");
                         topicbean = JSON.parseObject(response, CentreBean.class);
                        List<CentreBean.ListBean> list = topicbean.getList();
                        if (list != null && list.size() > 0) {
                            //设置适配器
                            mAdapter = new ActivityCentreAdapter(CentreActivity.this, list);

                            gridview.setAdapter(mAdapter);
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
        ivLoginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //判断滚动监听方法
        gridview.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (gridview.getLastVisiblePosition() == (gridview.getCount() - 1)) {
                            Toast.makeText(CentreActivity.this, "加载", Toast.LENGTH_SHORT).show();

                            if (mAdapter != null) {
                                number += 5;
                                mAdapter.setNumber(number);

                                //刷新
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                        // 判断滚动到顶部

                        if (gridview.getFirstVisiblePosition() == 0) {
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount && !flag) {
                    flag = true;
                } else
                    flag = false;
            }
        });

    }
}
