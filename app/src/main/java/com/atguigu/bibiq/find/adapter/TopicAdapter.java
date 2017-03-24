package com.atguigu.bibiq.find.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.find.bean.Topicbean;
import com.atguigu.bibiq.home.adapter.WebActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/24 0024.
 * 功能:
 */
public class TopicAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Topicbean.ListBean> datas;
    private int mNumber;

    public TopicAdapter(Context context, List<Topicbean.ListBean> list) {
        this.mContext = context;
        this.datas = list;
    }

    @Override
    public int getCount() {
        return (mNumber + 5) > datas.size() ? datas.size() : (mNumber + 5);
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
            convertView = View.inflate(mContext, R.layout.adapter_topic, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTopicName.setText(datas.get(position).getTitle());

        Glide.with(mContext)
                .load(datas.get(position).getCover())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.drawable.img_tips_error_banner_tv)
                .into(viewHolder.ivTopicIcon);

        viewHolder.ivTopicIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //联网页面
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("web_json", datas.get(position).getLink() + ",," + datas.get(position).getTitle());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_topic_icon)
        ImageView ivTopicIcon;
        @InjectView(R.id.tv_topic_name)
        TextView tvTopicName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
