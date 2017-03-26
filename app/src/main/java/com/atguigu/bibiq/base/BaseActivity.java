package com.atguigu.bibiq.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.atguigu.bibiq.app.AppManager;
import com.atguigu.bibiq.search.SearchActivity;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.atguigu.bibiq.utils_search.IOnSearchClickListener;
import com.atguigu.bibiq.utils_search.SearchFragment;
import com.zhy.http.okhttp.OkHttpUtils;

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
        OkHttpUtils.delete().tag(this);
        AppManager.getInstance().remove(this);
    }

    /**
     * 显示搜索框
     */
    public void isShowSearch() {

        //实例化
        SearchFragment searchFragment = SearchFragment.newInstance();
        //搜索框内容回调
        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                //这里处理逻辑
                Intent intent = new Intent(BaseActivity.this, SearchActivity.class);
                intent.putExtra("url", ConstantAddress.SEARCH_HAND + keyword + ConstantAddress.SEARCH_TAIL);
                intent.putExtra("name", keyword);
                startActivity(intent);
            }

            //二维码回调
            @Override
            public void OnScanClick() {
                Log.e("TAG", "二维码");
                //这里处理逻辑
                Toast.makeText(BaseActivity.this, "我是二维码点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        //显示
        searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
    }
}
