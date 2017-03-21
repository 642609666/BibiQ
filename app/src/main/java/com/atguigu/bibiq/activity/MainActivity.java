package com.atguigu.bibiq.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.adapter.MainViewPagerAdapter;
import com.atguigu.bibiq.base.BaseActivity;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.find.findFragment;
import com.atguigu.bibiq.play.PlayFragment;
import com.atguigu.bibiq.recommend.RecommendFragment;
import com.atguigu.bibiq.tothem.ToThemFragment;
import com.atguigu.bibiq.type.TypeFragment;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

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
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @InjectView(R.id.ll_more)
    LinearLayout llMore;
    @InjectView(R.id.drawlayout)
    DrawerLayout drawlayout;
    private ArrayList<BaseFragment> mList;

    private MainViewPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        //加载fragment碎片
        initFragment();


        adapter = new MainViewPagerAdapter(this.getSupportFragmentManager(), mList);

        viewPager.setAdapter(adapter);

        //添加复用,避免重复初始化
        viewPager.setOffscreenPageLimit(5);
        //关联ViewPager
        tablayout.setupWithViewPager(viewPager);

        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }


    private void initFragment() {
        mList = new ArrayList<>();
        mList.add(new PlayFragment());
        mList.add(new RecommendFragment());
        mList.add(new ToThemFragment());
        mList.add(new TypeFragment());
        mList.add(new findFragment());
    }

    @Override
    protected void initListener() {
        // ViewPager切换时NestedScrollView滑动到顶部
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                nestedScrollView.scrollTo(0, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.title_game, R.id.title_down,
            R.id.title_search, R.id.fab, R.id.ll_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_more: //点击出来侧滑
                Toast.makeText(MainActivity.this, "侧滑出来", Toast.LENGTH_SHORT).show();
                drawlayout.openDrawer(Gravity.LEFT);
                //初始化侧滑
                initMenu();
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
        }
    }

    private void initMenu() {
    }

}
