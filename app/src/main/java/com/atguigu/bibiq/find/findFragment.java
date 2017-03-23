package com.atguigu.bibiq.find;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.activity.LoginActivity;
import com.atguigu.bibiq.base.BaseFragment;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:发现页面
 */

public class findFragment extends BaseFragment {

    @InjectView(R.id.tv_find_search)
    TextView tvFindSearch;
    @InjectView(R.id.iv_find_saoyisao)
    ImageView ivFindSaoyisao;
    @InjectView(R.id.ivest_hot_fl)
    TagFlowLayout ivestHotFl;
    @InjectView(R.id.iv_find_down_or_up)
    ImageView ivFindDownOrUp;
    @InjectView(R.id.tv_find_more)
    TextView tvFindMore;
    @InjectView(R.id.ll_find_more)
    LinearLayout llFindMore;
    @InjectView(R.id.tv_find_interest)
    TextView tvFindInterest;
    @InjectView(R.id.tv_find_topics)
    TextView tvFindTopics;
    @InjectView(R.id.tv_find_activity)
    TextView tvFindActivity;
    @InjectView(R.id.tv_find_blackhouse)
    TextView tvFindBlackhouse;
    @InjectView(R.id.tv_find_original)
    TextView tvFindOriginal;
    @InjectView(R.id.tv_find_aregion)
    TextView tvFindAregion;
    @InjectView(R.id.tv_find_game)
    TextView tvFindGame;
    @InjectView(R.id.tv_find_shopping)
    TextView tvFindShopping;
    @InjectView(R.id.ivest_hot_fl1)
    TagFlowLayout ivestHotFl1;
    @InjectView(R.id.newstd)
    NestedScrollView newstd;
    /**
     * 热搜的数据
     */
    private List<FindBean.DataBean.ListBean> mListBeen;
    private Boolean isDwonOrUp = false;

    @Override
    public int getLayoutid() {
        return R.layout.fragment_find;
    }

    @Override
    public String getChildUrl() {
        return ConstantAddress.BBQ_FIND_HOT;
    }

    @Override
    protected void initData(String json) {
        Log.e("TAG", "发现数据初始化");
        //联网请求数据
        initFromNet(json);
    }

    private void initFromNet(String json) {
        if (!TextUtils.isEmpty(json)) {
            Log.e("TAG", "发现界面请求成功");
            FindBean findBean = JSON.parseObject(json, FindBean.class);
            mListBeen = findBean.getData().getList();

            //设置热搜
            initHot();
        }
    }

    private void initHot() {
        String[] strings = new String[mListBeen.size()];
        for (int i = 0; i < mListBeen.size(); i++) {
            strings[i] = mListBeen.get(i).getKeyword();
        }
        if (!isDwonOrUp) {
            //设置适配器
            ivestHotFl.setAdapter(new TagAdapter<String>(strings) {
                @Override
                public View getView(FlowLayout parent, int position, String o) {

                    final TextView textView = findFragment.this.getView(o);
                    return textView;
                }
            });
        } else {
            //设置适配器
            ivestHotFl1.setAdapter(new TagAdapter<String>(strings) {
                @Override
                public View getView(FlowLayout parent, int position, String o) {

                    final TextView textView = findFragment.this.getView(o);
                    return textView;
                }
            });
        }


    }

    /**
     * 流布局数据设置
     *
     * @param o
     * @return
     */
    @NonNull
    private TextView getView(String o) {
        final TextView textView = new TextView(getActivity());

        textView.setText(o);

        //给字体设置背景颜色
        textView.setBackgroundResource(R.drawable.hot_shape);
        //获取shape资源管理
        GradientDrawable background = (GradientDrawable) textView.getBackground();

        // textView.setTextColor(Color.rgb(red, blue, greed));
        //给字体背景shape设置背景随机颜色

        //设置点击事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "" + textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return textView;
    }


    /**
     * 是查看更多还是收起
     * flase是收起状态
     */


    @OnClick({R.id.tv_find_search, R.id.iv_find_saoyisao, R.id.ll_find_more, R.id.tv_find_interest, R.id.tv_find_topics, R.id.tv_find_activity, R.id.tv_find_blackhouse, R.id.tv_find_original, R.id.tv_find_aregion, R.id.tv_find_game, R.id.tv_find_shopping})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_find_search:
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_find_saoyisao:
                Toast.makeText(getActivity(), "扫一扫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_find_more:
                if (isDwonOrUp) {
                    isDwonOrUp = !isDwonOrUp;
                    tvFindMore.setText("查看更多");
                    ivFindDownOrUp.setImageResource(R.drawable.ic_arrow_down_gray_round);
                    ivestHotFl.setVisibility(View.VISIBLE);
                    newstd.setVisibility(View.GONE);
                } else {
                    isDwonOrUp = !isDwonOrUp;
                    tvFindMore.setText("收        起");
                    ivFindDownOrUp.setImageResource(R.drawable.ic_arrow_up);
                    newstd.setVisibility(View.VISIBLE);
                    ivestHotFl.setVisibility(View.GONE);
                }
                initHot();
                break;
            case R.id.tv_find_interest:
                Toast.makeText(getActivity(), "兴趣圈", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_find_topics:
                Toast.makeText(getActivity(), "话题中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_find_activity:
                Toast.makeText(getActivity(), "活动中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_find_blackhouse:
                Toast.makeText(getActivity(), "小黑屋", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_find_original:
                Toast.makeText(getActivity(), "原创排行榜", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_find_aregion:
                Toast.makeText(getActivity(), "全区排行榜", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_find_game:
                Toast.makeText(getActivity(), "游戏中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_find_shopping:
                Toast.makeText(getActivity(), "周边商城", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
