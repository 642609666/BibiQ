package com.atguigu.bibiq.store;

import android.text.TextUtils;
import android.util.Log;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.find.bean.OriginalBean;
import com.atguigu.bibiq.shopping.adapter.MyShoppingAdapter;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/24 0024.
 * 功能:商城界面
 */
public class store extends BaseFragment {
    @InjectView(R.id.gridview)
    GridView gridview;
    @InjectView(R.id.activity_shopping)
    LinearLayout activityShopping;

    private MyShoppingAdapter mAdapter;
    private List<OriginalBean.DataBean> datas;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_store;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    protected void initData(String json) {
        mAdapter = new MyShoppingAdapter(getActivity());

        gridview.setAdapter(mAdapter);
        initFromNet();
    }

    private void initFromNet() {
        OkHttpUtils.get()
                .url(ConstantAddress.BBQ_ORIGINAL_ALL)
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "商城加载失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {

                            Log.e("TAG", "商城加载成功");
                            OriginalBean originalBean = JSON.parseObject(response, OriginalBean.class);
                            datas = originalBean.getData();
                            if (datas != null) {
                                if (mAdapter != null) {
                                    mAdapter.setData(datas);
                                    mAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });

    }


}
