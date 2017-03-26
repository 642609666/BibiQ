package com.atguigu.bibiq.find.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.activity.LoginActivity;
import com.atguigu.bibiq.find.bean.BlackHouseDynamicBean;
import com.atguigu.bibiq.find.bean.BlackHouseNoticeBean;
import com.atguigu.bibiq.activity.WebActivity;
import com.atguigu.bibiq.view.MyGridView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/24 0024.
 * 功能:小黑屋页面适配器
 */
public class MyBlackHouseAdapter extends RecyclerView.Adapter {


    private final Context mContext;
    private final List<BlackHouseNoticeBean.DataBean> datahand;
    private final List<BlackHouseDynamicBean.DataBean> datatail;

    private final int ZERO = 0;
    private final int ONE = 1;
    private final int TWO = 2;
    private final LayoutInflater inflater;
    int temp = ZERO;

    @Override
    public int getItemCount() {
        return 1;
    }

    public MyBlackHouseAdapter(Context context, List<BlackHouseNoticeBean.DataBean> datahand, List<BlackHouseDynamicBean.DataBean> datatail) {
        this.mContext = context;
        this.datahand = datahand;
        this.datatail = datatail;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (ZERO == position) {
            temp = ZERO;
        } else if (ONE == position) {
            temp = ONE;
        } else if (TWO == position) {
            temp = TWO;
        }
        return temp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ZERO == viewType) {
            return new MyBlackHouseViewHolder(inflater.inflate(R.layout.house_hand, null));
        } else if (ONE == viewType) {
        } else if (TWO == viewType) {
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (ZERO == position) {
            MyBlackHouseViewHolder myBlackHouseViewHolder = (MyBlackHouseViewHolder) holder;
            myBlackHouseViewHolder.setData(mContext, datahand, datatail);
        } else if (ONE == position) {
        } else if (TWO == position) {
        }
    }

    static class MyBlackHouseViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_house_icon)
        ImageView ivHouseIcon;
        @InjectView(R.id.btn_house_register)
        Button btnHouseRegister;
        @InjectView(R.id.tv_house_title)
        TextView tvHouseTitle;
        @InjectView(R.id.tv_black_number_left)
        TextView tvBlackNumberLeft;
        @InjectView(R.id.tv_black_number_right)
        TextView tvBlackNumberRight;
        @InjectView(R.id.tv_house_more)
        TextView tvHouseMore;
        @InjectView(R.id.gridview)
        MyGridView gridview;
        @InjectView(R.id.tv_house_all)
        TextView tvHouseAll;
        @InjectView(R.id.gridview1)
        MyGridView gridview1;

        private MyBlacAdapter mAdapter;
        private MyBlacNoticeAdapter mNoticeAdapter;
        public boolean flag = true;
        private int temp = 0;

        public MyBlackHouseViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(final Context context, final List<BlackHouseNoticeBean.DataBean> datahand, final List<BlackHouseDynamicBean.DataBean> datatail) {
            //设置公告适配器
            mAdapter = new MyBlacAdapter(context, datahand);
            gridview.setAdapter(mAdapter);

            //设置动态评论适配器
            mNoticeAdapter = new MyBlacNoticeAdapter(context, datatail);
            gridview1.setAdapter(mNoticeAdapter);

            //设置点击事件
            btnHouseRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            });

            tvHouseMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "更多", Toast.LENGTH_SHORT).show();
                }
            });

            tvHouseAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "全部违规", Toast.LENGTH_SHORT).show();
                }
            });

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("web_json",datahand.get(position).getUrl()+",," + datahand.get(position).getTitle());
                    context.startActivity(intent);
                }
            });
            gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
