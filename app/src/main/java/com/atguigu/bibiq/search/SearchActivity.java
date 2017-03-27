package com.atguigu.bibiq.search;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseActivity;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.search.adapter.MyBaseAdapter;
import com.atguigu.bibiq.search.adapter.MySearchTabLayoutAdapter;
import com.atguigu.bibiq.search.bean.SearchBean;
import com.atguigu.bibiq.search.fragment.BangumiFragment;
import com.atguigu.bibiq.search.fragment.SynthesisFragment;
import com.atguigu.bibiq.search.fragment.TelevisionFragment;
import com.atguigu.bibiq.search.fragment.UPFragment;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.atguigu.bibiq.view.MyGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;


public class SearchActivity extends BaseActivity {


    @InjectView(R.id.iv_web_back)
    ImageView ivWebBack;
    @InjectView(R.id.title_tv_name)
    TextView titleTvName;
    @InjectView(R.id.title_scan)
    ImageView titleScan;
    @InjectView(R.id.tablayout1)
    TabLayout tablayout1;
    @InjectView(R.id.tv_title_search_left)
    TextView tvTitleSearchLeft;
    @InjectView(R.id.iv_title_search_left)
    ImageView ivTitleSearchLeft;
    @InjectView(R.id.ll_title_search_left)
    LinearLayout llTitleSearchLeft;
    @InjectView(R.id.tv_title_search_centre)
    TextView tvTitleSearchCentre;
    @InjectView(R.id.iv_title_search_centre)
    ImageView ivTitleSearchCentre;
    @InjectView(R.id.ll_title_search_centre)
    LinearLayout llTitleSearchCentre;
    @InjectView(R.id.tv_title_search_right)
    TextView tvTitleSearchRight;
    @InjectView(R.id.iv_title_search_right)
    ImageView ivTitleSearchRight;
    @InjectView(R.id.ll_title_search_right)
    LinearLayout llTitleSearchRight;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.gridview)
    MyGridView gridview;


    @InjectView(R.id.btn_more)
    Button btnMore;
    @InjectView(R.id.ll_root)
    LinearLayout llRoot;
    private String url;
    private String name;

    private MySearchTabLayoutAdapter mAdapter;
    private MyBaseAdapter mBaseAdapter;
    private List<BaseFragment> mList;
    private Boolean isOpenLeft = true;
    private Boolean isOpenCentre = true;
    private Boolean isOpenRight = true;

    private String[] lift = {"默认排序", "播放多", "新发布", "弹幕多"};
    private String[] centre = {"全部时长", "0-10分钟", "10-30分钟", "30-60分钟", "60分钟+"};
    private String[] right = {"全部分区", "番剧", "动画", "国创", "音乐", "舞蹈",
            "游戏", "科技", "生活", "鬼畜", "时尚", "娱乐", "电影", "电视剧"};
    private SynthesisFragment mSynthesisFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {

        mBaseAdapter = new MyBaseAdapter(this);

        gridview.setAdapter(mBaseAdapter);

        //得到搜索到的数据
        url = getIntent().getStringExtra("url");
        name = getIntent().getStringExtra("name");
        //设置头部名字
        titleTvName.setText(name);


        //联网请求数据
        initFromNet(url);
        //

    }

    private void initFromNet(String url) {
        OkHttpUtils.get()
                .tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "搜索页面联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "搜索页面联网成功");

                        SearchBean searchBean = JSON.parseObject(response, SearchBean.class);
                        //设置碎片
                        initFragment(searchBean);

                        //设置适配
                        if (searchBean != null) {
                            isAdapter(searchBean.getData().getNav());
                        }
                    }
                });
    }

    private void isAdapter(List<SearchBean.DataBean.NavBean> nav) {
        List<String> list = new ArrayList<>();
        list.add("综合");
        for (int i = 0; i < nav.size(); i++) {
            if (nav.get(i).getTotal() > 0) {
                if (nav.get(i).getTotal() <= 99) {
                    list.add(nav.get(i).getName() + "(" + nav.get(i).getTotal() + ")");
                } else {
                    list.add(nav.get(i).getName() + "(" + 99 + "+)");
                }
            } else {
                list.add(nav.get(i).getName());
            }
        }
        mAdapter = new MySearchTabLayoutAdapter(getSupportFragmentManager(), mList, list);

        if (viewPager != null) {
            viewPager.setAdapter(mAdapter);
            //关联
            tablayout1.setupWithViewPager(viewPager);
            tablayout1.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    private void initFragment(SearchBean searchBean) {
        mList = new ArrayList<>();
        mSynthesisFragment = new SynthesisFragment(searchBean);
        mList.add(mSynthesisFragment);
        mList.add(new BangumiFragment());
        mList.add(new UPFragment());
        mList.add(new TelevisionFragment());
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.btn_more, R.id.iv_web_back, R.id.title_scan, R.id.ll_title_search_left, R.id.ll_title_search_centre, R.id.ll_title_search_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_web_back:
                finish();
                break;
            case R.id.btn_more:
                isOpenCentre = true;
                isOpenLeft = true;
                isOpenRight = true;
                ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_close);
                ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_close);
                ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_close);

                isDwonAnimation();
                break;
            case R.id.title_scan:
                Log.e("TAG", "二维码");
                Toast.makeText(SearchActivity.this, "二维码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_title_search_left:
                btnLeft();
                break;
            case R.id.ll_title_search_centre:
                btnCentre();
                break;
            case R.id.ll_title_search_right:
                btnRight();
                break;
        }
    }

    private void btnCentre() {
        if (isOpenCentre == true) {
            ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_open);
            llRoot.setVisibility(View.VISIBLE);

            if (mBaseAdapter != null) {
                mBaseAdapter.setListener(new MyBaseAdapter.IonClickListener() {
                    @Override
                    public void onListener(String name, int position) {
                        ivTitleSearchCentre.setClickable(false);
                        ivTitleSearchLeft.setClickable(false);
                        ivTitleSearchRight.setClickable(false);
                        tvTitleSearchCentre.setText(name);
                        mBaseAdapter.setDataNumber(position);
                        Log.e("TAG", "position" + position);
                        isDwonAnimation();
                        isOpenCentre = true;
                        ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_close);

                        if (position != 0) {
                            tvTitleSearchCentre.setTextColor(Color.parseColor("#FB7299"));
                        } else {
                            tvTitleSearchCentre.setTextColor(Color.parseColor("#7F7F7F"));
                        }
                        //设置假数据
                        initFromNet1(ConstantAddress.SEARCH_HAND + name + ConstantAddress.SEARCH_TAIL);
                    }
                });
                mBaseAdapter.setDataCentre(centre, isOpenCentre);
                mBaseAdapter.notifyDataSetChanged();
            }
            isOpenLeft = true;
            isOpenRight = true;
            ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_close);
            ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_close);
        } else {
            llRoot.setVisibility(View.GONE);
            ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_close);
        }
        isOpenCentre = !isOpenCentre;
    }

    private void btnRight() {
        if (isOpenRight == true) {
            ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_open);

            llRoot.setVisibility(View.VISIBLE);

            if (mBaseAdapter != null) {
                mBaseAdapter.setListener(new MyBaseAdapter.IonClickListener() {
                    @Override
                    public void onListener(String name, int position) {
                        isintright = position;
                        ivTitleSearchCentre.setClickable(false);
                        ivTitleSearchLeft.setClickable(false);
                        ivTitleSearchRight.setClickable(false);
                        tvTitleSearchRight.setText(name);
                        mBaseAdapter.setDataright(isintright);
                        Log.e("TAG", "position" + position);
                        isDwonAnimation();
                        isOpenLeft = true;
                        isOpenCentre = true;
                        isOpenRight = true;
                        isshowleft = false;
                        isshowcentre = false;
                        isshowright = true;
                        ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_close);

                        if (position != 0) {
                            tvTitleSearchRight.setTextColor(Color.parseColor("#FB7299"));
                        } else {
                            tvTitleSearchRight.setTextColor(Color.parseColor("#7F7F7F"));
                        }

                        //设置假数据
                        initFromNet1(ConstantAddress.SEARCH_HAND + name + ConstantAddress.SEARCH_TAIL);
                        //   mBaseAdapter.notifyDataSetChanged();
                    }
                });


            }
            isshowleft = false;
            isshowcentre = false;
            isshowright = true;
            isOpenCentre = true;
            isOpenLeft = true;
            mBaseAdapter.setData1(right, isshowright, isintright);
            mBaseAdapter.notifyDataSetChanged();
            ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_close);
            ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_close);
        } else {
            llRoot.setVisibility(View.GONE);
            ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_close);
        }
        isOpenRight = !isOpenRight;
    }

    private Boolean isshowleft = false;
    private Boolean isshowcentre = false;
    private Boolean isshowright = false;
    private int isintleft = 0;
    private int isintcentre = 0;
    private int isintright = 0;

    private void btnLeft() {
        if (isOpenLeft == true) {
            ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_open);
            //回调点击事件
            llRoot.setVisibility(View.VISIBLE);
            if (mBaseAdapter != null) {
                mBaseAdapter.setListener(new MyBaseAdapter.IonClickListener() {
                    @Override
                    public void onListener(String name, int position) {
                        isintleft = position;
                        tvTitleSearchLeft.setText(name);
                        mBaseAdapter.setDataleft(isintleft);
                        Log.e("TAG", "position" + position);
                        isDwonAnimation();
                        isOpenLeft = true;
                        isOpenCentre = true;
                        isOpenRight = true;
                        isshowleft = true;
                        isshowcentre = false;
                        isshowright = false;
                        ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_close);

                        //设置假数据
                        initFromNet1(ConstantAddress.SEARCH_HAND + name + ConstantAddress.SEARCH_TAIL);



                        if (position != 0) {
                            tvTitleSearchLeft.setTextColor(Color.parseColor("#FB7299"));
                        } else {
                            tvTitleSearchLeft.setTextColor(Color.parseColor("#7F7F7F"));
                        }

                        //  mBaseAdapter.notifyDataSetChanged();
                    }

                });

            }
            isOpenCentre = true;
            isOpenRight = true;
            isshowleft = true;
            isshowcentre = false;
            isshowright = false;
            mBaseAdapter.setData(lift, isshowleft, isintleft);
            mBaseAdapter.notifyDataSetChanged();
            ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_close);
            ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_close);
        } else {
            isDwonAnimation();
            llRoot.setVisibility(View.GONE);
            ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_close);
        }
        isOpenLeft = !isOpenLeft;
    }

    private void isDwonAnimation() {
        TranslateAnimation ta = new TranslateAnimation(0.0f, 0.0f, 0.0f, -gridview.getPivotY());
        AlphaAnimation aa = new AlphaAnimation(1, 0);
        ta.setDuration(50);
        aa.setDuration(50);
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new LinearInterpolator());
        set.addAnimation(ta);
        set.addAnimation(aa);
        llRoot.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llRoot.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initFromNet1(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "搜索页面联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "搜索页面联网成功");

                        SearchBean searchBean = JSON.parseObject(response, SearchBean.class);

                        if (mSynthesisFragment != null) {
                            mSynthesisFragment.setData(searchBean);
                        }
                    }
                });
    }

    private void isUpAnimation() {
        Log.e("TAG", "getPivotY()" + gridview.getPivotY());
        Log.e("TAG", "getY()" + gridview.getY());
        Log.e("TAG", "getRotationY()" + gridview.getRotationY());
        Log.e("TAG", "getScaleY()" + gridview.getScaleY());
        Log.e("TAG", "getScrollY()" + gridview.getScrollY());
        Log.e("TAG", "getTranslationY()" + gridview.getTranslationY());
        TranslateAnimation ta = new TranslateAnimation(0.0f, 0.0f, -20.0f, 100F);
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        ta.setDuration(300);
        aa.setDuration(300);
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new LinearInterpolator());
        set.addAnimation(ta);
        set.addAnimation(aa);
        llRoot.startAnimation(set);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.delete().tag(this);
    }

}
