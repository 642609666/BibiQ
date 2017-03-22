package com.atguigu.bibiq.type;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.type.bean.TypeBean;
import com.atguigu.bibiq.view.MyGridView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/22 0022.
 * 功能:分区页面适配器
 */
public class TypeRecylerView extends RecyclerView.Adapter {
    private String[] heads = {"直播", "番剧", "动画", "音乐", "舞蹈", "游戏", "科技", "生活", "鬼畜", "时尚",
            "广告", "娱乐", "电影", "电视剧", "游戏中心"};
    private final List<TypeBean.DataBean> datas;
    private final Context mContext;
    private final LayoutInflater inflater;
    private final int HEAD = 0; //头部数据


    public TypeRecylerView(Context context, List<TypeBean.DataBean> data) {
        this.mContext = context;
        this.datas = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (HEAD == viewType) {
            return new MyHeadViewHolder(inflater.inflate(R.layout.adapter_head_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (HEAD == position) {
            MyHeadViewHolder myHeadViewHolder = (MyHeadViewHolder) holder;
            myHeadViewHolder.setData(mContext, heads);
        }
    }

    static class MyHeadViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.gridview_type)
        MyGridView gridviewType;
        TypeGroupViewAdapter mAdapter;

        public MyHeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final Context context, final String[] heads) {
            mAdapter = new TypeGroupViewAdapter(context, heads);

            gridviewType.setAdapter(mAdapter);

            //设置点击事件
            gridviewType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, heads[position], Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
