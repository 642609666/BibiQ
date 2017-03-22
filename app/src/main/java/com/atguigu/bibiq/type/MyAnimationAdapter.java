package com.atguigu.bibiq.type;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.type.bean.TypeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/22 0022.
 * 功能:分区动画专区适配器
 */
public class MyAnimationAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<TypeBean.DataBean.BodyBean> datas;

    public MyAnimationAdapter(Context context, TypeBean.DataBean dataBean) {
        this.mContext = context;
        this.datas = dataBean.getBody();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_type_base_iten, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext)
                .load(datas.get(position).getCover())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(viewHolder.ivStreamingImage);
        viewHolder.tvStreamingTitle.setText(datas.get(position).getTitle());
        int online = datas.get(position).getPlay();
        if (online >= 10000) {
            float number = online;
            viewHolder.tvStreamingNickname.setText((float) (Math.round(number / 10000 * 10)) / 10 + "万");
        } else {
            viewHolder.tvStreamingNickname.setText(online + "");
        }
        int online1 = datas.get(position).getDanmaku();
        if (online1 >= 10000) {
            float number = online;
            viewHolder.tvStreamingOnline.setText((float) (Math.round(number / 10000 * 10)) / 10 + "万");
        } else {
            viewHolder.tvStreamingOnline.setText(online1 + "");
        }

        viewHolder.ivStreamingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_streaming_image)
        ImageView ivStreamingImage;
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
