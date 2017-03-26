package com.atguigu.bibiq.search.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.atguigu.bibiq.base.BaseFragment;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/26 0026.
 * 功能:搜索界面的tablayout适配器
 */
public class MySearchTabLayoutAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> datas;
    private final List<String> title;

    public MySearchTabLayoutAdapter(FragmentManager fm, List<BaseFragment> list, List<String> title) {
        super(fm);
        this.datas = list;
        this.title = title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    //防止破坏
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
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
