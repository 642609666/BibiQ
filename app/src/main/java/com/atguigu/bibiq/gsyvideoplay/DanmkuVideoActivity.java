package com.atguigu.bibiq.gsyvideoplay;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.utils.BitmapUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.shuyu.gsyvideoplayer.GSYPreViewManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by guoshuyu on 2017/2/19.
 * 弹幕
 */


public class DanmkuVideoActivity extends AppCompatActivity {

    @InjectView(R.id.post_detail_nested_scroll)
    NestedScrollView postDetailNestedScroll;
    @InjectView(R.id.danmaku_player)
    DanmakuVideoPlayer danmakuVideoPlayer;
    @InjectView(R.id.activity_detail_player)
    RelativeLayout activityDetailPlayer;
    @InjectView(R.id.iv_view_icon)
    ImageView ivViewIcon;
    @InjectView(R.id.tv_view_title)
    TextView tvViewTitle;
    @InjectView(R.id.btn_view_vip)
    Button btnViewVip;
    @InjectView(R.id.iv_view_name)
    TextView ivViewName;
    @InjectView(R.id.tv_view_play)
    TextView tvViewPlay;
    @InjectView(R.id.btn_view_attention)
    Button btnViewAttention;
    @InjectView(R.id.btn_view_attention_number)
    Button btnViewAttentionNumber;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    private boolean isPlay;
    private boolean isPause;

    private OrientationUtils orientationUtils;
    private String url_play;
    private String url_name;
    private String url_title;
    private String url_face;
    private String url_online;

    private MyViewViewpagerAdapter mAdapter;

    private List<BaseFragment> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmaku_layout);
        ButterKnife.inject(this);
        isPause = true;
        //使用自定义的全屏切换图片，!!!注意xml布局中也需要设置为一样的
        //必须在setUp之前设置
        danmakuVideoPlayer.setShrinkImageRes(R.drawable.custom_shrink);
        danmakuVideoPlayer.setEnlargeImageRes(R.drawable.custom_enlarge);


        //设置控件数据
        initData();
        //设置viewpager适配器
        mAdapter = new MyViewViewpagerAdapter(getSupportFragmentManager(), mList);
        //关联
        viewpager.setAdapter(mAdapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        danmakuVideoPlayer.setUp(url_play, true, null, url_name + "的直播间");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);
        danmakuVideoPlayer.setThumbImageView(imageView);

        resolveNormalVideoUI();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, danmakuVideoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        danmakuVideoPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        danmakuVideoPlayer.setRotateViewAuto(false);
        danmakuVideoPlayer.setLockLand(false);
        danmakuVideoPlayer.setShowFullAnimation(false);
        danmakuVideoPlayer.setNeedLockFull(true);

        //detailPlayer.setOpenPreView(true);
        danmakuVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                danmakuVideoPlayer.startWindowFullscreen(DanmkuVideoActivity.this, true, true);
            }
        });

        danmakuVideoPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        danmakuVideoPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });

    }

    /**
     * 设置控件的数据
     */
    private void initData() {

        mList = new ArrayList<>();
        mList.add(new InteractionFragment());//互动
        mList.add(new BillboardFragment());//排行榜
        mList.add(new FleetFragment());//舰队


        url_play = getIntent().getStringExtra("url_play");
        url_name = getIntent().getStringExtra("url_name");
        url_title = getIntent().getStringExtra("url_title");
        url_face = getIntent().getStringExtra("url_face");
        url_online = getIntent().getStringExtra("url_online");
        //String url = "https://res.exexm.com/cw_145225549855002";
        tvViewPlay.setText(Integer.parseInt(url_online) >= 10000 ? (float) (Math.round((float) Integer.parseInt(url_online) / 10000 * 10)) / 10 + "万" : url_online);
        ivViewName.setText(url_name);
        tvViewTitle.setText(url_title);
        Glide.with(this)
                .load(url_face)
                .transform(new BitmapTransformation(this) {
                    @Override
                    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

                        return BitmapUtils.circleBitmap(toTransform);
                    }

                    @Override
                    public String getId() {
                        return "";
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(ivViewIcon);
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!danmakuVideoPlayer.isIfCurrentIsFullscreen()) {
                    danmakuVideoPlayer.startWindowFullscreen(DanmkuVideoActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (danmakuVideoPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }


    private void resolveNormalVideoUI() {
        //增加title
        danmakuVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        danmakuVideoPlayer.getTitleTextView().setText("测试视频");
        danmakuVideoPlayer.getBackButton().setVisibility(View.GONE);
    }

    @OnClick({R.id.iv_view_icon, R.id.tv_view_title, R.id.iv_view_name, R.id.btn_view_attention, R.id.iv_view_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_view_icon:
                Toast.makeText(DanmkuVideoActivity.this, "头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_view_title:
                Toast.makeText(DanmkuVideoActivity.this, "主播信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_view_name:
                Toast.makeText(DanmkuVideoActivity.this, "头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_view_attention:
                Toast.makeText(DanmkuVideoActivity.this, "关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_view_back:
                finish();
                break;
        }
    }
}