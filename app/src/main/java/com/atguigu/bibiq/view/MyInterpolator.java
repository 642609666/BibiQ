package com.atguigu.bibiq.view;

import android.view.animation.Interpolator;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/26 0026.
 * 功能:自定义插值器
 */

public class MyInterpolator implements Interpolator {
    @Override
    //返回为float值 也就是实时的值
    public float getInterpolation(float input) {
        float factor = 0.4f;

        return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }

    float bounce(float t) {
        return t * t * 8;
    }


}
