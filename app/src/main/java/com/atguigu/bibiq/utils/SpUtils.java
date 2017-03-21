package com.atguigu.bibiq.utils;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/21 0021.
 * 功能:
 */

public class SpUtils {


    //sp保存json数据
    public static void saveSP(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("json", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    //sp获取json数据
    public static String getSP(Context context, String key) {
        return context.getSharedPreferences("json", Context.MODE_PRIVATE).getString(key, null);
    }
}
