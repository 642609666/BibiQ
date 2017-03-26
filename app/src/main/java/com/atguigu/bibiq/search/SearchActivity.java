package com.atguigu.bibiq.search;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
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
    private String url;
    private String name;

    private MySearchTabLayoutAdapter mAdapter;

    private List<BaseFragment> mList;
    private Boolean isOpenLeft = false;
    private Boolean isOpenCentre = false;
    private Boolean isOpenRight = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        //得到搜索到的数据
        url = getIntent().getStringExtra("url");
        name = getIntent().getStringExtra("name");
        //设置头部名字
        titleTvName.setText(name);


        //联网请求数据
        initFromNet(url);
        //

        initViewpager();
    }

    private void initViewpager() {
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    switch (position) {
                        case 0:
                            Log.e("TAG", "1111");
                            break;
                        case 1:
                            Log.e("TAG", "2222");
                            break;
                        case 2:
                            Log.e("TAG", "3333");
                            break;
                        case 3:
                            Log.e("TAG", "4444");
                            break;
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
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
                    list.add(nav.get(i).getName() + "(" + 99 + ")");
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


    @OnClick({R.id.iv_web_back, R.id.title_scan, R.id.ll_title_search_left, R.id.ll_title_search_centre, R.id.ll_title_search_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_web_back:
                finish();
                break;
            case R.id.title_scan:
                Log.e("TAG", "二维码");
                Toast.makeText(SearchActivity.this, "二维码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_title_search_left:
                if (!isOpenLeft) {
                    ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_open);
                } else {

                    ivTitleSearchLeft.setImageResource(R.drawable.ic_bangumi_index_close);
                }
                isOpenLeft = !isOpenLeft;
                break;
            case R.id.ll_title_search_centre:
                if (!isOpenCentre) {
                    ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_open);
                } else {
                    ivTitleSearchCentre.setImageResource(R.drawable.ic_bangumi_index_close);
                }
                isOpenCentre = !isOpenCentre;
                break;
            case R.id.ll_title_search_right:
                if (!isOpenRight) {
                    ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_open);
                } else {
                    ivTitleSearchRight.setImageResource(R.drawable.ic_bangumi_index_close);
                }
                isOpenRight = !isOpenRight;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.delete().tag(this);
    }
}
