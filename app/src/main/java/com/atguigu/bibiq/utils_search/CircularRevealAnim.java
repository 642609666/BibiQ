package com.atguigu.bibiq.utils_search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;

/**
 * 动画生成类
 */
public class CircularRevealAnim {

    public static final long DURATION = 500;

    private AnimListener mListener;

    public interface AnimListener {

        void onHideAnimationEnd();

        void onShowAnimationEnd();
    }

    @SuppressLint("NewApi")
    private void actionOtherVisible(final boolean isShow, final View triggerView, final View animView) {

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            if (isShow) {
                animView.setVisibility(View.VISIBLE);
                if (mListener != null) mListener.onShowAnimationEnd();
            } else {
                animView.setVisibility(View.GONE);
                if (mListener != null) mListener.onHideAnimationEnd();
            }
            return;
        }

/**
 * 计算 triggerView 的中心位置
 */
        int[] tvLocation = new int[2];
        triggerView.getLocationInWindow(tvLocation);
        int tvX = tvLocation[0] + triggerView.getWidth() / 2;
        int tvY = tvLocation[1] + triggerView.getHeight() / 2;

/**
 * 计算 animView 的中心位置
 */
        int[] avLocation = new int[2];
        animView.getLocationInWindow(avLocation);
        int avX = avLocation[0] + animView.getWidth() / 2;
        int avY = avLocation[1] + animView.getHeight() / 2;

        int rippleW = tvX < avX ? animView.getWidth() - tvX : tvX - avLocation[0];
        int rippleH = tvY < avY ? animView.getHeight() - tvY : tvY - avLocation[1];

        float maxRadius = (float) Math.sqrt(rippleW * rippleW + rippleH * rippleH);
        float startRadius;
        float endRadius;

        if (isShow) {
            startRadius = 0;
            endRadius = maxRadius;
        } else {
            startRadius = maxRadius;
            endRadius = 0;
        }

        Animator anim = ViewAnimationUtils.createCircularReveal(animView, tvX, tvY, startRadius, endRadius);
        animView.setVisibility(View.VISIBLE);
        anim.setDuration(DURATION);
        //DecelerateInterpolator// 一直再减速
        anim.setInterpolator(new DecelerateInterpolator());
        //自定义插值器
        // anim.setInterpolator(new MyInterpolator());


        ////BounceInterpolator// 小球回弹的效果
         //  anim.setInterpolator(new BounceInterpolator());

        //LinearInterpolator//默认的差值器线性
        // anim.setInterpolator(new LinearInterpolator());
        //AccelerateDecelerateInterpolator// 先快后慢
        // anim.setInterpolator(new AccelerateDecelerateInterpolator());
        //AccelerateInterpolator// 一直在加速
        // anim.setInterpolator(new AccelerateInterpolator());
        //AnticipateInterpolator// 先后移一段距离然后前进
        //  anim.setInterpolator(new AnticipateInterpolator());
        //AnticipateOvershootInterpolator// 先后移一段距离然后前进超过目标距离，再后移回来
        //anim.setInterpolator(new AnticipateOvershootInterpolator());
        //OvershootInterpolator// 前进超过目标距离，然后再后移回来
        // anim.setInterpolator(new OvershootInterpolator());

        //CycleInterpolator(3)//使动画循环，参数表示次数
        //anim.setInterpolator(new CycleInterpolator(1));


        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (isShow) {
                    animView.setVisibility(View.VISIBLE);
                    if (mListener != null) mListener.onShowAnimationEnd();
                } else {
                    animView.setVisibility(View.GONE);
                    if (mListener != null) mListener.onHideAnimationEnd();
                }
            }
        });

        anim.start();
    }

    public void show(View triggerView, View showView) {
        actionOtherVisible(true, triggerView, showView);
    }

    public void hide(View triggerView, View hideView) {
        actionOtherVisible(false, triggerView, hideView);
    }

    public void setAnimListener(AnimListener listener) {
        mListener = listener;
    }

}