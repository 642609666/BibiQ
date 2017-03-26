package com.atguigu.bibiq.utils_search;

/**
 * Created by Won on 2017/1/13.
 */

/**
 * 接口回调
 */
public interface IOnSearchClickListener {

    //搜索回调
    void OnSearchClick(String keyword);

    //二维码回调
    void OnScanClick();

}
