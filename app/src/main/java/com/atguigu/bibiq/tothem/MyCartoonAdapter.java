package com.atguigu.bibiq.tothem;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.tothem.bean.ToThemBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/23 0023.
 * 功能:追番页面适配器 国漫
 */
public class MyCartoonAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ToThemBean.ResultBean.SerializingBean> datas;

    public MyCartoonAdapter(Context context, List<ToThemBean.ResultBean.SerializingBean> dataBean) {
        this.mContext = context;
        this.datas = dataBean;
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
            convertView = View.inflate(mContext, R.layout.adapter_tothem_base_iten, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Glide.with(mContext)
                .load(datas.get(position).getCover())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(viewHolder.ivTothemImage);
        viewHolder.tvTothemName.setText(datas.get(position).getTitle());
        String number1 = datas.get(position).getFavourites();
        int online = Integer.parseInt(number1);
        if (online >= 10000) {
            float number = online;
            viewHolder.tvTothemLook.setText((float) (Math.round(number / 10000 * 10)) / 10 + "万人追看");
        } else {
            viewHolder.tvTothemLook.setText(online + "");
        }
        viewHolder.tvTothemUpdate.setText("更新至第" + datas.get(position).getNewest_ep_index() + "话");

        viewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "posttion==" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_tothem_image)
        ImageView ivTothemImage;
        @InjectView(R.id.tv_tothem_look)
        TextView tvTothemLook;
        @InjectView(R.id.tv_tothem_name)
        TextView tvTothemName;
        @InjectView(R.id.tv_tothem_update)
        TextView tvTothemUpdate;
        @InjectView(R.id.cardview)
        CardView cardview;
        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
