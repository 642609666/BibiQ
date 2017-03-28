package com.atguigu.bibiq.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.atguigu.bibiq.base.BaseFragment;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> datas;
    private String[] titles = new String[]{"直播", "推荐", "追番", "分区", "发现", "商城"};

    public MainViewPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.datas = list;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    /**
     * 获取页面标题
     * 显示标题才用到这个方法
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
