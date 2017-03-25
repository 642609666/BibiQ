package com.atguigu.bibiq.find.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.atguigu.bibiq.base.BaseFragment;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/25 0025.
 * 功能:原创排行榜
 */
public class OriginalViewpagerAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> datas;
    String[] names = {"原创", "全站", "番剧"};

    public OriginalViewpagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.datas = list;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
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
