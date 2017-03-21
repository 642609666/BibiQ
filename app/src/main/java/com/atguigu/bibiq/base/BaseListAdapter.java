package com.atguigu.bibiq.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:
 */

public abstract class BaseListAdapter<T> extends BaseAdapter {

    private final List<T> list = new ArrayList<>();

    public BaseListAdapter(List<T> list) {
        if (list != null && list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder baseHolder = null;

        if (convertView == null) {
            baseHolder = getHolder(parent.getContext());
        } else {
            baseHolder = (BaseHolder) convertView.getTag();
        }
        T t = list.get(position);
        baseHolder.setData(t);
        return baseHolder.getView();
    }

    /**
     * @return ViewHolder对象
     */
    public abstract BaseHolder getHolder(Context context);
}
