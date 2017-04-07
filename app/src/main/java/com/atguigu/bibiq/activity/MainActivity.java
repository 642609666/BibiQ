package com.atguigu.bibiq.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.adapter.MainViewPagerAdapter;
import com.atguigu.bibiq.base.BaseActivity;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.find.fragment.findFragment;
import com.atguigu.bibiq.home.view.HomeFragment;
import com.atguigu.bibiq.recommend.view.RecommendFragment;
import com.atguigu.bibiq.store.store;
import com.atguigu.bibiq.tothem.view.ToThemFragment;
import com.atguigu.bibiq.type.TypeFragment;
import com.atguigu.bibiq.utils.ImmerseUtils;

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
    /* @InjectView(R.id.nestedScrollView)
     NestedScrollView nestedScrollView;*/
    @InjectView(R.id.ll_more)
    LinearLayout llMore;
    @InjectView(R.id.drawlayout)
    DrawerLayout drawlayout;
    @InjectView(R.id.iv_left_icon)
    ImageView ivLeftIcon;
    @InjectView(R.id.tv_left_name)
    TextView tvLeftName;
    @InjectView(R.id.iv_left_theme)
    ImageView ivLeftTheme;
    @InjectView(R.id.rg_left)
    RadioGroup rgLeft;
    @InjectView(R.id.rb_left_home)
    RadioButton rbLeftHome;
    @InjectView(R.id.rb_left_big_member)
    RadioButton rbLeftBigMember;
    @InjectView(R.id.rb_left_integral)
    RadioButton rbLeftIntegral;
    @InjectView(R.id.rb_left_cache)
    RadioButton rbLeftCache;
    @InjectView(R.id.rb_left_look)
    RadioButton rbLeftLook;
    @InjectView(R.id.rb_left_collect)
    RadioButton rbLeftCollect;
    @InjectView(R.id.rb_left_record)
    RadioButton rbLeftRecord;
    @InjectView(R.id.rb_left_attention)
    RadioButton rbLeftAttention;
    @InjectView(R.id.rb_left_wallet)
    RadioButton rbLeftWallet;
    @InjectView(R.id.rb_left_theme)
    RadioButton rbLeftTheme;
    @InjectView(R.id.rb_left_setting)
    RadioButton rbLeftSetting;
    private ArrayList<BaseFragment> mList;

    private MainViewPagerAdapter adapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        //加载fragment碎片
        initFragment();
        //加载适配器
        initAdapter();
    }

    /**
     * 加载适配器
     */
    private void initAdapter() {
        adapter = new MainViewPagerAdapter(this.getSupportFragmentManager(), mList);

        viewPager.setAdapter(adapter);

        //添加复用,避免重复初始化
        // viewPager.setOffscreenPageLimit(5);
        //关联ViewPager
        tablayout.setupWithViewPager(viewPager);

        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    private void initFragment() {
        mList = new ArrayList<>();
        mList.add(new HomeFragment());
        mList.add(new RecommendFragment());
        mList.add(new ToThemFragment());
        mList.add(new TypeFragment());
        mList.add(new findFragment());
        mList.add(new store());
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
                //如果是直播就显示  不是就隐藏
                if (position == 0) {
                    fab.setVisibility(View.VISIBLE);
                } else {
                    fab.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置rudion 按钮点击事件
        switchRb();
    }

    private Handler mHandler = new Handler();
    private boolean mBoolean = false;

    @Override
    public void onBackPressed() {
        if (mBoolean) {
            finish();
        }
        Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBoolean = false;
            }
        }, 2000);
        mBoolean = true;
    }

    /**
     * 左侧布局
     */
    private void switchRb() {
        rgLeft.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left_home://首页
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_big_member://我的大会员
                        Toast.makeText(MainActivity.this, "我的大会员", Toast.LENGTH_SHORT).show();
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_integral://会员积分
                        Toast.makeText(MainActivity.this, "会员积分", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_cache://离线缓存
                        Toast.makeText(MainActivity.this, "离线缓存", Toast.LENGTH_SHORT).show();
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_look://稍后再看
                        Toast.makeText(MainActivity.this, "稍后再看", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_collect://我的收藏
                        Toast.makeText(MainActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_record://历史记录
                        Toast.makeText(MainActivity.this, "历史记录", Toast.LENGTH_SHORT).show();
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_attention://我的关注
                        Toast.makeText(MainActivity.this, "我的关注", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_wallet://B币钱包
                        Toast.makeText(MainActivity.this, "B币钱包", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_theme://主题选择
                        Toast.makeText(MainActivity.this, "主题选择", Toast.LENGTH_SHORT).show();
                        drawlayout.closeDrawers();
                        break;
                    case R.id.rb_left_setting://设置与帮助
                        Toast.makeText(MainActivity.this, "设置与帮助", Toast.LENGTH_SHORT).show();
                        drawlayout.closeDrawers();
                        break;
                    default:
                        drawlayout.closeDrawers();
                        break;
                }

            }
        });

        //默认是主页
        rgLeft.check(R.id.rb_left_home);
    }

    /**
     * false表示白天
     * true 表示黑天
     */
    private Boolean isTheme = false;

    @OnClick({R.id.title_game, R.id.title_down, R.id.iv_left_icon,
            R.id.title_search, R.id.fab, R.id.ll_more, R.id.iv_left_theme})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_more: //点击出来侧滑
                ImmerseUtils.showImmerse(MainActivity.this);
                // Toast.makeText(MainActivity.this, "侧滑出来", Toast.LENGTH_SHORT).show();
                drawlayout.openDrawer(Gravity.LEFT);

                break;
            case R.id.iv_left_icon: //侧滑头像
                Toast.makeText(MainActivity.this, "头像", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.title_game://游戏
                Toast.makeText(MainActivity.this, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_down://下载
                Toast.makeText(MainActivity.this, "下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_search://搜索
                //实例化
                isShowSearch();
                break;
            case R.id.fab://悬浮按钮
                Toast.makeText(MainActivity.this, "悬浮按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_left_theme://主题
                if (isTheme) {
                    ivLeftTheme.setImageResource(R.drawable.ic_switch_night);
                } else {
                    ivLeftTheme.setImageResource(R.drawable.ic_switch_daily);
                }
                isTheme = !isTheme;
                break;
        }
    }
}
