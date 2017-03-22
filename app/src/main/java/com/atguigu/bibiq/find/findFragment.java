package com.atguigu.bibiq.find;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseFragment;
import com.zhy.view.flowlayout.TagFlowLayout;

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

    @Override
    public int getLayoutid() {
        return R.layout.fragment_find;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    protected void initData(String json) {
        Log.e("TAG", "发现数据初始化");
    }


    /**
     * 是查看更多还是收起
     * flase是收起状态
     */
    private Boolean isDwonOrUp = false;

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
                Toast.makeText(getActivity(), "更多", Toast.LENGTH_SHORT).show();
                if (isDwonOrUp) {
                    isDwonOrUp = !isDwonOrUp;
                    tvFindMore.setText("查看更多");
                    ivFindDownOrUp.setImageResource(R.drawable.ic_arrow_down_gray_round);
                } else {
                    isDwonOrUp = !isDwonOrUp;
                    tvFindMore.setText("收        起");
                    ivFindDownOrUp.setImageResource(R.drawable.ic_arrow_up);
                }
                break;
            case R.id.tv_find_interest:
                Toast.makeText(getActivity(), "兴趣圈", Toast.LENGTH_SHORT).show();
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
