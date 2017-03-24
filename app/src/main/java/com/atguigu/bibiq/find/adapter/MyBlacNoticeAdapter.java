package com.atguigu.bibiq.find.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.find.bean.BlackHouseDynamicBean;
import com.atguigu.bibiq.utils.BitmapUtils;
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
 * QQ/微信: 642609666} on 3/24 0024.
 * 功能:小黑屋动态数据适配器
 */
public class MyBlacNoticeAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<BlackHouseDynamicBean.DataBean> datas;
    private int mNumber;

    public MyBlacNoticeAdapter(Context context, List<BlackHouseDynamicBean.DataBean> datatail) {
        this.mContext = context;
        this.datas = datatail;
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
            convertView = View.inflate(mContext, R.layout.adapter_house_notice_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //判断是否显示图片
        if (datas.get(position).isBlockedForever()) {
            viewHolder.ivHouseImageView.setVisibility(View.VISIBLE);
            viewHolder.tvHouseTime.setText("永久封禁");
        } else {
            viewHolder.ivHouseImageView.setVisibility(View.GONE);
            viewHolder.tvHouseTime.setText("封禁3天");
        }
        //设置名字
        viewHolder.tvHouseName.setText(datas.get(position).getUname());
        viewHolder.tvHouseState.setText(datas.get(position).getPunishTitle());

        //设置评论数
        viewHolder.tv_look.setText(datas.get(position).getCommentSum() + "评论数");
        //设置内容
        viewHolder.tvHouseContext.setText(datas.get(position).getOriginContentModify());

        //设置头像

        Glide.with(mContext)
                .load(datas.get(position).getFace())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
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
                }).into(viewHolder.ivHouseIcon);
        return convertView;
    }

    public void setnumber(int number) {
        mNumber = number;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_house_icon)
        ImageView ivHouseIcon;
        @InjectView(R.id.tv_house_name)
        TextView tvHouseName;
        @InjectView(R.id.iv_house_image_view)
        ImageView ivHouseImageView;
        @InjectView(R.id.tv_house_state)
        TextView tvHouseState;
        @InjectView(R.id.tv_house_time)
        TextView tvHouseTime;
        @InjectView(R.id.tv_house_context)
        TextView tvHouseContext;
        @InjectView(R.id.tv_look)
        TextView tv_look;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
