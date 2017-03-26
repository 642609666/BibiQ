package com.atguigu.bibiq.search.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.search.bean.SearchBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/26 0026.
 * 功能:
 */
public class MySynthesizeViewHolderAdapter extends BaseAdapter {

    private final List<SearchBean.DataBean.ItemsBean.ArchiveBean> datas;
    private final Context mContext;
    private int mNumber = 0;

    public MySynthesizeViewHolderAdapter(Context context, SearchBean datas) {
        this.mContext = context;
        this.datas = datas.getData().getItems().getArchive();
    }

    @Override
    public int getCount() {
        int i = 5 + mNumber;
        return i > datas.size() ? datas.size() : i;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_synthesize_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tvRecommTitle.setText(datas.get(position).getTitle());

        viewHolder.tvRecommContext.setText(datas.get(position).getAuthor());

        //观看数量
        int online = datas.get(position).getPlay();
        if (online >= 10000) {
            float number = online;
            viewHolder.tvRecommNumber.setText((float) (Math.round(number / 10000 * 10)) / 10 + "万");
        } else {
            viewHolder.tvRecommNumber.setText(online + "");
        }

        //评论数量
        int online1 = datas.get(position).getDanmaku();
        if (online1 >= 10000) {
            float number = online1;
            viewHolder.tvRecommComment.setText((float) (Math.round(number / 10000 * 10)) / 10 + "万");
        } else {
            viewHolder.tvRecommComment.setText(online1 + "");
        }
        //图片
        Glide.with(mContext)
                .load(datas.get(position).getCover())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewHolder.ivIcon);

        //播放时间
        String duration = datas.get(position).getDuration();
        String[] split = duration.split(":");
        int fen = Integer.parseInt(split[0]);
        int miao = Integer.parseInt(split[1]);
        int xiaoshi = 00;
        String hour = "00";
        String minute;
        String second;
        if (fen >= 60) {
            xiaoshi = fen / 60;
            if (xiaoshi >= 10) {
                hour = xiaoshi + "";
            } else {
                hour = "0" + xiaoshi;
            }

            if (fen % 60 >= 10) {
                minute = fen % 60 + "";
            } else {
                minute = "0" + fen + "";
            }

            if (miao >= 10) {
                second = miao + "";
            } else {
                second = "0" + miao + "";
            }
        } else {
            if (fen >= 10) {
                minute = fen + "";
            } else {
                minute = "0" + fen + "";
            }

            if (miao >= 10) {
                second = miao + "";
            } else {
                second = "0" + miao + "";
            }
        }
        viewHolder.tvPlaytime.setText(hour + ":" + minute + ":" + second);
//        if (fen >= 60) {
//            xiaoshi = fen / 60;
//
//            if (xiaoshi < 10) {
//                if (xiaoshi < 10 && fen >= 10 && miao >= 10) {
//                    viewHolder.tvPlaytime.setText("0" + xiaoshi + ":" + (fen - xiaoshi) + ":" + miao);
//                } else if (xiaoshi < 10 && fen >= 10 && miao < 10) {
//                    viewHolder.tvPlaytime.setText("0" + xiaoshi + ":" + (fen - xiaoshi) + ":0" + miao);
//                } else if (xiaoshi < 10 && fen < 10 && miao < 10) {
//                    viewHolder.tvPlaytime.setText("0" + xiaoshi + ":0" + (fen - xiaoshi) + ":0" + miao);
//                } else if (xiaoshi < 10 && fen < 10 && miao >= 10) {
//                    viewHolder.tvPlaytime.setText("0" + xiaoshi + ":0" + (fen - xiaoshi) + ":" + miao);
//                }
//            } else {
//                if (xiaoshi < 10 && fen >= 10 && miao >= 10) {
//                    viewHolder.tvPlaytime.setText(xiaoshi + ":" + (fen - xiaoshi) + ":" + miao);
//                } else if (xiaoshi < 10 && fen >= 10 && miao < 10) {
//                    viewHolder.tvPlaytime.setText(xiaoshi + ":" + (fen - xiaoshi) + ":0" + miao);
//                } else if (xiaoshi < 10 && fen < 10 && miao < 10) {
//                    viewHolder.tvPlaytime.setText(xiaoshi + ":0" + (fen - xiaoshi) + ":0" + miao);
//                } else if (xiaoshi < 10 && fen < 10 && miao >= 10) {
//                    viewHolder.tvPlaytime.setText(xiaoshi + ":0" + (fen - xiaoshi) + ":" + miao);
//                }
//            }
//        } else {
//            if (fen >= 10 && miao >= 10) {
//                viewHolder.tvPlaytime.setText("00" + ":" + (fen) + ":" + miao);
//            } else if (fen >= 10 && miao < 10) {
//                viewHolder.tvPlaytime.setText("00" + ":" + (fen) + ":0" + miao);
//            } else if (fen < 10 && miao < 10) {
//                viewHolder.tvPlaytime.setText("00" + ":0" + (fen) + ":0" + miao);
//            } else if (fen < 10 && miao >= 10) {
//                viewHolder.tvPlaytime.setText("00" + ":0" + (fen) + ":" + miao);
//            }
//        }

        return convertView;
    }

    public void setNumber(int number) {
        mNumber = number;
    }


    static class ViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView ivIcon;
        @InjectView(R.id.tv_playtime)
        TextView tvPlaytime;
        @InjectView(R.id.tv_recomm_title)
        TextView tvRecommTitle;
        @InjectView(R.id.tv_recomm_context)
        TextView tvRecommContext;
        @InjectView(R.id.tv_recomm_number)
        TextView tvRecommNumber;
        @InjectView(R.id.tv_recomm_comment)
        TextView tvRecommComment;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
