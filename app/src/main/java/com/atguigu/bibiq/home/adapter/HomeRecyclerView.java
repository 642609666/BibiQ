package com.atguigu.bibiq.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.activity.LoginActivity;
import com.atguigu.bibiq.activity.WebActivity;
import com.atguigu.bibiq.bean.HomeBean;
import com.atguigu.bibiq.bean.HomeStreamingBean;
import com.atguigu.bibiq.view.MyGridView;
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

import static com.atguigu.bibiq.R.id.ll_home_title_btn;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:
 */

public class HomeRecyclerView extends RecyclerView.Adapter {


    private Context mContext;
    /**
     * 得到的总数据
     */
    private HomeBean.DataBean datas;

    private final int BANNER = 0; //广告条
    private final int TYPE = 1;   //分类
    private final int STREAMING = 2; //直播
    private final int DRAWING = 3; //绘画专区
    private final int LIFE = 4;//生活娱乐
    private final int SING = 5;//唱见遇见
    private final int MOBILEGAMELIVE = 6;//手游直播
    private final int MOBILEGAMELIVE1 = 7;//单击联机
    private final int MOBILEGAMELIVE2 = 8;//网络游戏
    private final int MOBILEGAMELIVE3 = 9;//电子竞技
    private final int MOBILEGAMELIVE4 = 10;//御宅文化
    private final int MOBILEGAMELIVE5 = 11;//放映厅
    private final int BUTTON = 12;//最后的button按钮
    private LayoutInflater inflater;
    private List<HomeStreamingBean.DataBean> homeStreamingBeanData;

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
        return datas.getPartitions().size() + 3 + 1;
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
        } else if (STREAMING == position) {
            temp = STREAMING;
        } else if (TYPE == position) {
            temp = TYPE;
        } else if (DRAWING == position) {
            temp = DRAWING;
        } else if (LIFE == position) {
            temp = LIFE;
        } else if (SING == position) {
            temp = SING;
        } else if (MOBILEGAMELIVE == position) {
            temp = MOBILEGAMELIVE;
        } else if (MOBILEGAMELIVE1 == position) {
            temp = MOBILEGAMELIVE1;
        } else if (MOBILEGAMELIVE2 == position) {
            temp = MOBILEGAMELIVE2;
        } else if (MOBILEGAMELIVE3 == position) {
            temp = MOBILEGAMELIVE3;
        } else if (MOBILEGAMELIVE4 == position) {
            temp = MOBILEGAMELIVE4;
        } else if (MOBILEGAMELIVE5 == position) {
            temp = MOBILEGAMELIVE5;
        } else if (BUTTON == position) {
            temp = BUTTON;
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
        if (BANNER == viewType) {
            return new MyBannerViewHolder(inflater.inflate(R.layout.adapter_banner, null));
        } else if (STREAMING == viewType) {
            return new MyStreamingViewHolder(inflater.inflate(R.layout.adapter_streaming, null));
        } else if (TYPE == viewType) {
            return new MyTypeViewHolder(inflater.inflate(R.layout.adapter_type, null));
        } else if (DRAWING == viewType) {
            return new MyDrawingViewHolder(inflater.inflate(R.layout.adapter_drawing, null));
        } else if (LIFE == viewType) {
            return new MyLifeViewHolder(inflater.inflate(R.layout.adapter_streaming, null));
        } else if (SING == viewType) {
            return new MySingViewHolder(inflater.inflate(R.layout.adapter_streaming, null));
        } else if (MOBILEGAMELIVE == viewType) {
            return new MyMobileGameLiveViewHolder(inflater.inflate(R.layout.adapter_streaming, null));
        } else if (MOBILEGAMELIVE1 == viewType) {
            return new MyMobileGameLiveViewHolder(inflater.inflate(R.layout.adapter_streaming, null));
        } else if (MOBILEGAMELIVE2 == viewType) {
            return new MyMobileGameLiveViewHolder(inflater.inflate(R.layout.adapter_streaming, null));
        } else if (MOBILEGAMELIVE3 == viewType) {
            return new MyMobileGameLiveViewHolder(inflater.inflate(R.layout.adapter_streaming, null));
        } else if (MOBILEGAMELIVE4 == viewType) {
            return new MyMobileGameLiveViewHolder(inflater.inflate(R.layout.adapter_streaming, null));
        } else if (MOBILEGAMELIVE5 == viewType) {
            return new MyMobileGameLiveViewHolder(inflater.inflate(R.layout.adapter_streaming, null));
        } else if (BUTTON == viewType) {
            return new MyButtonViewHolder(inflater.inflate(R.layout.adapter_button, null));
        }

        return null;
    }

    /**
     * 得到直播数据
     *
     * @param mHomeStreamingBeanData
     */
    public void setmHomeStreamingBeanData(List<HomeStreamingBean.DataBean> mHomeStreamingBeanData) {
        this.homeStreamingBeanData = mHomeStreamingBeanData;
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
        } else if (STREAMING == position) {
            MyStreamingViewHolder myStreamingViewHolder = (MyStreamingViewHolder) holder;
            myStreamingViewHolder.setData(mContext, homeStreamingBeanData);
        } else if (TYPE == position) {
            MyTypeViewHolder myTypeViewHolder = (MyTypeViewHolder) holder;
            myTypeViewHolder.setData(mContext, 5);
        } else if (DRAWING == position) {
            MyDrawingViewHolder myDrawingViewHolder = (MyDrawingViewHolder) holder;
            myDrawingViewHolder.setData(mContext, datas);
        } else if (LIFE == position) {
            MyLifeViewHolder myLifeViewHolder = (MyLifeViewHolder) holder;
            myLifeViewHolder.setData(mContext, datas);
        } else if (SING == position) {
            MySingViewHolder mySingViewHolder = (MySingViewHolder) holder;
            mySingViewHolder.setData(mContext, datas);
        } else if (MOBILEGAMELIVE == position) {
            MyMobileGameLiveViewHolder myMobileGameLiveViewHolder = (MyMobileGameLiveViewHolder) holder;
            myMobileGameLiveViewHolder.setData(mContext, datas.getPartitions().get(3));
        } else if (MOBILEGAMELIVE1 == position) {
            MyMobileGameLiveViewHolder myMobileGameLiveViewHolder = (MyMobileGameLiveViewHolder) holder;
            myMobileGameLiveViewHolder.setData(mContext, datas.getPartitions().get(4));
        } else if (MOBILEGAMELIVE2 == position) {
            MyMobileGameLiveViewHolder myMobileGameLiveViewHolder = (MyMobileGameLiveViewHolder) holder;
            myMobileGameLiveViewHolder.setData(mContext, datas.getPartitions().get(5));
        } else if (MOBILEGAMELIVE3 == position) {
            MyMobileGameLiveViewHolder myMobileGameLiveViewHolder = (MyMobileGameLiveViewHolder) holder;
            myMobileGameLiveViewHolder.setData(mContext, datas.getPartitions().get(6));
        } else if (MOBILEGAMELIVE4 == position) {
            MyMobileGameLiveViewHolder myMobileGameLiveViewHolder = (MyMobileGameLiveViewHolder) holder;
            myMobileGameLiveViewHolder.setData(mContext, datas.getPartitions().get(7));
        } else if (MOBILEGAMELIVE5 == position) {
            MyMobileGameLiveViewHolder myMobileGameLiveViewHolder = (MyMobileGameLiveViewHolder) holder;
            myMobileGameLiveViewHolder.setData(mContext, datas.getPartitions().get(8));
        } else if (BUTTON == position) {
            MyButtonViewHolder myButtonViewHolder = (MyButtonViewHolder) holder;
            myButtonViewHolder.setData(mContext);
        }
    }

    /**
     * 广告条
     */
    static class MyBannerViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.banner)
        Banner banner;

        public MyBannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final Context context, final List<HomeBean.DataBean.BannerBean> data, final int position) {
            //设置数据
            List<String> imageViews = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                imageViews.add(data.get(i).getImg());
            }

            //使用banner
            banner.setImages(imageViews)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {

                            Glide.with(context)
                                    .load(path)
                                    //添加缓存
                                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    //磁盘高速缓存策略
                                    // DiskCacheStrategy.NONE 什么都不缓存
                                    // DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像。
                                    // DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）
                                    // DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
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
                    if (position >= data.size()) {
                        position--;
                    }
                    intent.putExtra("web_json", data.get(position).getLink() + ",," + data.get(position).getTitle());
                    context.startActivity(intent);
                }
            });

        }
    }

    /**
     * 分类
     */
    static class MyTypeViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.gridview)
        GridView gridview;

        private TypeBaseAdapter mAdapter;
        private List<String> mList;

        public MyTypeViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(final Context context, int i) {
            mList = new ArrayList<>();
            mList.add("关注");
            mList.add("中心");
            mList.add("小视频");
            mList.add("搜索");
            mList.add("分类");
            mAdapter = new TypeBaseAdapter(context, mList);
            gridview.setAdapter(mAdapter);

            //设置点击事件
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   switch (position) {
                       case  0:
                           context.startActivity(new Intent(context, LoginActivity.class));
                           break;
                       case  1:
                           context.startActivity(new Intent(context, LoginActivity.class));
                           break;
                       case  2:
                           Toast.makeText(context, ""+mList.get(position), Toast.LENGTH_SHORT).show();
                           break;
                       case  3:
                           Toast.makeText(context, ""+mList.get(position), Toast.LENGTH_SHORT).show();
                           break;
                       case  4:
                           Toast.makeText(context, ""+mList.get(position), Toast.LENGTH_SHORT).show();
                           break;
                   }
                }
            });
        }
    }

    /**
     * 直播
     */
    static class MyStreamingViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_home_title_icon)
        ImageView ivHomeTitleIcon;
        @InjectView(R.id.tv_home_title_name)
        TextView tvHomeTitleName;
        @InjectView(R.id.ll_home_title_btn)
        LinearLayout ll_home_title_btn;
        @InjectView(R.id.tv_home_title_number)
        TextView tvHomeTitleNumber;
        @InjectView(R.id.tv_home_title_right)
        TextView tvHomeTitleRight;
        @InjectView(R.id.gridview)
        GridView gridview;
        @InjectView(R.id.btn_home_tail_more)
        Button btnHomeTailMore;
        @InjectView(R.id.tv_home_tail_number)
        TextView tvHomeTailNumber;
        @InjectView(R.id.tv_home_tail_right)
        TextView tvHomeTailRight;

        private StreamingBaseAdapter mAdapter;

        public MyStreamingViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("TAG", "222222222");
                }
            });
        }

        public void setData(final Context context, List<HomeStreamingBean.DataBean> homeStreamingBeanData) {
            if (homeStreamingBeanData != null) {
                mAdapter = new StreamingBaseAdapter(context, homeStreamingBeanData);

                gridview.setAdapter(mAdapter);


                ll_home_title_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "推荐直播", Toast.LENGTH_SHORT).show();
                    }
                });

                btnHomeTailMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });
                tvHomeTailRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    /**
     * 绘画专区
     */
    static class MyDrawingViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_home_title_icon)
        ImageView ivHomeTitleIcon;
        @InjectView(R.id.tv_home_title_name)
        TextView tvHomeTitleName;
        @InjectView(R.id.tv_home_title_number)
        TextView tvHomeTitleNumber;
        @InjectView(R.id.tv_home_title_right)
        TextView tvHomeTitleRight;
        @InjectView(ll_home_title_btn)
        LinearLayout llHomeTitleBtn;
        //        @InjectView(R.id.gridview)
        MyGridView gridview;
        @InjectView(R.id.btn_home_tail_more)
        Button btnHomeTailMore;
        @InjectView(R.id.tv_home_tail_number)
        TextView tvHomeTailNumber;
        @InjectView(R.id.tv_home_tail_right)
        TextView tvHomeTailRight;

        private HomeBeanAdapter mAdapter;

        public MyDrawingViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            this.gridview = (MyGridView) view.findViewById(R.id.gridview_drawable);
        }

        public void setData(final Context context, HomeBean.DataBean datas) {
            mAdapter = new HomeBeanAdapter(context, datas.getPartitions().get(0).getLives());

            this.gridview.setAdapter(mAdapter);

            //设置标头
            Glide.with(context)
                    .load(datas.getPartitions().get(0).getPartition().getSub_icon().getSrc())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(ivHomeTitleIcon);
            //设置名字
            tvHomeTitleName.setText(datas.getPartitions().get(0).getPartition().getName());
            //设置直播人数
            tvHomeTitleNumber.setText(datas.getPartitions().get(0).getPartition().getCount() + "");
            //假数据
            tvHomeTailNumber.setText(datas.getPartitions().get(0).getPartition().getCount() * 3 + "");

            //设置点击事件

            //设置点击事件
           /*gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "position ==" + position, Toast.LENGTH_SHORT).show();
                }
            });*/


            llHomeTitleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "绘画专区", Toast.LENGTH_SHORT).show();
                }
            });

            btnHomeTailMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                }
            });
            tvHomeTailRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 生活专区
     */
    static class MyLifeViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_home_title_icon)
        ImageView ivHomeTitleIcon;
        @InjectView(R.id.tv_home_title_name)
        TextView tvHomeTitleName;
        @InjectView(R.id.tv_home_title_number)
        TextView tvHomeTitleNumber;
        @InjectView(R.id.tv_home_title_right)
        TextView tvHomeTitleRight;
        @InjectView(ll_home_title_btn)
        LinearLayout llHomeTitleBtn;
        @InjectView(R.id.gridview)
        GridView gridview;
        @InjectView(R.id.btn_home_tail_more)
        Button btnHomeTailMore;
        @InjectView(R.id.tv_home_tail_number)
        TextView tvHomeTailNumber;
        @InjectView(R.id.tv_home_tail_right)
        TextView tvHomeTailRight;
        private HomeBeanAdapter mAdapter;

        public MyLifeViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(final Context context, HomeBean.DataBean datas) {
            mAdapter = new HomeBeanAdapter(context, datas.getPartitions().get(1).getLives());

            gridview.setAdapter(mAdapter);

            //设置标头
            Glide.with(context)
                    .load(datas.getPartitions().get(1).getPartition().getSub_icon().getSrc())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(ivHomeTitleIcon);
            //设置名字
            tvHomeTitleName.setText(datas.getPartitions().get(1).getPartition().getName());
            //设置直播人数
            tvHomeTitleNumber.setText(datas.getPartitions().get(1).getPartition().getCount() + "");
            //假数据
            tvHomeTailNumber.setText(datas.getPartitions().get(1).getPartition().getCount() * 3 + "");

            //设置点击事件

            //设置点击事件
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "position ==" + position, Toast.LENGTH_SHORT).show();
                }
            });

            llHomeTitleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "生活专区", Toast.LENGTH_SHORT).show();
                }
            });

            btnHomeTailMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                }
            });
            tvHomeTailRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 唱见遇见
     */
    static class MySingViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_home_title_icon)
        ImageView ivHomeTitleIcon;
        @InjectView(R.id.tv_home_title_name)
        TextView tvHomeTitleName;
        @InjectView(R.id.tv_home_title_number)
        TextView tvHomeTitleNumber;
        @InjectView(R.id.tv_home_title_right)
        TextView tvHomeTitleRight;
        @InjectView(ll_home_title_btn)
        LinearLayout llHomeTitleBtn;
        @InjectView(R.id.gridview)
        GridView gridview;
        @InjectView(R.id.btn_home_tail_more)
        Button btnHomeTailMore;
        @InjectView(R.id.tv_home_tail_number)
        TextView tvHomeTailNumber;
        @InjectView(R.id.tv_home_tail_right)
        TextView tvHomeTailRight;
        private HomeBeanAdapter mAdapter;

        public MySingViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(final Context context, HomeBean.DataBean datas) {
            mAdapter = new HomeBeanAdapter(context, datas.getPartitions().get(2).getLives());

            gridview.setAdapter(mAdapter);

            //设置标头
            Glide.with(context)
                    .load(datas.getPartitions().get(2).getPartition().getSub_icon().getSrc())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(ivHomeTitleIcon);
            //设置名字
            tvHomeTitleName.setText(datas.getPartitions().get(2).getPartition().getName());
            //设置直播人数
            tvHomeTitleNumber.setText(datas.getPartitions().get(2).getPartition().getCount() + "");
            //假数据
            tvHomeTailNumber.setText(datas.getPartitions().get(2).getPartition().getCount() * 3 + "");

            //设置点击事件

            //设置点击事件
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "position ==" + position, Toast.LENGTH_SHORT).show();
                }
            });

            llHomeTitleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "唱见遇见", Toast.LENGTH_SHORT).show();
                }
            });

            btnHomeTailMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                }
            });
            tvHomeTailRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 手游直播
     */
    static class MyMobileGameLiveViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_home_title_icon)
        ImageView ivHomeTitleIcon;
        @InjectView(R.id.tv_home_title_name)
        TextView tvHomeTitleName;
        @InjectView(R.id.tv_home_title_number)
        TextView tvHomeTitleNumber;
        @InjectView(R.id.tv_home_title_right)
        TextView tvHomeTitleRight;
        @InjectView(ll_home_title_btn)
        LinearLayout llHomeTitleBtn;
        @InjectView(R.id.gridview)
        GridView gridview;
        @InjectView(R.id.btn_home_tail_more)
        Button btnHomeTailMore;
        @InjectView(R.id.tv_home_tail_number)
        TextView tvHomeTailNumber;
        @InjectView(R.id.tv_home_tail_right)
        TextView tvHomeTailRight;

        private HomeBeanAdapter mAdapter;

        public MyMobileGameLiveViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(final Context context, HomeBean.DataBean.PartitionsBean datas) {
            mAdapter = new HomeBeanAdapter(context, datas.getLives());

            gridview.setAdapter(mAdapter);

            //设置标头
            Glide.with(context)
                    .load(datas.getPartition().getSub_icon().getSrc())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(ivHomeTitleIcon);
            //设置名字
            tvHomeTitleName.setText(datas.getPartition().getName());
            //设置直播人数
            tvHomeTitleNumber.setText(datas.getPartition().getCount() + "");
            //假数据
            tvHomeTailNumber.setText(datas.getPartition().getCount() * 3 + "");

            //设置点击事件

            //设置点击事件
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "position ==" + position, Toast.LENGTH_SHORT).show();
                }
            });

            llHomeTitleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "手游直播", Toast.LENGTH_SHORT).show();
                }
            });

            btnHomeTailMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                }
            });
            tvHomeTailRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 最后的button按钮
     */
    private class MyButtonViewHolder extends RecyclerView.ViewHolder {
        Button btn_button;
        public MyButtonViewHolder(View view) {
            super(view);
            btn_button = (Button) view.findViewById(R.id.btn_button);
        }

        public void setData(final Context context) {
            btn_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "更多直播", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
