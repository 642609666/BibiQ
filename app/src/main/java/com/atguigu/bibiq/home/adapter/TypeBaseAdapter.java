package com.atguigu.bibiq.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:
 */
public class TypeBaseAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<String> datas;
    private List<Integer> dataDrawable;

    public TypeBaseAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.datas = list;
        dataDrawable = new ArrayList();
        dataDrawable.add(R.drawable.live_home_follow_anchor);
        dataDrawable.add(R.drawable.live_home_live_center);
        dataDrawable.add(R.drawable.live_home_clip_video);
        dataDrawable.add(R.drawable.live_home_search_room);
        dataDrawable.add(R.drawable.live_home_all_category);
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
            convertView = View.inflate(mContext, R.layout.adapter_type_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTypeName.setText(datas.get(position));
        viewHolder.ivTypeImage.setImageResource(dataDrawable.get(position));

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.tv_type_name)
        TextView tvTypeName;
        @InjectView(R.id.iv_type_image)
        ImageView ivTypeImage;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}