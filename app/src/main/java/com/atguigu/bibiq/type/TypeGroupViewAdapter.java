package com.atguigu.bibiq.type;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.type.bean.TypeHandBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:分区头部适配器
 */
public class TypeGroupViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<TypeHandBean.DataBean> datas;

    public TypeGroupViewAdapter(Context context, List<TypeHandBean.DataBean> list) {
        this.mContext = context;
        this.datas = list;
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

        viewHolder.tvTypeName.setText(datas.get(position).getName());
        Glide.with(mContext)
                .load(datas.get(position).getLogo())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewHolder.ivTypeImage);

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