package com.atguigu.bibiq.search.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
public class MyBaseAdapter2 extends BaseAdapter {
    private final Context mContext;
    private String[] datas;
    private int temp;
    private int mDataNumber = 0;

    public MyBaseAdapter2(Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_search_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tbName.setText(datas[position]);

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.tbName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onListener(finalViewHolder.tbName.getText().toString(), position);
                    Log.e("TAG", "mDataNumber" + mDataNumber + "=====position" + position);

                }
            }
        });
        if (mDataNumber == position) {
            finalViewHolder.tbName.setTextColor(Color.parseColor("#FFFFFF"));
            finalViewHolder.tbName.setBackgroundColor(Color.parseColor("#FB7299"));
        } else {
            finalViewHolder.tbName.setTextColor(Color.parseColor("#7F7F7F"));
            finalViewHolder.tbName.setBackgroundColor(Color.parseColor("#EAEAEA"));
        }
        return convertView;
    }

    public void setData(String[] lift) {
        this.datas = lift;
    }

    public void setDataNumber(int dataNumber) {
        mDataNumber = dataNumber;
    }

    static class ViewHolder {
        @InjectView(R.id.tb_name)
        TextView tbName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public void setListener(IonClickListener listener) {
        mListener = listener;
    }

    private IonClickListener mListener;

    public interface IonClickListener {
        void onListener(String name, int position);
    }
}
