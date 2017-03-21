package com.atguigu.bibiq.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.bean.HomeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:
 */

public class HomeRecyclerView extends RecyclerView.Adapter {


    private final Context mContext;
    /**
     * 得到的总数据
     */
    private final HomeBean.DataBean datas;

    private final int BANNER = 0;
    private final int TYPE = 1;
    private final int PARTITIONS = 2;
    private final LayoutInflater inflater;

    int temp = BANNER;

    public HomeRecyclerView(Context context, HomeBean.DataBean banner) {

        this.mContext = context;
        this.datas = banner;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 获取项目的统计
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * 得到数据类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (BANNER == position) {
            temp = BANNER;
        } else if (TYPE == position) {
            temp = TYPE;
        } else if (PARTITIONS == position) {
            temp = PARTITIONS;
        }
        return temp;
    }

    /**
     * 创建视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyBannerViewHolder myBannerViewHolder = null;
        if (BANNER == viewType) {
            return new MyBannerViewHolder(inflater.inflate(R.layout.adapter_banner, null));
        }

        return null;
    }

    /**
     * 绑定视图数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (BANNER == position) {
            MyBannerViewHolder myBannerViewHolder = (MyBannerViewHolder) holder;
            myBannerViewHolder.setData(mContext, datas.getBanner(), position);
        }
    }

    static class MyBannerViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.banner)
        Banner banner;

        public MyBannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final Context context, final List<HomeBean.DataBean.BannerBean> data, int position) {
            //设置数据
            List<String> imageViews = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                imageViews.add(data.get(i).getImg());
                //    imageViews.add(data.get(i).getImg());
            }

            //使用banner
            banner.setImages(imageViews)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {

                            Glide.with(context)
                                    .load(path)
                                    //添加缓存
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .crossFade()
                                    .into(imageView);
                        }
                    })
                    .start();

            //设置样式
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            //3.设置Banner的点击事件  进入网址界面
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("web_json", data.get(position).getLink() + ",," + data.get(position).getTitle());
                    context.startActivity(intent);
                }
            });

        }
    }

}
