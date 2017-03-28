package com.atguigu.bibiq.gsyvideoplay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.atguigu.bibiq.base.BaseFragment;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/28 0028.
 * 功能:直播视频的viewpagere适配器
 */
public class MyViewViewpagerAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> list;
    private String[] string = {"互动", "排行榜", "舰队"};

    public MyViewViewpagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return string[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
