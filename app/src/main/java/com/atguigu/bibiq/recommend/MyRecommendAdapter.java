package com.atguigu.bibiq.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.recommend.bean.RecommendBean;
import com.atguigu.bibiq.view.MyGridView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/23 0023.
 * 功能:推荐页面适配器
 */
public class MyRecommendAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<RecommendBean.DataBean> datas;

    private final int ONE = 0;
    private final LayoutInflater inflater;
    private int temp = ONE;

    public MyRecommendAdapter(Context context, List<RecommendBean.DataBean> data) {
        this.mContext = context;
        this.datas = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (ONE == position) {
            temp = ONE;
        }
        return temp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ONE == viewType) {
            return new MyrecommendViewHolder(inflater.inflate(R.layout.adapter_recommend_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (ONE == position) {
            MyrecommendViewHolder myrecommendViewHolder = (MyrecommendViewHolder) holder;
            myrecommendViewHolder.setData(mContext, datas);
        }
    }


    static class MyrecommendViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.gridview)
        MyGridView gridview;
        private MyRecommendOneAdapter mAdapter;

        public MyrecommendViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(Context context, List<RecommendBean.DataBean> datas) {
            mAdapter = new MyRecommendOneAdapter(context, datas);
            gridview.setAdapter(mAdapter);
        }
    }

}
