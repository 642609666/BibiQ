package com.atguigu.bibiq.search.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.search.bean.SearchBean;
import com.atguigu.bibiq.view.MyGridView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/26 0026.
 * 功能:搜索界面综合fragment适配器
 */
public class MysynthesisAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final SearchBean datas;
    private final int OVER = 0;
    private final LayoutInflater inflater;
    private int temp = OVER;
    private int mNumber;

    public MysynthesisAdapter(Context context, SearchBean searchBean) {
        this.mContext = context;
        this.datas = searchBean;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (OVER == position) {
            temp = OVER;
        }
        return temp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (OVER == viewType) {
            return new MySynthesizeViewHolder(inflater.inflate(R.layout.adapter_synthesize, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (OVER == position) {
            MySynthesizeViewHolder mySynthesizeViewHolder = (MySynthesizeViewHolder) holder;
            mySynthesizeViewHolder.setData(mContext, datas,mNumber);
        }

    }

    public void setNumber(int number) {
        mNumber = number;
    }


    static class MySynthesizeViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.gridview)
        MyGridView gridview;

        private MySynthesizeViewHolderAdapter mAdapter;
        private boolean flag = false;

        public MySynthesizeViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(final Context context, SearchBean datas, int number) {
            mAdapter = new MySynthesizeViewHolderAdapter(context, datas);

            gridview.setAdapter(mAdapter);

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("TAG", "posiyion" + position);
                    Toast.makeText(context, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });

            if (mAdapter != null) {
                mAdapter.setNumber(number);

                mAdapter.notifyDataSetChanged();
            }
        }
    }

}
