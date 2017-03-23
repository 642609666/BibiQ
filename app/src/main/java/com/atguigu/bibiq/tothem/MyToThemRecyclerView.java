package com.atguigu.bibiq.tothem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.home.adapter.WebActivity;
import com.atguigu.bibiq.tothem.bean.ToThemBean;
import com.atguigu.bibiq.view.MyGridView;
import com.bumptech.glide.Glide;
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
 * QQ/微信: 642609666} on 3/23 0023.
 * 功能:追番界面适配器
 */
public class MyToThemRecyclerView extends RecyclerView.Adapter {
    private final Context mContext;
    private final ToThemBean.ResultBean datas;
    private final LayoutInflater inflater;


    public MyToThemRecyclerView(Context context, ToThemBean.ResultBean result) {
        this.mContext = context;
        this.datas = result;
        inflater = LayoutInflater.from(context);

    }

    private final int TO_HAND = 0;   //头布局
    private final int BANGUMI = 1;  //番剧推荐
    private final int CARTOON = 2;  //国漫推荐

    private int temp = TO_HAND;

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (TO_HAND == position) {
            temp = TO_HAND;
        } else if (BANGUMI == position) {
            temp = BANGUMI;
        } else if (CARTOON == position) {
            temp = CARTOON;
        }

        return temp;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TO_HAND == viewType) {
            return new MyToThemHandViewHolder(inflater.inflate(R.layout.adapter_tothem_hand, null));
        } else if (BANGUMI == viewType) {
            return new MyBangumiViewHolder(inflater.inflate(R.layout.adapter_tothem_bangumi, null));
        } else if (CARTOON == viewType) {
            return new MyCartoonViewHolder(inflater.inflate(R.layout.adapter_tothem_bangumi, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (TO_HAND == position) {
            MyToThemHandViewHolder myToThemHandViewHolder = (MyToThemHandViewHolder) holder;
            myToThemHandViewHolder.setData(mContext);
        } else if (BANGUMI == position) {
            MyBangumiViewHolder myBangumiViewHolder = (MyBangumiViewHolder) holder;
            myBangumiViewHolder.setData(mContext, datas);
        } else if (CARTOON == position) {
            MyCartoonViewHolder myCartoonViewHolder = (MyCartoonViewHolder) holder;
            myCartoonViewHolder.setData(mContext, datas);
        }
    }

    /**
     * 追番头部布局
     */
    static class MyToThemHandViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_tothem_bangumi)
        ImageView ivTothemBangumi;
        @InjectView(R.id.rl_tothem_bangumi)
        RelativeLayout rlTothemBangumi;
        @InjectView(R.id.iv_tothem_diffuse)
        ImageView ivTothemDiffuse;
        @InjectView(R.id.rl_tothem_diffuse)
        RelativeLayout rlTothemDiffuse;
        @InjectView(R.id.ll_tothem_time)
        LinearLayout llTothemTime;
        @InjectView(R.id.ll_tithem_index)
        LinearLayout llTithemIndex;
        @InjectView(R.id.iv_tothem_register)
        ImageView ivTothemRegister;

        public MyToThemHandViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final Context context) {
            //设置点击事件
            rlTothemBangumi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "番剧", Toast.LENGTH_SHORT).show();
                }
            });
            rlTothemDiffuse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "国漫", Toast.LENGTH_SHORT).show();
                }
            });
            llTothemTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "时间", Toast.LENGTH_SHORT).show();
                }
            });
            llTithemIndex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "索引", Toast.LENGTH_SHORT).show();
                }
            });

            ivTothemRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "登录", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 番剧推荐
     */
    static class MyBangumiViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_tothem_title_icon)
        ImageView ivTothemTitleIcon;
        @InjectView(R.id.tv_tothem_title_name)
        TextView tvTothemTitleName;
        @InjectView(R.id.tv_tothem_title_right)
        TextView tvTothemTitleRight;
        @InjectView(R.id.ll_tothem_title_btn)
        LinearLayout llTothemTitleBtn;
        @InjectView(R.id.gridview)
        MyGridView gridview;
        @InjectView(R.id.banner)
        Banner banner;
        private MyBangumiAdapter mAdapter;

        public MyBangumiViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(final Context context, ToThemBean.ResultBean datas) {
            mAdapter = new MyBangumiAdapter(context, datas.getPrevious().getList());
            //设置适配器
            gridview.setAdapter(mAdapter);

            ivTothemTitleIcon.setImageResource(R.drawable.ic_btn_game);

            tvTothemTitleName.setText("番剧推荐");

            initBanner(banner, context, datas.getAd().getHead());

            llTothemTitleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "更多番剧", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 国漫
     */
    static class MyCartoonViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_tothem_title_icon)
        ImageView ivTothemTitleIcon;
        @InjectView(R.id.tv_tothem_title_name)
        TextView tvTothemTitleName;
        @InjectView(R.id.tv_tothem_title_right)
        TextView tvTothemTitleRight;
        @InjectView(R.id.ll_tothem_title_btn)
        LinearLayout llTothemTitleBtn;
        @InjectView(R.id.gridview)
        MyGridView gridview;
        @InjectView(R.id.banner)
        Banner banner;
        private MyCartoonAdapter mAdapter;

        public MyCartoonViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }


        public void setData(final Context context, ToThemBean.ResultBean datas) {
            mAdapter = new MyCartoonAdapter(context, datas.getSerializing());
            //设置适配器
            gridview.setAdapter(mAdapter);

            ivTothemTitleIcon.setImageResource(R.drawable.ic_btn_game);

            tvTothemTitleName.setText("国漫推荐");

            initBanner(banner, context, datas.getAd().getHead());

            llTothemTitleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "更多国漫", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    /**
     * 正常广告条
     *
     * @param context
     * @param dataBean
     */
    public static void initBanner(final Banner banner, final Context context, final List<ToThemBean.ResultBean.AdBean.HeadBean> dataBean) {
        //设置广告轮播条
        banner.setVisibility(View.VISIBLE);
        //2.设置Banner的数据
        List<String> images = new ArrayList<>();
        //获得广告条数据
        for (int i = 0; i < dataBean.size(); i++) {
            images.add(dataBean.get(i).getImg());
        }
        //简单使用
        banner.setImages(images)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
                        Glide.with(context)
                                .load(path)
                                .crossFade()
                                .into(imageView);
                    }
                })
                .start();
        //设置样式
        banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
        //3.设置Banner的点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //进入联网数据页面
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("web_json", dataBean.get(position).getLink() + ",," + dataBean.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }


}
