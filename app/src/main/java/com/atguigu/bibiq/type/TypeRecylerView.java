package com.atguigu.bibiq.type;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private final int AIMTRON = 2;//国创区
    private final int AIMTRON1 = 3;//音乐区
    private final int AIMTRON2 = 4;//舞蹈区
    private final int AIMTRON3 = 5;//话题
    private final int AIMTRON4 = 6;//游戏区
    private final int AIMTRON5 = 7;//鬼畜区
    private final int AIMTRON6 = 8;//话题
    private final int AIMTRON7 = 9;//生活区
    private final int AIMTRON8 = 10;//话题
    private final int AIMTRON9 = 11;//科技区
    private final int AIMTRON10 = 12;//活动中心
    private final int AIMTRON11 = 13;//话题
    private final int AIMTRON12 = 14;//时尚区
    private final int AIMTRON13 = 15;//广告区
    private final int AIMTRON14 = 16;//电视剧区
    private final int AIMTRON15 = 17;//话题
    private final int AIMTRON16 = 18;//电影区
    private final int AIMTRON17 = 19;//电影区


    private int temp = HEAD;
    private List<TypeHandBean.DataBean> mHandData;

    public TypeRecylerView(Context context, List<TypeBean.DataBean> data) {
        this.mContext = context;
        this.datas = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (HEAD == position) {
            temp = HEAD;
        } else if (ANIMATION == position) {
            temp = ANIMATION;
        } else if (AIMTRON == position) {
            temp = AIMTRON;
        } else if (AIMTRON1 == position) {
            temp = AIMTRON1;
        } else if (AIMTRON2 == position) {
            temp = AIMTRON2;
        } else if (AIMTRON3 == position) {
            temp = AIMTRON3;
        } else if (AIMTRON4 == position) {
            temp = AIMTRON4;
        } else if (AIMTRON5 == position) {
            temp = AIMTRON5;
        } else if (AIMTRON6 == position) {
            temp = AIMTRON6;
        }else if (AIMTRON7 == position) {
            temp = AIMTRON7;
        }else if (AIMTRON8 == position) {
            temp = AIMTRON8;
        }else if (AIMTRON9 == position) {
            temp = AIMTRON9;
        }else if (AIMTRON10 == position) {
            temp = AIMTRON10;
        }else if (AIMTRON11 == position) {
            temp = AIMTRON11;
        }else if (AIMTRON12 == position) {
            temp = AIMTRON12;
        }else if (AIMTRON13 == position) {
            temp = AIMTRON13;
        }else if (AIMTRON14 == position) {
            temp = AIMTRON14;
        }else if (AIMTRON15 == position) {
            temp = AIMTRON15;
        }else if (AIMTRON16 == position) {
            temp = AIMTRON16;
        }else if (AIMTRON17 == position) {
            temp = AIMTRON17;
        }
        return temp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (HEAD == viewType) {
            return new MyHeadViewHolder(inflater.inflate(R.layout.adapter_head_item, null));
        } else if (ANIMATION == viewType) {
            return new MyAnimationViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        } else if (AIMTRON == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        } else if (AIMTRON1 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        } else if (AIMTRON2 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        } else if (AIMTRON3 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        } else if (AIMTRON4 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        } else if (AIMTRON5 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        } else if (AIMTRON6 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON7 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON8 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON9 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON10 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON11 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON12 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON13 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON14 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON15 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON16 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
        }else if (AIMTRON17 == viewType) {
            return new MyAimtronViewHolder(inflater.inflate(R.layout.adapter_type_animation, null));
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
        } else if (AIMTRON == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(1));
        } else if (AIMTRON1 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(2));
        } else if (AIMTRON2 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(3));
        } else if (AIMTRON3 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(4));
        } else if (AIMTRON4 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(5));
        } else if (AIMTRON5 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(6));
        } else if (AIMTRON6 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(7));
        }else if (AIMTRON7 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(8));
        }else if (AIMTRON8 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(9));
        }else if (AIMTRON9 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(10));
        }else if (AIMTRON10 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(11));
        }else if (AIMTRON11 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(12));
        }else if (AIMTRON12 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(13));
        }else if (AIMTRON13 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(14));
        }else if (AIMTRON14 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(15));
        }else if (AIMTRON15 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(16));
        }else if (AIMTRON16 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(17));
        }else if (AIMTRON17 == position) {
            MyAimtronViewHolder myAimtronViewHolder = (MyAimtronViewHolder) holder;
            myAimtronViewHolder.setData(mContext, datas.get(18));
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
        @InjectView(R.id.ll_type_tail)
        LinearLayout llTypeTail;

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

            //设置广告条
            initBanner(banner, context, dataBean);
        }
    }

    /**
     * 国创区
     */
    static class MyAimtronViewHolder extends RecyclerView.ViewHolder {
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
        @InjectView(R.id.ll_type_tail)
        LinearLayout llTypeTail;
        private MyAnimationAdapter mAdapter;

        public MyAimtronViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(final Context context, TypeBean.DataBean dataBean) {

            //只要一条数据  那么久显示一条banner
            if (dataBean.getBody().size() == 1) {
                Log.e("TAG", "只要一条广告条");
                //设置头部图片(假数据)
                ivTypeTitleIcon.setImageResource(R.drawable.ic_btn_biliyoo);
                //设置名字
                tvTypeTitleName.setText("话题");
                //设置广告
                initBannerOne(banner, context, dataBean.getBody().get(0));

                llTypeTail.setVisibility(View.GONE);

            } else {

                if (dataBean.getBody().size() % 2 == 1) {
                    gridview.setNumColumns(3);
                }
                //设置头部图片(假数据)
                ivTypeTitleIcon.setImageResource(R.drawable.ic_btn_game);
                //设置名字
                tvTypeTitleName.setText(dataBean.getTitle());
                //设置适配器
                mAdapter = new MyAnimationAdapter(context, dataBean);
                gridview.setAdapter(mAdapter);
                btnTypeTailMore.setTag("更多动画");
                //设置动态数量
                tvTypeTailNumber.setText("44");

                btnTypeTailMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "更多国创", Toast.LENGTH_SHORT).show();
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
            }
            llTypeTitleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "进去看看", Toast.LENGTH_SHORT).show();
                }
            });

            if (dataBean.getBanner() != null) {
                initBanner(banner, context, dataBean);
            }
        }
    }

    /**
     * 正常广告条
     *
     * @param context
     * @param dataBean
     */
    public static void initBanner(final Banner banner, final Context context, TypeBean.DataBean dataBean) {
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

    /**
     * 话题广告条
     *
     * @param context
     * @param dataBean
     */
    public static void initBannerOne(final Banner banner, final Context context, final TypeBean.DataBean.BodyBean dataBean) {
        //设置广告轮播条
        banner.setVisibility(View.VISIBLE);
        //2.设置Banner的数据
        List<String> images = new ArrayList<>();
        //获得广告条数据

        images.add(dataBean.getCover());
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
                intent.putExtra("web_json", dataBean.getUri() + ",," + dataBean.getTitle() + "此处没有文字");
                context.startActivity(intent);
            }
        });
    }
}
