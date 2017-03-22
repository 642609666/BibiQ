package com.atguigu.bibiq.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.bean.HomeStreamingBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:直播适配器
 */
public class StreamingBaseAdapter extends BaseAdapter {
    private final Context mContext;
    private Random mRandom = new Random();
    private final List<HomeStreamingBean.DataBean> homeStreamingBeanData;

    public StreamingBaseAdapter(Context context, List<HomeStreamingBean.DataBean> homeStreamingBeanData) {
        this.mContext = context;
        this.homeStreamingBeanData = homeStreamingBeanData;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public Object getItem(int position) {
        return homeStreamingBeanData.get(position);
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
        int nextInt = mRandom.nextInt(homeStreamingBeanData.size() - 1);

        //虚拟变位
        position = nextInt;
        //加载图片
        Glide.with(mContext)
                .load(homeStreamingBeanData.get(position).getCover().getSrc())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(viewHolder.ivStreamingImage);
        viewHolder.tvStreamingArea.setText("#" + homeStreamingBeanData.get(position).getArea() + "#");
        viewHolder.tvStreamingTitle.setText(homeStreamingBeanData.get(position).getTitle());
        viewHolder.tvStreamingNickname.setText(homeStreamingBeanData.get(position).getOwner().getName());
        int online = homeStreamingBeanData.get(position).getOnline();
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
