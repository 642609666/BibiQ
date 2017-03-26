package com.atguigu.bibiq.search;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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
import com.atguigu.bibiq.search.adapter.MySearchTabLayoutAdapter;
import com.atguigu.bibiq.search.bean.SearchBean;
import com.atguigu.bibiq.search.fragment.BangumiFragment;
import com.atguigu.bibiq.search.fragment.SynthesisFragment;
import com.atguigu.bibiq.search.fragment.TelevisionFragment;
import com.atguigu.bibiq.search.fragment.UPFragment;
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

        viewPager.setAdapter(mAdapter);
        //关联
        tablayout1.setupWithViewPager(viewPager);
        tablayout1.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragment(SearchBean searchBean) {
        mList = new ArrayList<>();
        mList.add(new SynthesisFragment(searchBean));
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
                if (isOpenLeft == true) {
                    isUpAnimation();
                    ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_open);
                    if (mBaseAdapter != null) {
                        llRoot.setVisibility(View.VISIBLE);
                        mBaseAdapter.setData(lift);
                        mBaseAdapter.notifyDataSetChanged();
                    }

                    isOpenCentre = true;
                    isOpenRight = true;
                    ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_close);
                    ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_close);
                } else {
                    llRoot.setVisibility(View.GONE);
                    ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_close);
                }
                isOpenLeft = !isOpenLeft;
                break;
            case R.id.ll_title_search_centre:
                if (isOpenCentre == true) {
                    isUpAnimation();
                    ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_open);
                    if (mBaseAdapter != null) {
                        llRoot.setVisibility(View.VISIBLE);
                        mBaseAdapter.setData(centre);
                        mBaseAdapter.notifyDataSetChanged();

                        isOpenLeft = true;
                        isOpenRight = true;
                        ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_close);
                        ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_close);
                    }
                } else {
                    llRoot.setVisibility(View.GONE);
                    ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_close);
                }
                isOpenCentre = !isOpenCentre;
                break;
            case R.id.ll_title_search_right:
                if (isOpenRight == true) {
                    isUpAnimation();
                    ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_open);
                    if (mBaseAdapter != null) {
                        llRoot.setVisibility(View.VISIBLE);
                        mBaseAdapter.setData(right);
                        mBaseAdapter.notifyDataSetChanged();


                        isOpenCentre = true;
                        isOpenLeft = true;
                        ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_close);
                        ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_close);
                    }
                } else {
                    llRoot.setVisibility(View.GONE);
                    ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_close);
                }
                isOpenRight = !isOpenRight;
                break;
        }
    }

    private void isDwonAnimation() {
        int height = gridview.getHeight();
        TranslateAnimation ta = new TranslateAnimation(0.0f, 0.0f, 0.0f, -height);
        AlphaAnimation aa = new AlphaAnimation(1,0);
        ta.setDuration(500);
        aa.setDuration(500);
        AnimationSet set = new AnimationSet(false);
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
    private void isUpAnimation() {
        int height = gridview.getMinimumHeight();
        TranslateAnimation ta = new TranslateAnimation(0.0f, 0.0f, 0.0f, height);
        AlphaAnimation aa = new AlphaAnimation(0,1);
        ta.setDuration(500);
        aa.setDuration(500);
        AnimationSet set = new AnimationSet(false);
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
