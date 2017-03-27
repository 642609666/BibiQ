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
public class MyBaseAdapter extends BaseAdapter {
    private final Context mContext;
    private String[] datas;
    private int mDataNumber = 0;
    private Boolean isOpenLeft = false;
    private Boolean isOpenCentre = false;
    private Boolean isOpenRight = false;
    private int isintleft;
    private int isintright;

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
                }
            }
        });
        if (isintleft == position && isOpenLeft) {
            finalViewHolder.tbName.setTextColor(Color.parseColor("#FFFFFF"));
            finalViewHolder.tbName.setBackgroundColor(Color.parseColor("#FB7299"));
            Log.e("TAG", "ileft=" + isintleft + "               posi" + position + "??????" + isOpenLeft);
        } else if (isintright == position && isOpenRight) {
            finalViewHolder.tbName.setTextColor(Color.parseColor("#FFFFFF"));
            finalViewHolder.tbName.setBackgroundColor(Color.parseColor("#FB7299"));
            Log.e("TAG", "right=" + isintright + "               posi" + position + "??????" + isOpenRight);
        } else {
            finalViewHolder.tbName.setTextColor(Color.parseColor("#7F7F7F"));
            finalViewHolder.tbName.setBackgroundColor(Color.parseColor("#EAEAEA"));
            //Log.e("TAG", "left" + isintright + "           posi" + position + "?" + isOpenRight);
            // Log.e("TAG", "right" + isintleft + "           posi" + position + "?" + isOpenLeft);
        }

        isOpenLeft = false;
        isOpenCentre = false;
        isOpenRight = false;
        return convertView;
    }

    public void setData(String[] lift, Boolean isOpenLeft, int isintleft) {
        this.datas = lift;
        this.isOpenLeft = isOpenLeft;
        this.isintleft = isintleft;
    }

    public void setData1(String[] lift, Boolean isOpenRight, int isintright) {
        this.datas = lift;
        this.isOpenRight = isOpenRight;
        this.isintright = isintright;
    }

    public void setData2(String[] lift, Boolean isOpenLeft) {
        this.datas = lift;
        this.isOpenLeft = isOpenLeft;
        this.isOpenLeft = isOpenLeft;
    }

    public void setDataNumber(int dataNumber) {
        mDataNumber = dataNumber;
    }

    public void setDataCentre(String[] centre, Boolean isOpenCentre) {
        this.datas = centre;
        this.isOpenCentre = isOpenCentre;
    }

    public void setDataleft(int dataleft) {
        isintleft = dataleft;
    }

    public void setDataright(int dataright) {
        isintright = dataright;
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
