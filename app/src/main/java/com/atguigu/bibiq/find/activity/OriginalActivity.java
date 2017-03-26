package com.atguigu.bibiq.find.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseActivity;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.find.fragment.OriginalAllFragment;
import com.atguigu.bibiq.find.fragment.OriginalBangumiFragment;
import com.atguigu.bibiq.find.fragment.OriginalFragment;
import com.atguigu.bibiq.find.adapter.OriginalViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 原创视频排行榜
 */
public class OriginalActivity extends BaseActivity {

    @InjectView(R.id.iv_web_back)
    ImageView ivWebBack;
    @InjectView(R.id.title_tv_isregister)
    TextView titleTvIsregister;
    @InjectView(R.id.ll_more)
    LinearLayout llMore;
    @InjectView(R.id.title_game)
    ImageView titleGame;
    @InjectView(R.id.title_down)
    ImageView titleDown;
    @InjectView(R.id.title_search)
    ImageView titleSearch;
    @InjectView(R.id.tablayout1)
    TabLayout tablayout1;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;

    private List<BaseFragment> mList;
    private OriginalViewpagerAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_original;
    }

    @Override
    protected void initData() {
        //初始化碎片
        initFragment();

        mAdapter = new OriginalViewpagerAdapter(getSupportFragmentManager(), mList);

        viewPager.setAdapter(mAdapter);

        tablayout1.setupWithViewPager(viewPager);

        tablayout1.setTabMode(TabLayout.MODE_SCROLLABLE);

        titleTvIsregister.setText("原创排行榜");

        titleGame.setVisibility(View.GONE);
    }

    private void initFragment() {
        mList = new ArrayList<>();
        mList.add(new OriginalFragment());
        mList.add(new OriginalAllFragment());
        mList.add(new OriginalBangumiFragment());
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.iv_web_back, R.id.title_down, R.id.title_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_web_back:
                finish();
                break;
            case R.id.title_down:
                Toast.makeText(OriginalActivity.this, "下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_search:
                isShowSearch();
               // Toast.makeText(OriginalActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
