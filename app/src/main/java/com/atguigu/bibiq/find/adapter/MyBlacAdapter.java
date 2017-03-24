package com.atguigu.bibiq.find.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.find.bean.BlackHouseNoticeBean;
import com.atguigu.bibiq.utils.TimeUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/24 0024.
 * 功能:小黑屋官方公告适配器
 */
public class MyBlacAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<BlackHouseNoticeBean.DataBean> datas;

    public MyBlacAdapter(Context context, List<BlackHouseNoticeBean.DataBean> datahand) {
        this.mContext = context;
        this.datas = datahand;
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
            convertView = View.inflate(mContext, R.layout.adapter_house_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //显示是否置顶
        if (datas.get(position).isStickStatus()) {
            viewHolder.ivHouseIcon.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivHouseIcon.setVisibility(View.GONE);
        }

        viewHolder.tvHouseTitle.setText(datas.get(position).getTitle());
        //设置时间
        String dateTimeFromMillisecond = TimeUtils.getDateFromMillisecond(datas.get(position).getCtime());
        viewHolder.tvHouseTime.setText(dateTimeFromMillisecond);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_house_icon)
        ImageView ivHouseIcon;
        @InjectView(R.id.tv_house_title)
        TextView tvHouseTitle;
        @InjectView(R.id.tv_house_time)
        TextView tvHouseTime;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
