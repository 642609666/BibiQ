package com.atguigu.bibiq.recommend;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.recommend.bean.RecommendBean;
import com.atguigu.bibiq.utils.BitmapUtils;
import com.atguigu.bibiq.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/23 0023.
 * 功能:推荐页面适配器
 */
public class MyRecommendAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<RecommendBean.DataBean> datas;

    public MyRecommendAdapter(Context context, List<RecommendBean.DataBean> data) {
        this.mContext = context;
        this.datas = data;
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
            convertView = View.inflate(mContext, R.layout.adapter_recommend, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //说明
        viewHolder.tvRecommTitle.setText(datas.get(position).getTitle());
        //内容
        viewHolder.tvRecommContext.setText(datas.get(position).getDesc());
        //评论数
        viewHolder.tvRecommComment.setText(datas.get(position).getTid() + "");
        //昵称
        viewHolder.tvRecommName.setText(datas.get(position).getName());
        //类型
        viewHolder.tvRecommType.setText(datas.get(position).getTname() + "  "
                + datas.get(position).getTag().getTag_name());

        //观看数量
        int online = datas.get(position).getPlay();
        if (online >= 10000) {
            float number = online;
            viewHolder.tvRecommNumber.setText((float) (Math.round(number / 10000 * 10)) / 10 + "万人追看");
        } else {
            viewHolder.tvRecommNumber.setText(online + "");
        }
        //图片
        Glide.with(mContext)
                .load(datas.get(position).getCover())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewHolder.ivRecommIcon);
        //小头像
        Glide.with(mContext)
                .load(datas.get(position).getFace())
                .transform(new BitmapTransformation(mContext) {
                    @Override
                    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
                        Bitmap bitmap = BitmapUtils.circleBitmap(toTransform);
                        return bitmap;
                    }

                    @Override
                    public String getId() {
                        return "";
                    }
                }).into(viewHolder.ivRecommIconHand);
        //几天前登录
        int ctime = datas.get(position).getDuration();
        int  time = ctime / 60 / 24;

        if(time > 0) {
            viewHolder.tvRecommTime.setText(time + "天前");
        }else if (ctime/60 > 0){
            viewHolder.tvRecommTime.setText(ctime/60 + "小时前");
        }else{
            viewHolder.tvRecommTime.setText(time + "分钟前");
        }
        //播放时间
        viewHolder.tvRecommPlaytime.setText(new TimeUtils().getTimeFromMillisecond(datas.get(position).getPlay() * 1000));
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_recomm_title)
        TextView tvRecommTitle;
        @InjectView(R.id.iv_recomm_icon)
        ImageView ivRecommIcon;
        @InjectView(R.id.tv_recomm_context)
        TextView tvRecommContext;
        @InjectView(R.id.tv_recomm_number)
        TextView tvRecommNumber;
        @InjectView(R.id.tv_recomm_comment)
        TextView tvRecommComment;
        @InjectView(R.id.iv_recomm_icon_hand)
        ImageView ivRecommIconHand;
        @InjectView(R.id.tv_recomm_name)
        TextView tvRecommName;
        @InjectView(R.id.tv_recomm_time)
        TextView tvRecommTime;
        @InjectView(R.id.tv_recomm_type)
        TextView tvRecommType;
        @InjectView(R.id.tv_recomm_playtime)
        TextView tvRecommPlaytime;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
