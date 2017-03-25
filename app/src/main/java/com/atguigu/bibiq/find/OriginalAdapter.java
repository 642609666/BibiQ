package com.atguigu.bibiq.find;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.find.bean.OriginalBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/25 0025.
 * 功能:原创排行榜适配器
 */
public class OriginalAdapter extends BaseAdapter {
    private final Context mContext;
    private List<OriginalBean.DataBean> datas;

    public OriginalAdapter(Context context, List<OriginalBean.DataBean> data) {
        this.mContext = context;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_original, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position <= 2) {
            viewHolder.tvNumber.setTextColor(Color.parseColor("#FB7299"));
        } else {
            viewHolder.tvNumber.setTextColor(Color.parseColor("#979797"));
        }
        viewHolder.tvNumber.setText((position + 1) + "");
        viewHolder.tvRecommContext.setText(datas.get(position).getTitle());
        viewHolder.tvRecommNumber.setText(datas.get(position).getName());
        viewHolder.tvRecommComment.setText("综合评分:" + datas.get(position).getPts());

        Glide.with(mContext)
                .load(datas.get(position).getCover())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewHolder.ivRecommIcon);
        return convertView;
    }

    public void setData(List<OriginalBean.DataBean> data) {
        if (datas != null) {
            datas.clear();
            datas.addAll(data);
        } else {
            datas = data;
        }
    }

    static class ViewHolder {
        @InjectView(R.id.iv_recomm_icon)
        ImageView ivRecommIcon;
        @InjectView(R.id.tv_recomm_context)
        TextView tvRecommContext;
        @InjectView(R.id.tv_recomm_number)
        TextView tvRecommNumber;
        @InjectView(R.id.tv_recomm_comment)
        TextView tvRecommComment;
        @InjectView(R.id.tv_number)
        TextView tvNumber;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
