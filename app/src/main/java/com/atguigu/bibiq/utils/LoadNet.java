package com.atguigu.bibiq.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/12 0012.
 * 功能:
 */

public class LoadNet {

    /**
     * 二次封装网络下载请求
     *
     * @param url 网络请求地址
     */
    public static void getDataNet(String url, final OnGetNet mOnGetNet) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String response, int id) {
                        //  Log.e("TAG", "主页请求数据成功" + content);
                        if (mOnGetNet != null) {
                            mOnGetNet.onSuccess(response);
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                        if (mOnGetNet != null) {
                            mOnGetNet.onSuccess(e.getMessage());
                        }
                    }


                });

    }

    private OnGetNet mOnGetNet;

    public void setOnGetNet(OnGetNet onGetNet) {
        mOnGetNet = onGetNet;
    }


    public interface OnGetNet {
        /**
         * 成功接口
         *
         * @param content
         */
        void onSuccess(String content);

        /**
         * 失败接口
         *
         * @param content
         */
        void onFailure(String content);
    }
}
