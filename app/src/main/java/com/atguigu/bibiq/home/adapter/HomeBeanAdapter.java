package com.atguigu.bibiq.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.bean.HomeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:除了直播通用适配器
 */
public class HomeBeanAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.DataBean.PartitionsBean.LivesBean> lives;


    public HomeBeanAdapter(Context context, List<HomeBean.DataBean.PartitionsBean.LivesBean> lives) {
        this.mContext = context;
        this.lives = lives;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return lives.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_streaming_iten, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //加载图片
        Glide.with(mContext)
                .load(lives.get(position).getCover().getSrc())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(viewHolder.ivStreamingImage);
        viewHolder.tvStreamingArea.setText("#" + lives.get(position).getArea() + "#");
        viewHolder.tvStreamingTitle.setText(lives.get(position).getTitle());
        viewHolder.tvStreamingNickname.setText(lives.get(position).getOwner().getName());
        int online = lives.get(position).getOnline();
        if (online >= 10000) {
            float number = online;
            viewHolder.tvStreamingOnline.setText((float) (Math.round(number / 10000 * 10)) / 10 + "万");
        } else {
            viewHolder.tvStreamingOnline.setText(online + "");
        }

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_streaming_image)
        ImageView ivStreamingImage;
        @InjectView(R.id.tv_streaming_area)
        TextView tvStreamingArea;
        @InjectView(R.id.tv_streaming_title)
        TextView tvStreamingTitle;
        @InjectView(R.id.tv_streaming_nickname)
        TextView tvStreamingNickname;
        @InjectView(R.id.tv_streaming_online)
        TextView tvStreamingOnline;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
