package com.atguigu.bibiq.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.InjectView;

/**
 * 欢迎界面
 */
public class WelcomeActivity extends BaseActivity {

    @InjectView(R.id.tv_welcome_versions)
    TextView tvWelcomeVersions;
    @InjectView(R.id.iv_welcome_loading)
    ImageView ivWelcomeLoading;
    @InjectView(R.id.tv_welcome_into)
    TextView tvWelcomeInto;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    private Handler mHandler = new Handler();

    @Override
    protected void initData() {

        //设置版本号
        tvWelcomeVersions.setText("版本号: " + getVersions());
        //设置动画以及延迟消息
        setAnimation();

    }

    /**
     * 设置动画以及延迟消息
     */
    private void setAnimation() {
        //设置gif图片
        Glide.with(this)
                .load(R.drawable.dwonlo)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                //磁盘高速缓存策略
                // DiskCacheStrategy.NONE 什么都不缓存
                // DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像。
                // DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）
                // DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
                .into(ivWelcomeLoading);

        //设置5秒自动进入主界面
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotuMain();
            }
        }, 5000);

        //设置2秒显示提示按钮
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvWelcomeInto.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    /**
     * 进入主界面
     */
    private void gotuMain() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.main_alpha, R.anim.main_alpha_press);
        finish();
    }

    @Override
    protected void initListener() {
    }

    /**
     * 设置点击直接进入主界面
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mHandler.removeCallbacksAndMessages(null);
            gotuMain();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 获得版本号
     *
     * @return
     */
    public String getVersions() {
        String versionName = null;

        try {
            //拿到包的管理器
            PackageManager packageManager = getPackageManager();
            //拿到包的管理信息
            PackageInfo packageArchiveInfo = packageManager.getPackageInfo(getPackageName(), 0);

            //得到版本 ,每次提交需要增加1
            int versionCode = packageArchiveInfo.versionCode;

            //得到版本号
            versionName = packageArchiveInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
