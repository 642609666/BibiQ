package com.atguigu.bibiq.type;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.home.adapter.WebActivity;
import com.atguigu.bibiq.type.bean.TypeBean;
import com.atguigu.bibiq.type.bean.TypeHandBean;
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
 * QQ/微信: 642609666} on 3/22 0022.
 * 功能:分区页面适配器
 */
public class TypeRecylerView extends RecyclerView.Adapter {

    private final List<TypeBean.DataBean> datas;
    private final Context mContext;
    private final LayoutInflater inflater;
    private final int HEAD = 0; //头部数据
    private final int ANIMATION = 1;//动画区
    //private final int ANIMATION = 1;//国创区

    private int temp = HEAD;
    private List<TypeHandBean.DataBean> mHandData;

    public TypeRecylerView(Context context, List<TypeBean.DataBean> data) {
        this.mContext = context;
        this.datas = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (HEAD == position) {
            temp = HEAD;
        } else if (ANIMATION == position) {
            temp = ANIMATION;
        }
        return temp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (HEAD == viewType) {
            return new MyHeadViewHolder(inflater.inflate(R.layout.adapter_head_item, null));
        } else if (ANIMATION == viewType) {
            return new MyAnimationViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (HEAD == position) {
            MyHeadViewHolder myHeadViewHolder = (MyHeadViewHolder) holder;
            myHeadViewHolder.setData(mContext, mHandData);
        } else if (ANIMATION == position) {
            MyAnimationViewHolder myAnimationViewHolder = (MyAnimationViewHolder) holder;
            myAnimationViewHolder.setData(mContext, datas.get(0));
        }
    }

    public void setHandData(List<TypeHandBean.DataBean> handData) {
        mHandData = handData;

    }

    /**
     * 分区头部数据设置
     */
    static class MyHeadViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.gridview_type)
        MyGridView gridviewType;
        TypeGroupViewAdapter mAdapter;

        public MyHeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final Context context, final List<TypeHandBean.DataBean> handData) {
            mAdapter = new TypeGroupViewAdapter(context, handData);

            gridviewType.setAdapter(mAdapter);

            //设置点击事件
            gridviewType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, handData.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 分区动画区
     */
    static class MyAnimationViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_type_title_icon)
        ImageView ivTypeTitleIcon;
        @InjectView(R.id.tv_type_title_name)
        TextView tvTypeTitleName;
        @InjectView(R.id.btn_type_title_look)
        Button btnTypeTitleLook;
        @InjectView(R.id.ll_type_title_btn)
        LinearLayout llTypeTitleBtn;
        @InjectView(R.id.gridview)
        MyGridView gridview;
        @InjectView(R.id.btn_type_tail_more)
        Button btnTypeTailMore;
        @InjectView(R.id.tv_type_tail_number)
        TextView tvTypeTailNumber;
        @InjectView(R.id.tv_type_tail_right)
        TextView tvTypeTailRight;
        @InjectView(R.id.banner)
        Banner banner;

        private MyAnimationAdapter mAdapter;

        public MyAnimationViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(final Context context, final TypeBean.DataBean dataBean) {

            //设置适配器
            mAdapter = new MyAnimationAdapter(context, dataBean);
            gridview.setAdapter(mAdapter);

            //设置头部图片(假数据)
            ivTypeTitleIcon.setImageResource(R.drawable.ic_btn_game);
            //设置名字
            tvTypeTitleName.setText(dataBean.getTitle());

            btnTypeTailMore.setTag("更多动画");
            //设置动态数量
            tvTypeTailNumber.setText("44");

            llTypeTitleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "进去看看", Toast.LENGTH_SHORT).show();
                }
            });
            btnTypeTailMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "更多动画", Toast.LENGTH_SHORT).show();
                }
            });
            tvTypeTailRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                }
            });
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
            //设置广告轮播条
            banner.setVisibility(View.VISIBLE);
            //2.设置Banner的数据
            List<String> images = new ArrayList<>();
            //获得广告条数据
            final List<TypeBean.DataBean.BannerBean.BottomBean> bottom = dataBean.getBanner().getBottom();
            for (int i = 0; i < bottom.size(); i++) {
                images.add(bottom.get(i).getImage());
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
                    intent.putExtra("web_json", bottom.get(position).getUri() + ",," + bottom.get(position).getTitle());
                    context.startActivity(intent);
                }
            });
        }
    }

}
