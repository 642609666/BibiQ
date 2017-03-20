package com.atguigu.bibiq.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.atguigu.bibiq.app.AppManager;

import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/20 0020.
 * 功能:
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实现抽象方法由子类去自己设置布局
        setContentView(getLayoutId());

        //butterknife绑定
        ButterKnife.inject(this);

        //初始化数据
        initData();
        //设置监听
        initListener();
    }


    /**
     * 由子类设置布局文件
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 1.由子类初始化数据
     * 2.由子类联网下载数据
     */
    protected abstract void initData();

    /**
     * 设置监听效果
     */
    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //butterknife解绑
        ButterKnife.reset(this);
        AppManager.getInstance().remove(this);
    }
}
