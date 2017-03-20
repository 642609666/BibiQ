package com.atguigu.bibiq.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.title_navigation)
    ImageView titleNavigation;
    @InjectView(R.id.title_icon)
    ImageView titleIcon;
    @InjectView(R.id.title_game)
    ImageView titleGame;
    @InjectView(R.id.title_down)
    ImageView titleDown;
    @InjectView(R.id.title_search)
    ImageView titleSearch;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.title_tv_isregister)
    TextView titleTvIsregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("这事什么");
//        toolbar.setSubtitle("这是上拉没有啦");
//        setSupportActionBar(toolbar);  //把toolbar添加到actionbar里

    }

    @OnClick({R.id.title_navigation, R.id.title_icon, R.id.title_game, R.id.title_down, R.id.title_search, R.id.fab, R.id.title_tv_isregister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_navigation: //点击出来侧滑
                Toast.makeText(MainActivity.this, "侧滑出来", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_icon://头像
                Toast.makeText(MainActivity.this, "头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_game://游戏
                Toast.makeText(MainActivity.this, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_down://下载
                Toast.makeText(MainActivity.this, "下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_search://搜索
                Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab://悬浮按钮
                Toast.makeText(MainActivity.this, "悬浮按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_tv_isregister://是否登录
                Toast.makeText(MainActivity.this, "是否登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
