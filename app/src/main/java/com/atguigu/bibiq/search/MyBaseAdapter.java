package com.atguigu.bibiq.search;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.bibiq.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/26 0026.
 * 功能:
 */
public class MyBaseAdapter extends BaseAdapter {
    private final Context mContext;
    private String[] datas;
    private int temp;

    public MyBaseAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.length;
    }

    @Override
    public Object getItem(int position) {
        return datas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        temp = position;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_search_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tbName.setText(datas[position]);
        if(temp == position) {
            viewHolder.tbName.setTextColor(Color.parseColor("#FFFFFF"));
            viewHolder.tbName.setBackgroundColor(Color.parseColor("#FB7299"));
        }else{
            viewHolder.tbName.setTextColor(Color.parseColor("#7F7F7F"));
            viewHolder.tbName.setBackgroundColor(Color.parseColor("#EAEAEA"));
        }
        temp = position;
        return convertView;
    }

    public void setData(String[] lift) {
        this.datas = lift;
    }

    static class ViewHolder {
        @InjectView(R.id.tb_name)
        TextView tbName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
